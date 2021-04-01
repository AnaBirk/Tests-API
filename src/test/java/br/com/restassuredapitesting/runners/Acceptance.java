package br.com.restassuredapitesting.runners;


import br.com.restassuredapitesting.tests.auth.tests.PostAuthTest;
import br.com.restassuredapitesting.tests.booking.tests.GetBookignTest;
import br.com.restassuredapitesting.tests.booking.tests.PutBookingTest;
import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)//é do próprio JUnit, serve para criar um esquema de categorias
@Categories.IncludeCategory(br.com.restassuredapitesting.suites.Acceptance.class)//Incluir essas categorias(que é a)
@Suite.SuiteClasses({
        GetBookignTest.class,
        PutBookingTest.class,
        PostAuthTest.class
})
public class Acceptance {
}
