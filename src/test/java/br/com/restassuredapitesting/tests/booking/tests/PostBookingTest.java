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

import javax.xml.ws.Response;


public class PostBookingTest extends BaseTest {
    PostBookingRequest postBookingRequest = new PostBookingRequest();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category(Acceptance.class)
    @DisplayName("Criar uma nova reserva")
    public void criarReserva(){
        postBookingRequest.criarReserva()
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Validar retorno 500 quando o payload da reserva estiver inválido")
    public void validarErroDeServidor(){
        postBookingRequest.payloadInvalido()
                .then()
                .assertThat()
                .statusCode(500);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Validar a criação de uma reserva em sequência")
    public void testCreatBookings(){
        postBookingRequest.payloadInvalido()
                .then()
                .assertThat()
                .statusCode(500);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Criar uma reserva enviando mais parâmetros no payload da reserva")
    public void criarReservaComMaisParametros(){
        postBookingRequest.reservaComMaisParametos()
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Validar retorno 418 quando o header Accept for invalido")
    public void testValidateTeapot(){
        postBookingRequest.headerAcceptInvalido()
                .then()
                .assertThat()
                .statusCode(418); //obs: adorei o código de erro 418 " I'm a teapot"
    }
}

