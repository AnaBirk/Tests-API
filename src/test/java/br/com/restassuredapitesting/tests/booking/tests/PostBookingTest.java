package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.suites.Acceptance;
import br.com.restassuredapitesting.suites.E2e;
import br.com.restassuredapitesting.tests.base.tests.BaseTest;
import br.com.restassuredapitesting.tests.booking.requests.PostBookingRequest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;


public class PostBookingTest extends BaseTest {

    PostBookingRequest postBookingRequest = new PostBookingRequest();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category(Acceptance.class)
    @DisplayName("Criar uma nova reserva")
    public void createBooking() throws Exception{
        postBookingRequest.criarReserva()
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Validar retorno 500 quando o payload da reserva estiver inválido")
    public void validateInternalServerError () throws Exception{
        postBookingRequest.invalidPayload()
                .then()
                .assertThat()
                .statusCode(500);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Validar a criação de uma reserva em sequência")
    public void testCreatBookings() throws Exception{
        postBookingRequest.criarReserva()
                .then()
                .assertThat()
                .statusCode(200);
        postBookingRequest.criarReserva()
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Criar uma reserva enviando mais parâmetros no payload da reserva")
    public void createBookingWithMoreParameters() throws Exception{
        postBookingRequest.additionalBookingParameters()
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Validar retorno 418 quando o header Accept for inválido")
    public void testValidateTeapot(){
        postBookingRequest.invalidHeaderAccept()
                .then()
                .assertThat()
                .statusCode(418);
    }
}

