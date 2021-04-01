package br.com.restassuredapitesting.tests.auth.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;

public class PostAuthRequest {

    @Step("Buscar o token")
    public Response token(){
        JSONObject payload = new JSONObject();
        payload.put("username", "admin");
        payload.put("password", "password123");

        return given()
                .header("Content-Type", "application/json")
                .when()
                //Gerar o objeto Body
                .body(payload.toString())
                .post("auth");
    }

    //Método auxiliar para pegar somente o token, pois o de cima não pode fazer isso
    @Step("Retornar o token")
    public String getToken(){
    //Do retorno 200 só quero extrair do body o retorno Token
        return "token= "+ this.token().then().statusCode(200).extract().path("token"); //Autenticação
    }

}
