package steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import pojo.Order;
import pojo.Pet;

import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static pojo.Order.orderBuild;
import static pojo.Pet.petBuild;

public class CommonSteps {

    Response response;
    Object responseBody;
    Object requestBody;
    Pet requestPetBody;
    Order requestOrderBody;

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
        Pet bodyGetId = (Pet) responseBody;
        requestPetBody.setId(bodyGetId.getId());  // записываем в ожидаемый результат действительный id
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

    @Когда("^выполнен POST запрос (.*) с валидными параметрами заказа")
    public void postOrder(String link, Map<String, String> params) throws ClassNotFoundException {
        requestOrderBody = orderBuild(params);  // записываем собранное тело перед отправкой
        response = given()
                .body(requestOrderBody)
                .post(link);
        responseBody = response.getBody().as(Class.forName("pojo.Order"));  // записываем тело ответа
        Order body = (Order) responseBody;
        requestOrderBody.setId(body.getId());  // записываем в ожидаемый результат действительный id
        requestBody = requestOrderBody;
    }

    @Когда("^выполнен POST запрос (.*) с невалидным значением параметра (.*)")
    public void postOrderWrongComplete(String link, String whatever, Map<String, String> params) {
        response = given()
                .body(orderBuild(params))
                .post(link);
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
                .delete(link + "/" +  id);
    }

    @Когда("^выполнен GET запрос (.*), для животного указан валидный id$")
    public void getPetId(String link) {
        Pet bodyGetId = (Pet) requestBody;
        String id = bodyGetId.getId();
        response = given()
                .get(link + "/" +  id);
    }

    @Когда("^выполнен DELETE запрос (.*) для удаления объекта, указан невалидный id (.*)$")
    public void deleteWithWrongId(String link, String id) {
        response = given()
                .delete(link + "/" +  id);
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
                    .get(link + "/" +  id);
    }
}