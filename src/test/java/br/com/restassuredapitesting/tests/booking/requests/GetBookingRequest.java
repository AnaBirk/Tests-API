package br.com.restassuredapitesting.tests.booking.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;


//Get -> Método que estamos utilizando
//Booking -> Endpoint(funcionalidade)
//Request -> O motivo (o que estamos fazendo aqui)
//Request do tipo Get para o Booking - reservas
public class GetBookingRequest {
    @Step("Buscar todas as reservas")
    public Response allBookings(){
        //Esse método ira retornar o Response do Get
        return given()  //given é uma anotação do RestAssured, usado para dar instruções
                .header("Content-Type","Application/json")
                .when()
                .get("booking");
    }
}
