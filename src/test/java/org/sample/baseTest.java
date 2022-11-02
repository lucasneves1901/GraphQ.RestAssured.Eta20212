package org.sample;

import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;

public class baseTest {

    @BeforeClass
    public static void setup() {
        enableLoggingOfRequestAndResponseIfValidationFails();
        baseURI = "https://countries.trevorblades.com/";
    }
}
