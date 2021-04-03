package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.utils.Utils;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;


public class PostBookingRequest {

    @Step("Request para criar reserva")
    public static Response criarReserva(){
        return given()
                .header("Content-Type", "application/json")
                .body(Utils.validPayloadBooking())
                .when()
                .post("booking");
    }

    @Step("Request de Post sem Body")
    public static Response invalidPayload(){
        return given()
                .header("Content-Type", "application/json")
                .when()
                .post("booking");
    }

    @Step("Criar uma reserva com mais parametros")
    public static Response additionalBookingParameters(){
        return given()
                .header("Content-Type", "application/json")
                .body(Utils.payloadBookingWithMoreParameters())
                .when()
                .post("booking");
    }

    @Step("header Accept inválido")
    public static Response invalidHeaderAccept(){
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/oog")
                .body(Utils.validPayloadBooking())
                .post("booking");
        }
}
