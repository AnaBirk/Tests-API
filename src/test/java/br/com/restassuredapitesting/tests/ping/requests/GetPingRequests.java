package br.com.restassuredapitesting.tests.ping.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;


public class GetPingRequests {

    @Step("Buscar o Ping")
    public Response getPing(){
        return given()
                .get("ping");
    }
}

