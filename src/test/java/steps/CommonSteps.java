package steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class CommonSteps {

    Response response;

    public static String readFile(String path) throws IOException {
        return Files.readString(Paths.get(path));
    }

    @Дано("^выполнен GET запрос (.*) с параметром status = (.*)$")
    public void petList(String link, String status) {
        response = RestAssured.get(link + status)
                .andReturn();
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
    public void postPet(String link) throws IOException {
        String body = readFile("src/test/resources/files/postPetBody.md");
        response = given()
                .headers("Accept", "application/json"
                        , "Content-type", "application/json")
                .body(body)
                .post(link)
                .then().extract().response();
    }

    @Тогда("тело ответа содержит отправленные параметры")
    public void responseBody() throws IOException {
        String body = readFile("src/test/resources/files/getPetBody.md");
        String message = "Wrong body parameters!";
        Assert.assertTrue(message, response.getBody().asPrettyString().contains(body));
    }

    @Когда("^выполнен POST запрос (.*) с невалидным номером id$")
    public void postPetWrongId(String link) throws IOException {
        String body = readFile("src/test/resources/files/postPetWrongId.md");
        response = given()
                .headers("Accept", "application/json"
                        , "Content-type", "application/json")
                .body(body)
                .post(link)
                .then().extract().response();
    }
}
