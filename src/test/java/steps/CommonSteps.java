package steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.restassured.response.Response;
import org.junit.Assert;
import pojo.Order;
import pojo.Pet;

import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;
import static pojo.Pet.petBuild;
import static pojo.Pet.petResponseParam;

public class CommonSteps {

    Response response;
    Object responseBody;
    Object requestBody;
    Pet requestPetBody;
    Order requestOrderBody;


    @Тогда("^код ответа (\\d*)$")
    public void getStatusCode(int number) {
        Assert.assertEquals(number, response.getStatusCode());
    }

    @Когда("^выполнен PUT запрос (.*) для внесения изменений в информацию о животном$")
    public void putPetParameters(String link, Map<String, String> params) throws ClassNotFoundException {
        requestPetBody = petBuild(params);  // собираем новое тело
        Pet bodyGetId = (Pet) requestBody;
        requestPetBody.setId(bodyGetId.getId());  // собранному телу задаем актуальное значение id
        response = given()
                .body(requestPetBody)
                .put(link);
        responseBody = response.getBody().as(Class.forName("pojo.Pet"));
        requestBody = requestPetBody;
    }

    @Когда("^выполнен DELETE запрос (.*) для удаления животного, указан валидный id$")
    public void deletePet(String link) {
        Pet bodyGetId = (Pet) requestBody;
        String id = bodyGetId.getId();
        response = given()
                .delete(link + "/" + id);
    }

    @Когда("^выполнен GET запрос (.*), для животного указан валидный id$")
    public void getPetId(String link) {
        Pet bodyGetId = (Pet) requestBody;
        String id = bodyGetId.getId();
        response = given()
                .get(link + "/" + id);
    }

    @Когда("^выполнен DELETE запрос (.*) для удаления объекта, указан невалидный id (.*)$")
    public void deleteWithWrongId(String link, String id) {
        response = given()
                .delete(link + "/" + id);
    }

    @Когда("^выполнен DELETE запрос (.*) для удаления заказа, указан валидный id$")
    public void deleteOrder(String link) {
        Order bodyGetId = (Order) requestBody;
        String id = bodyGetId.getId();
        response = given()
                .delete(link + "/" + id);
    }

    @Когда("^выполнен GET запрос (.*), для заказа указан валидный id$")
    public void getOrderId(String link) {
        Order bodyGetId = (Order) requestBody;
        String id = bodyGetId.getId();
        response = given()
                .get(link + "/" + id);
    }
}