package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.suites.Acceptance;
import br.com.restassuredapitesting.tests.base.tests.BaseTest;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.PutBookingnRequest;
import br.com.restassuredapitesting.utils.Utils;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

@Feature("Reservas")
public class PutBookingTest extends BaseTest {

    //Para alterar, eu preciso de um Id, a unica listagem de ids é Get. Então precisa iniciar o getBookingRequest
    PutBookingnRequest putBookingnRequest= new PutBookingnRequest();
    GetBookingRequest getBookingRequest = new GetBookingRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Alterar uma reserva utilizando token")
    public void validadeAlterarUmaReservaUtilizandoToken() throws Exception {
        //Preciso passar um Id pro nosso put, só que preciso pegar esse Id do meu Get
        int primeiroId = getBookingRequest.allBookings().then().statusCode(200).extract().path("[0].bookingid");

        System.out.println(primeiroId);

         putBookingnRequest.alterarUmaReservaComToken(primeiroId, Utils.validPayloadBooking()).then()
                .statusCode(200)
                .time(lessThan(3L), TimeUnit.SECONDS)
            .body("size()", greaterThan(0));
}

}