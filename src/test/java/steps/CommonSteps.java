package steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import pojo.Pet;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static pojo.Pet.petBuild;

public class CommonSteps {

    Response response;
    Pet requestBody;
    Object responseBody;

    @Дано("^выполнен GET запрос (.*) с параметром status = (.*)$")
    public void petList(String link, String status) {
        response = RestAssured.get(link + status).andReturn();
    }

    @Тогда("^код ответа (\\d*)$")
    public void getStatus(int number) {
        String message = "expecting " + number + ", but it was " + response.getStatusCode();
        Assert.assertEquals(message, number, response.getStatusCode());
    }

    @Тогда("^тело ответа содержит параметр status = (.*)$")
    public void getParam(String status) {
        String message = "pet in status: \"" + status + "\" not found";
        Assert.assertTrue(message, response.getBody().asPrettyString().contains(status));
    }

    @Когда("^выполнен POST запрос (.*) с валидными параметрами$")
    public void postPet(String link, Map<String, String> params) throws ClassNotFoundException {
        requestBody = petBuild(params);

        response = given()
                .body(requestBody)
                .post(link);
        responseBody = response.getBody().as(Class.forName("pojo.Pet"));
    }

    @Тогда("^тело ответа содержит отправленные параметры$")
    public void responseBody() {
        String message = "Wrong body";
        Assert.assertEquals(message, requestBody, responseBody);
    }

    @Когда("^выполнен POST запрос (.*) с невалидным номером id$")
    public void postPetWrongId(String link, Map<String, String> params) {
        response = given()
                .body(petBuild(params))
                .post(link);
    }
}
