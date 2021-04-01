package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.utils.Utils;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;


public class PostBookingRequest {
    
    //gerar requisição
    @Step("Criar uma reserva")
    public Response criarReserva(){
        return given()
                .header("Content-Type", "application/json")
                .body(Utils.validPayloadBooking())
                .when()
                .post("booking");

    }
}
