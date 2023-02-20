package steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.Assert;
import pojo.Pet;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static pojo.Pet.petBuild;
import static steps.CommonSteps.*;

public class PetSteps {

    Pet requestPetBody;
    Pet responsePetBody;
    ObjectMapper mapper = new ObjectMapper();
    Pet[] pets;

    @Когда("^выполнен POST запрос (.*) с параметрами животного$")
    public void postPet(String link, Map<String, String> params) {
        requestPetBody = petBuild(params);
        response = given()
                .basePath(link)
                .body(requestPetBody)
                .post();
        responsePetBody = response.getBody().as(Pet.class);
        id = responsePetBody.getId();
        requestPetBody.setId(responsePetBody.getId());
        responseBody = responsePetBody;
        responsePostBody = responsePetBody;
        requestBody = requestPetBody;
        requestPostBody = requestPetBody;
    }

    @Когда("^выполнен PUT запрос (.*) с параметрами животного$")
    public void putPetParameters(String link, Map<String, String> params) {
        id = responsePetBody.getId();
        requestPetBody = petBuild(params);
        requestPetBody.setId(id);  // собранному телу запроса задаем актуальное значение id
        response = given()
                .basePath(link)
                .body(requestPetBody)
                .put();
        responsePetBody = response.getBody().as(Pet.class);
        responseBody = responsePetBody;
        requestBody = requestPetBody;
    }

    @Когда("^выполнен PUT запрос (.*) с невалидными параметрами животного$")
    public void putPetWrongParameters(String link, Map<String, String> params) {
        id = responsePetBody.getId();
        requestPetBody = petBuild(params);
        requestPetBody.setId(id);  // собранному телу запроса задаем актуальное значение id
        response = given()
                .basePath(link)
                .body(requestPetBody)
                .put();
    }

    @Когда("^выполнен PUT запрос (.*) с параметрами животного, задано невалидное значение параметра id$")
    public void putPetWrongIdParameters(String link, Map<String, String> params) {
        requestPetBody = petBuild(params);
        response = given()
                .basePath(link)
                .body(requestPetBody)
                .put();
    }

    @Когда("^выполнен GET запрос (.*) с параметром id животного$")
    public void getPetId(String link) {
        response = given()
                .basePath(link + "/" + id)
                .get();
        responsePetBody = response.getBody().as(Pet.class);
        responseBody = responsePetBody;
    }

    @Дано("^выполнен GET запрос (.*) с параметрами$")
    public void getWithParams(String link, Map<String, String> params) throws IOException {
        response = given()
                .params(params)
                .get(link);
        pets = mapper.readValue(response.getBody().asPrettyString(), Pet[].class);
    }

    @Тогда("^тело ответа включает отправленные параметры")
    public void responseContainsBody() {
        Assert.assertTrue(Arrays.stream(pets).anyMatch(pet -> pet.equals(responseBody)));
    }
}