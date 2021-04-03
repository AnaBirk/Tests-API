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

    GetBookingRequest getBookingRequest = new GetBookingRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Contract.class)
    @DisplayName("Garantir o contrato do retorno da lista de reservas")
    public void ensureAllBookingsContract() throws Exception{
        getBookingRequest.allBookings().then().log().all()
                .statusCode(200)
                .assertThat()
                .body(
                        matchesJsonSchema(
                                new File(
                                        Utils.getContractsBasePath("booking", "bookings")
                                )
                        )
                );
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Contract.class)
    @DisplayName("Garantir o contrato do retorno de uma reserva específica")
    public void ensureOneBookingContract() throws Exception{
        getBookingRequest.getOneBooking("2").then().log().all()
                .statusCode(200)
                .assertThat()
                .body(
                        matchesJsonSchema(
                                new File(
                                        Utils.getContractsBasePath("booking", "bookingId")
                                )
                        )
                );
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs das Reservas")
    public void allBookingsList() throws Exception {
        getBookingRequest.allBookings().then().log().all()
                .statusCode(200)
                .time(lessThan(3L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar uma reserva especifica")
    public void getOneBooking() throws Exception {
        getBookingRequest.getOneBooking("5").then().log().all()
                .statusCode(200)
                .time(lessThan(3L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs de reservas utilizando o filtro firstname")
    public void getBookingsByFirstnameFilter() throws Exception{
       getBookingRequest.firstNameFilter("Sally").then().log().all()
               .statusCode(200)
               .time(lessThan(3L), TimeUnit.SECONDS)
               .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs de reservas utilizando o filtro lastname")
    public void getBookingsByLastnameFilter() throws Exception{
        getBookingRequest.lastnameFilter("Brown").then().log().all()
                .statusCode(200)
                .time(lessThan(3L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs de reservas utilizando o filtro checkin")

    public void getBookingsByCheckinDateFilter() throws Exception{
        getBookingRequest.CheckinFilter("2013-02-23").then()
               .statusCode(200)
               .time(lessThan(3L), TimeUnit.SECONDS)
               .body("size()", greaterThan(0)); //ERRO 500
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs de reservas utilizando o filtro checkout")
    public void getBookingsByCheckoutDateFilter() throws Exception{
        getBookingRequest.checkoutFilter("2014-10-23").then()
                .statusCode(200)
                .time(lessThan(3L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0)); //ERRO 500
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs de reservas utilizando o filtro checkout and checkout")

    public void getBookingsByCheckoutAndCheckoutFilter() throws Exception{
        getBookingRequest.checkoutAndCheckoutFilter("2014-10-23").then()
                .statusCode(200)
                .time(lessThan(3L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0)); //JSON path size() doesn't match.
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs de reservas utilizando o filtro  name, checkin and checkout date")
    public void getBookingsByNameCheckinAndCheckoutFilter() throws Exception{
        getBookingRequest. nameCheckinAndCheckoutFilter("Sally", "Brown", "2013-02-23", "2014-10-23" ).then()
                .statusCode(200)
                .time(lessThan(3L), TimeUnit.SECONDS);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Visualizar erro de servidor 500 quando enviar filtro mal formatado")
    public void viewErrorByMisinformedFilter() throws Exception {
        getBookingRequest.checkoutFilter("filtro").then()
                .statusCode(200)
                .time(lessThan(3L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0)); //Erro 500
    }
}
