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

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Tentar alterar uma reserva que não existe")
    public void ReservaNaoExistente() throws Exception {
        //Preciso passar um Id pro nosso put, só que preciso pegar esse Id do meu Get
        //int primeiroId = getBookingRequest.allBookings().then().statusCode(200).extract().path();
        putBookingnRequest.alterarUmaReservaComToken(100, Utils.validPayloadBooking()).then()
                .statusCode(200)
                .time(lessThan(3L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Tentar alterar uma reserva quando o token não for enviado")
    public void alterarReservaSemToken() throws Exception{
        int primeiroId = getBookingRequest.allBookings().then().statusCode(200).extract().path("[0].bookingid");

        putBookingnRequest.alterarReservaSemToken(primeiroId, Utils.validPayloadBooking()).then()
                .statusCode(200)
                .time(lessThan(3L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0)); //HTTP/1.1 403 Forbidden
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Tentar alterar uma reserva quando o token enviado for inválido")
    public void alterarReservaComTokenInvalido() throws Exception{
        int primeiroId = getBookingRequest.allBookings().then().statusCode(200).extract().path("[0].bookingid");

        putBookingnRequest.alterarReservaComTokenInvalido(primeiroId, Utils.validPayloadBooking()).then()
                .statusCode(200)
                .time(lessThan(3L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0)); //HTTP/1.1 403 Forbidden*/
    }



    /*
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Alterar uma reserva utilizando o basic")
    public void validadeAlterarUmaReservaUtilizandoBasic() throws Exception {
        //Preciso passar um Id pro nosso put, só que preciso pegar esse Id do meu Get
        int primeiroId = getBookingRequest.allBookings().then().statusCode(200).extract().path("[0].bookingid");

        System.out.println(primeiroId);

        putBookingnRequest.(primeiroId, Utils.validPayloadBooking().then()
                .statusCode(200)
                .time(lessThan(3L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }
*/


}
