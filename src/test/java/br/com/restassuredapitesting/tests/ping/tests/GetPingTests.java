package br.com.restassuredapitesting.tests.ping.tests;

import br.com.restassuredapitesting.suites.Healthcheck;
import br.com.restassuredapitesting.tests.base.tests.BaseTest;
import br.com.restassuredapitesting.tests.ping.requests.GetPingRequests;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import java.util.concurrent.TimeUnit;
import static org.hamcrest.Matchers.lessThan;


public class GetPingTests extends BaseTest {

    GetPingRequests getPingRequests = new GetPingRequests();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Healthcheck.class)
    @DisplayName("Verificar se API está online")
    public void testPing() throws Exception {
        getPingRequests.getPing().then()
                .statusCode(201)
                .time(lessThan(3L), TimeUnit.SECONDS);
    }
}
