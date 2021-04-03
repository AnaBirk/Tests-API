package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.runners.Acceptance;
import br.com.restassuredapitesting.suites.Contract;
import br.com.restassuredapitesting.suites.E2e;
import br.com.restassuredapitesting.tests.base.tests.BaseTest;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import br.com.restassuredapitesting.utils.Utils;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import java.io.File;
import java.util.concurrent.TimeUnit;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

@Feature("Reservas")
public class GetBookignTest extends BaseTest {
    //Criar um novo objeto de requisição
    GetBookingRequest getBookingRequest = new GetBookingRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs das Reservas")

    public void validarIdsDasReservas() throws Exception {
        getBookingRequest.allBookings().then().log().all()
                //listar ids das reservas - validar o statusCode
                .statusCode(200)
                //Depois de verificar se o statusCode é 200 ele verifica se o tempo é menor que 3s
                .time(lessThan(3L), TimeUnit.SECONDS)
                //Se o tempo for menor que 3s vamos verificar no retorno, o corpo da requisição
                //O tamanho tem que ser maior que 0
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar uma reserva especifica")
    public void validarIdEspecifico() throws Exception {
        getBookingRequest.reservaEspecifica("5").then().log().all()
                .statusCode(200)
                .time(lessThan(3L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs de reservas utilizando o filtro firstname")
    public void validarIdsComFristName() throws Exception{
       getBookingRequest.allBookingsUsingFirstName("Sally").then().log().all()
               .statusCode(200)
               .time(lessThan(3L), TimeUnit.SECONDS)
               .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs de reservas utilizando o filtro lastname")
    public void validarIdsComLastname() throws Exception{
        getBookingRequest.allBookingsUsingLastname("Brown").then().log().all()
                .statusCode(200)
                .time(lessThan(3L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs de reservas utilizando o filtro checkin")

    public void validarIdsDasReservasUsandoCheckinDate() throws Exception{
        getBookingRequest.allBookingsFilterByCheckin("2013-02-23").then()
               .statusCode(200)
               .time(lessThan(3L), TimeUnit.SECONDS)
               .body("size()", greaterThan(0)); //ERRO 500
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs de reservas utilizando o filtro checkout")
    public void validarIdsDasReservasUsandoCheckoutDate() throws Exception{
        getBookingRequest.allBookingsUsingCheckout("2014-10-23").then()
                .statusCode(200)
                .time(lessThan(3L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0)); //ERRO 500
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs de reservas utilizando o filtro checkout and checkout")

    public void validarIdsDasReservasUsandoCheckoutAndCheckout() throws Exception{
        getBookingRequest.allBookingsFilterByCheckoutAndCheckout("2014-10-23").then()
                .statusCode(200)
                .time(lessThan(3L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0)); //JSON path size() doesn't match.
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs de reservas utilizando o filtro  name, checkin and checkout date")
    public void validarIdsDasReservasUsandoCheckoutCheckinAndName() throws Exception{
        getBookingRequest.allBookingsFilterByNameAndCheckinAndCheckout("Sally", "Brown", "2013-02-23", "2014-10-23" ).then()
                .statusCode(200)
                .time(lessThan(3L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0)); //JSON path size() doesn't match.
        // Expected: a value greater than <0>
        // Actual: 0
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Visualizar erro de servidor 500 quando enviar filtro mal formatado")
    public void visualizerErroComFiltroMalInformado() throws Exception {
        getBookingRequest.allBookingsUsingCheckout("filtro").then()
                .statusCode(200)
                .time(lessThan(3L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0)); //Erro 500
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category(Contract.class) // é a nossa interface dentro de suíte
    @DisplayName("Garantir o contrato do retorno da lista de reservas")
    public void garantirContratoListaReserva() throws Exception{
        getBookingRequest.allBookings().then().log().all()
                .statusCode(200)//é 200, se for:
                .assertThat()//faz um assert disso
                .body(
                    matchesJsonSchema(
                            new File(
                                    Utils.getContractsBasePath("booking", "bookings")
                            )
                    )
                );
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category(Contract.class)
    @DisplayName("Garantir o contrato do retorno de uma reserva específica")
    public void garantirContratoReservaEspecifica() throws Exception{
        getBookingRequest.reservaEspecifica("2").then().log().all()
                .statusCode(200)
                .assertThat()
                .body(
                        matchesJsonSchema(
                                new File(
                                        Utils.getContractsBasePath("booking", "bookingMarkJackson")
                                )
                        )
                );
    }
}
