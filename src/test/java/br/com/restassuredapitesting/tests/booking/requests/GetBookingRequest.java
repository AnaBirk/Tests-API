package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.utils.Utils;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;


public class GetBookingRequest {
    @Step("Buscar todas as reservas")
    public Response allBookings(){
        return given()
                .header("Content-Type","Application/json")
                .when()
                .get("booking");
    }

    @Step("Buscar uma reserva especifica")
    public Response getOneBooking(String id){
        return given()
                .header("Accept","application/json")
                .body(Utils.validPayloadBooking())
                .when()
                .get("booking/" + id);
    }

    @Step("Buscar todas as reservas usando o filtro firstname")
    public Response firstNameFilter(String firstname){
        return given()
                .header("Accept","application/json")
                .when()
                .get("booking?firstname=" + firstname);
    }

    @Step("Buscar todas as reservas usando o filtro lastname")
    public Response lastnameFilter(String lastname) {
        return given()
                .header("Accept", "application/json")
                .when()
                .get("booking?lastname=" + lastname);
    }

    @Step("Buscar todas as reservas usando o filtro checkin")
    public Response CheckinFilter(String checkin){
        return given()
                .header("Accept", "application/json")
                .when()
                .get("booking?checkin=" + checkin);
    }

    @Step("Listar IDs de reservas utilizando o filtro checkout")
        public Response checkoutFilter(String  checkout){
        return given()
                .header("Accept", "application/json")
                .when()
                .get("booking?checkout=" + checkout);
    }

    @Step("Listar IDs de reservas utilizando o filtro checkout and checkout")
        public Response checkoutAndCheckoutFilter(String checkout){
        return given()
                .header("Accept", "application/json")
                .when()
                .get("booking?checkout=" + checkout + "&checkout=" + checkout);
    }

    @Step("Listar IDs de reservas utilizando o filtro name, checkin and checkout date")
    public Response nameCheckinAndCheckoutFilter( String firstname, String lastname, String checkin, String checkout){
        return given()
                .header("Accept", "application/json")
                .when()
                .get("booking?firstname=" + firstname+ "&lastname=" + lastname + "&checkin=" + checkin + "&checkout=" + checkout);
    }
}

