package br.com.restassuredapitesting.tests.booking.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;


public class DeleteBookingRequest{

        @Step("Excluir reserva")
        public Response deleteBooking(int id){
            return given()
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                    .delete("booking/" + id);
        }

        @Step("Excluir reserva sem autorização")
        public Response deleteWithoutAuth(int id){
            return given()
                    .header("Content-Type", "application/json")
                    .delete("booking/" + id);
        }
}
