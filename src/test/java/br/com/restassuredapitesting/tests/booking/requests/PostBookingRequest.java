package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.utils.Utils;
import io.qameta.allure.Step;

import javax.xml.ws.Response;

import static io.restassured.RestAssured.given;

public class PostBookingRequest {
    
    //gerar requisição
    
    @Step("Criar uma reserva")
    public Response criarReserva(){
        return (Response) given()
                .header("Content-Type", "application/json")
                .body(Utils.validPayloadBooking())
                .when()
                .post("booking");

    }
}
