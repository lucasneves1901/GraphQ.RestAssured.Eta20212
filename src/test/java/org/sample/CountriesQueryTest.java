package org.sample;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;

public class CountriesQueryTest extends baseTest {

    @Test
    public void validateBrazilCountry() {
        String queryCountries = "{\"query\":\"query {\\n  country(code: \\\"BR\\\") {\\n    code\\n\\t\\tnative\\n    phone\\n    capital\\n    currency\\n    emoji\\n  }\\n}\\n\"}";

        Response response =
                given()
                        .header("Content-Type", "application/json")
                        .and().body(queryCountries)
                        .when().post();

        response.then().log().body();
        response.then().statusCode(200);
        response.then().body("data.country.phone", equalTo("55"));
        response.then().body("data.country.capital", equalTo("Brasília"));
        response.then().body("data.country.currency", equalTo("BRL"));
    }

    @Test
    public void validateSpainCountry() {
        String queryCountries = "{\"query\":\"query {\\n  country(code: \\\"AR\\\") \\n}\\n\"}";

        Response response =
                given()
                        .header("Content-Type", "application/json")
                        .and().body(queryCountries)
                        .when().post();

        response.then().log().body();
        response.then().statusCode(400);
        response.then().body("errors.extensions.code", contains("GRAPHQL_VALIDATION_FAILED"));
    }

    @Test
    public void validateIncorrectQuery() {
        String queryCountries = "{\"query\":\"query {\\n  country(code: \\\"100\\\") {\\n    code\\n\\t\\tnative\\n    phone\\n    capital\\n    currency\\n    emoji\\n  }\\n}\\n\"}";

        Response response =
                given()
                        .header("Content-Type", "application/json")
                        .and().body(queryCountries)
                        .when().post();

        response.then().log().body();
        response.then().statusCode(200);
        response.then().body("data.country", equalTo(null));
    }
}

