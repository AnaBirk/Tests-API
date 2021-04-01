package br.com.restassuredapitesting.runners;


import br.com.restassuredapitesting.tests.ping.tests.GetPingTests;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(br.com.restassuredapitesting.suites.Healthcheck.class)
@Suite.SuiteClasses({
        GetPingTests.class
})

public class Healthcheck {
}
