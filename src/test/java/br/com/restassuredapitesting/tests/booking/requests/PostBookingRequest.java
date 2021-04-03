package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.utils.Utils;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;


public class PostBookingRequest {

    @Step("Criar uma reserva")
    public static Response criarReserva(){
        return given()
                .header("Content-Type", "application/json")
                .body(Utils.validPayloadBooking())
                .when()
                .post("booking");
    }

    @Step("Request de Post sem Body")
    public static Response payloadInvalido(){
        return given()
                .header("Content-Type", "application/json")
                .when()
                .post("booking");
    }


    //Validar a criação de mais de um livro em sequência
    //Criar uma reserva enviando mais parâmetros no payload da reserva
    @Step("Criar uma reserva com mais parametros")
    public static Response reservaComMaisParametos(){
        return given()
                .header("Content-Type", "application/json")
                .body(Utils.payloadBookingWithMoreParameters())
                .when()
                .post("booking");
    }




    @Step("header Accept invalido")
    public static Response headerAcceptInvalido(){
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/oog")
                .body(Utils.validPayloadBooking())
                .post("booking");
        }
}
