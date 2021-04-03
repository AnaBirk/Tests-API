package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.suites.Acceptance;
import br.com.restassuredapitesting.suites.E2e;
import br.com.restassuredapitesting.tests.base.tests.BaseTest;
import br.com.restassuredapitesting.tests.booking.requests.DeleteBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.PostBookingRequest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;


public class DeleteBookingTest extends BaseTest {

    DeleteBookingRequest deleteBookingRequest = new DeleteBookingRequest();
    PostBookingRequest postBookingRequest = new PostBookingRequest();


    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category(Acceptance.class)
    @DisplayName("Excluir um reserva com sucesso")
    public void deleteBookingWithSucess() throws Exception{
        int idReservCriada = postBookingRequest.criarReserva().then().statusCode(200).extract().path("bookingid");
        deleteBookingRequest.deleteBooking(idReservCriada)
                .then()
                .assertThat()
                .statusCode(201);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Deletar uma reserva que não existe")
    public void deleteBookingThatDoesNotExist()throws Exception{
        deleteBookingRequest.deleteBooking(-1)
                .then()
                .assertThat()
                .statusCode(201); //ERRO 405
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Deletar uma reserva sem autorização")
    public void deleteBookingwithoutAuthorization() throws Exception{
        int idReservCriada = postBookingRequest.criarReserva().then().statusCode(200).extract().path("bookingid");
        deleteBookingRequest.deleteWithoutAuth(idReservCriada)
                .then()
                .assertThat()
                .statusCode(201); //ERRO 403 Forbidden
    }
}
