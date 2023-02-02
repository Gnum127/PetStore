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
    Object responseBody;
    Object requestBody;
    Pet requestPetBody;

    @Дано("^выполнен GET запрос (.*) с параметром status = (.*)$")
    public void getPetList(String link, String status) {
        response = RestAssured.get(link + status);
    }

    @Тогда("^код ответа (\\d*)$")
    public void getStatusCode(int number) {
        Assert.assertEquals(number, response.getStatusCode());
    }

    @Тогда("^тело ответа содержит параметр status = (.*)$")
    public void getParamList(String status) {
        String message = "pet in status: \"" + status + "\" not found";
        Assert.assertTrue(message, response.getBody().asPrettyString().contains(status));
    }

    @Когда("^выполнен POST запрос (.*) с валидными параметрами животного$")
    public void postPet(String link, Map<String, String> params) throws ClassNotFoundException {
        requestPetBody = petBuild(params);  // записываем собранное тело перед отправкой
        response = given()
                .body(requestPetBody)
                .post(link);
        responseBody = response.getBody().as(Class.forName("pojo.Pet"));  // записываем тело ответа
        Pet body = (Pet) responseBody;
        requestPetBody.setId(body.getId());  // записываем в ожидаемый результат действительный id
        requestBody = requestPetBody;
    }

    @Тогда("^тело ответа содержит отправленные параметры$")
    public void responseBody() {
        Assert.assertEquals(requestBody, responseBody);
    }

    @Когда("^выполнен POST запрос (.*) с невалидным значением id животного$")
    public void postPetWrongId(String link, Map<String, String> params) {
        response = given()
                .body(petBuild(params))
                .post(link);
    }
}