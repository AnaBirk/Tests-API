package br.com.restassuredapitesting.tests.booking.requests;


import br.com.restassuredapitesting.tests.auth.requests.PostAuthRequest;
import br.com.restassuredapitesting.tests.booking.tests.DeleteBookingTest;
import io.qameta.allure.Severity;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class DeleteBookingRequest{

        PostAuthRequest postAuthRequest = new PostAuthRequest();

        @Step("Excluir reserva")
        public Response excluirReserva(int id){
            return given()
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                    .delete("booking/" + id);
        }


    @Step("Excluir reserva sem autorização")
    public Response excluirReservaSemAuth(int id){
        return given()
                .header("Content-Type", "application/json")
                .delete("booking/" + id);
    }


}
