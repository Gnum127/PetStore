package steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.restassured.response.Response;
import org.junit.Assert;
import pojo.Pet;

import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;
import static pojo.Pet.petBuild;
import static pojo.Pet.petResponseParam;

public class PetSteps {

    Response response;
    Pet responseBody;
    Pet requestBody;
    Map<String, String> petParams;

    @Дано("^выполнен GET запрос (.*) с параметрами$")
    public void getPetList(String link, Map<String, String> params) {
        response = given().with().params(params).get(link);
    }

    @Тогда("^тело ответа содержит параметры животного$")
    public void responseWithParams() {
        String message = "param was wrong";
        String expecting;
        String key;
        String value;
        ArrayList <String> keys = new ArrayList<>(petParams.keySet());
        for (String o : keys) {
            key = o;
            value = petParams.get(key);
            key = petResponseParam(key);
            Pattern pattern = Pattern.compile("[0-9]+");
            if (value.matches(pattern.toString())) {
                expecting = key + "\": " + value;
            } else expecting = key + "\": \"" + value;
            if (key.equals("photoUrls")) {
                expecting = key + "\": [\n" +
                        "            \"" + value;
            }  // в случае изменения тела ответа условие не выполнится и проверка уйдет в ошибку
            Assert.assertTrue(message, response.getBody().asPrettyString().contains(expecting));
        }
    }

    @Когда("^выполнен POST запрос (.*) с параметрами животного$")
    public void postPet(String link, Map<String, String> params) {
        requestBody = petBuild(params);
        response = given()
                .body(requestBody)
                .post(link);
        petParams = params;
    }

    @Тогда("^тело ответа содержит отправленные параметры$")
    public void responseBody() throws ClassNotFoundException {
        responseBody = (Pet) response.getBody().as(Class.forName("pojo.Pet"));
        requestBody.setId(responseBody.getId());
        System.out.println(responseBody.toString());
        Assert.assertEquals(requestBody, responseBody);
    }

    @Тогда("^Pet код ответа (\\d*)$")
    public void getStatusCode(int number) {
        Assert.assertEquals(number, response.getStatusCode());
    }
}
