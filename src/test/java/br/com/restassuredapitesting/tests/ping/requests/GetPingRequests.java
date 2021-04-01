package br.com.restassuredapitesting.tests.ping.requests;

import br.com.restassuredapitesting.tests.base.tests.BaseTest;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetPingRequests {

    @Step("Buscar o Ping")
    public Response helthPing(){
        return given()
                .get("ping");
    }
}

