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