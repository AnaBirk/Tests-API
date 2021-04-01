package br.com.restassuredapitesting.tests.base.tests;

import io.restassured.RestAssured;
import org.junit.BeforeClass;

// Aqui é o teste em si, o objeto de teste
public class BaseTest {
    @BeforeClass
    public static void setup(){
        //Configura a URL base
        RestAssured.baseURI = "https://treinamento-api.herokuapp.com/";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
