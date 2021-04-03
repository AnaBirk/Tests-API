package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.suites.Acceptance;
import br.com.restassuredapitesting.suites.E2e;
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

    PutBookingnRequest putBookingnRequest= new PutBookingnRequest();
    GetBookingRequest getBookingRequest = new GetBookingRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Alterar uma reserva utilizando token")
    public void updateBookingByToken() throws Exception {
        int getId = getBookingRequest.allBookings().then().statusCode(200).extract().path("[1].bookingid");
         putBookingnRequest.updateBookingToken(getId, Utils.validPayloadBooking()).then()
                .statusCode(200)
                .time(lessThan(3L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Alterar uma reserva usando o Basic auth")
    public void updateBookingByBasic() throws Exception {
        int getId = getBookingRequest.allBookings().then().statusCode(200).extract().path("[6].bookingid");
        putBookingnRequest.updateBookingBasic(getId, Utils.validPayloadBooking()).then()
                .statusCode(200)
                .time(lessThan(2L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Tentar alterar uma reserva que não existe")
    public void ReservaNaoExistente() throws Exception {
        putBookingnRequest.updateBookingToken(100, Utils.validPayloadBooking()).then()
                .statusCode(200)
                .time(lessThan(3L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0)); //ERRO 405
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Tentar alterar uma reserva quando o token não for enviado")
    public void updateBookingWithoutToken() throws Exception{
        int getIdReserva = getBookingRequest.allBookings().then().statusCode(200).extract().path("[5].bookingid");

        putBookingnRequest.alterarReservaSemToken(getIdReserva, Utils.validPayloadBooking()).then()
                .statusCode(200)
                .time(lessThan(3L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0)); //ERRO 403 Forbidden
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Tentar alterar uma reserva quando o token enviado for inválido")
    public void alterarReservaComTokenInvalido() throws Exception{
        int getIdReserva = getBookingRequest.allBookings().then().statusCode(200).extract().path("[8].bookingid");

        putBookingnRequest.alterarReservaComTokenInvalido(getIdReserva, Utils.validPayloadBooking()).then()
                .statusCode(200)
                .time(lessThan(3L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0)); //ERRO 403 Forbidden
    }
}
