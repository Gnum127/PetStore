package steps;

import io.cucumber.java.ru.Когда;
import pojo.Pet;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static pojo.Pet.petBuild;
import static steps.CommonSteps.response;
import static steps.CommonSteps.requestBody;
import static steps.CommonSteps.responseBody;
import static steps.CommonSteps.id;

public class PetSteps {

    Pet requestPetBody;
    Pet responsePetBody;

    @Когда("^выполнен POST запрос (.*) с параметрами животного$")
    public void postPet(String link, Map<String, String> params) {
        requestPetBody = petBuild(params);
        response = given()
                .body(requestPetBody)
                .post(link);
        responsePetBody = response.getBody().as(Pet.class);
        id = responsePetBody.getId();
        requestPetBody.setId(responsePetBody.getId());
        responseBody = responsePetBody;
        requestBody = requestPetBody;
    }

    @Когда("^выполнен PUT запрос (.*) с параметрами животного$")
    public void putPetParameters(String link, Map<String, String> params) {
        id = responsePetBody.getId();
        requestPetBody = petBuild(params);
        requestPetBody.setId(id);  // собранному телу запроса задаем актуальное значение id
        response = given()
                .body(requestPetBody)
                .put(link);
        responsePetBody = response.getBody().as(Pet.class);
        responseBody = responsePetBody;
        requestBody = requestPetBody;
    }

    @Когда("^выполнен PUT запрос (.*) с параметрами животного, задано невалидное значение параметра id$")
    public void putPetWrongIdParameters(String link, Map<String, String> params) {
        requestPetBody = petBuild(params);
        response = given()
                .body(requestPetBody)
                .put(link);
    }
}
