package br.com.restassuredapitesting.runners;

import br.com.restassuredapitesting.tests.base.tests.BaseTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//@RunWith - > quremos executar com o cattegories do Allure, é uma categorização do Allure
//Dessas categorizações de teste só queremos a categoria(suíte) Alltests, iue é a interface que está dentro de suites

@RunWith(Categories.class) // quer dizer que queremos executar com o Categories do Allure
@Categories.IncludeCategory(br.com.restassuredapitesting.suites.AllTests.class)//Só vamos querer

@Suite.SuiteClasses({
        //Dentro do suites dizemos quais as classes que queremos
        BaseTest.class
})
public class AllTests {
    //Posso colocar meus runnes aqui?
}
