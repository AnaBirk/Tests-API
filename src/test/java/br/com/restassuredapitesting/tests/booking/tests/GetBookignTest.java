package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.runners.Acceptance;
import br.com.restassuredapitesting.suites.Contract;
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
    //A severidade
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    //O nome do cenário
    @DisplayName("Listar IDs das Reservas")

    public void validarIdsDasReservas() throws Exception {
        getBookingRequest.allBookings().then()
                //listar ids das reservas - validar o statusCode
                .statusCode(200)
                //Depois de verificar se o statusCode é 200 ele verifica se o tempo é menor que 3s
                .time(lessThan(3L), TimeUnit.SECONDS)
                //Se o tempo for menor que 3s vamos verificar no retorno, o corpo da requisição
                //O tamanho tem que ser maior que 0
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category(Contract.class) // é a nossa interface dentro de suíte
    @DisplayName("Garantir o contrato do retorno da lista de reservas")
    public void ganatirContratoListaReserva() throws Exception{
        getBookingRequest.allBookings().then()
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
}
