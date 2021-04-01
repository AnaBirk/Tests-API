package br.com.restassuredapitesting.tests.booking.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import java.util.Date;

import static io.restassured.RestAssured.given;

public class GetBookingRequest {
    @Step("Buscar todas as reservas")
    public Response allBookings(){
        //Esse método ira retornar o Response do Get
        return given()  //given é uma anotação do RestAssured, usado para dar instruções
                .header("Content-Type","Application/json")
                .when()
                .get("booking");
    }


    @Step("Buscar uma reserva especifica")
    public Response reservaEspecifica(String id){
        return given()
                .header("Accept","application/json")
                .when()
                .get("booking/" + id);
    }
    @Step("Buscar todas as reservas usando o filtro firstname")
    public Response allBookingsUsingFirstName(String firstname){
        return given()
                .header("Accept","application/json")
                .when()
                .get("booking?firstname=" + firstname);
    }

    @Step("Buscar todas as reservas usando o filtro lastname")
    public Response allBookingsUsingLastname(String lastname) {
        return given()
                .header("Accept", "application/json")
                .when()
                .get("booking?lastname=" + lastname);
    }

    @Step("Buscar todas as reservas usando o filtro checkin")
    public Response allBookingsFilterByCheckin(String checkin){
        return given()
                .header("Accept", "application/json")
                .when()
                .get("booking?checkin=" + checkin);
    }

    @Step("Listar IDs de reservas utilizando o filtro checkout")
        public Response allBookingsUsingCheckout(String  checkout){
        return given()
                .header("Accept", "application/json")
                .when()
                .get("booking?checkout=" + checkout);
    }

    @Step("Listar IDs de reservas utilizando o filtro checKOUT and checkout")
        public Response allBookingsFilterByCheckoutAndCheckout(String checkout){
        return given()
                .header("Accept", "application/json")
                .when()
                .get("booking?checkout=" + checkout + "&checkout=" + checkout);
    }

    @Step("Listar IDs de reservas utilizando o filtro name, checkin and checkout date")
    public Response allBookingsFilterByNameAndCheckinAndCheckout(String checkin, String checkout, String firstname, String lastname){
        return given()
                .header("Accept", "application/json")
                .when()
                .get("booking?firstname=" + firstname+ "&lastname=" + lastname + "&checkin=" + checkin + "&checkout=" + checkout);
    }



}

