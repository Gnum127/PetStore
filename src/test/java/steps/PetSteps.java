package steps;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.Assert;
import pojo.Pet;

import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;
import static pojo.Pet.petBuild;
import static pojo.Pet.petResponseParam;
import static steps.CommonSteps.response;
import static steps.CommonSteps.requestBody;
import static steps.CommonSteps.responseBody;
import static steps.CommonSteps.id;

public class PetSteps {
    Pet requestPetBody;
    Pet responsePetBody;
    Map<String, String> petParams;

    @Тогда("^тело ответа содержит параметры животного$")
    public void responseWithParams() {
        String message = "param was wrong";
        String expecting;
        String key;
        String value;
        ArrayList<String> keys = new ArrayList<>(petParams.keySet());
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
        requestPetBody = petBuild(params);
        response = given()
                .body(requestPetBody)
                .post(link);
        petParams = params;  // возможно стоит вообще убрать эту мапу
        responsePetBody = response.getBody().as(Pet.class);
        id = responsePetBody.getId();
    }

    @Когда("^выполнен PUT запрос (.*) с параметрами животного$")
    public void putPetParameters(String link, Map<String, String> params) {
        id = responsePetBody.getId();
        requestPetBody = petBuild(params);
        requestPetBody.setId(id);  // собранному телу запроса задаем актуальное значение id
        response = given()
                .body(requestPetBody)
                .put(link);
        petParams = params; // возможно стоит вообще убрать эту мапу
        responsePetBody = response.getBody().as(Pet.class);
    }

    @Когда("^выполнен PUT запрос (.*) с параметрами животного, задано невалидное значение параметра id$")
    public void putPetWrongIdParameters(String link, Map<String, String> params) {
        requestPetBody = petBuild(params);
        response = given()
                .body(requestPetBody)
                .put(link);
        petParams = params; // возможно стоит вообще убрать эту мапу
    }

    @Тогда("^тело ответа содержит отправленные параметры животного$")
    public void responseBody() {
        requestPetBody.setId(responsePetBody.getId());
        Assert.assertEquals(requestPetBody, responsePetBody);
    }
}
