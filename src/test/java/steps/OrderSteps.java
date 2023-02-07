package steps;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.restassured.response.Response;
import org.junit.Assert;
import pojo.Order;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static pojo.Order.orderBuild;

public class OrderSteps {

    Response response;
    Order responseBody;
    Order requestBody;

    @Когда("^выполнен POST запрос (.*) с параметрами заказа")
    public void postOrder(String link, Map<String, String> params) throws ClassNotFoundException {
        requestBody = orderBuild(params);
        response = given()
                .body(requestBody)
                .post(link);
        responseBody = (Order) response.getBody().as(Class.forName("pojo.Order"));
    }

    @Тогда("^Order код ответа (\\d*)$")
    public void getStatusCode(int number) {
        Assert.assertEquals(number, response.getStatusCode());
    }

    @Тогда("^тело ответа содержит отправленные параметры заказа$")
    public void responseBody() {
        requestBody.setId(responseBody.getId());
        Assert.assertEquals(requestBody, responseBody);
    }

    @Когда("^Order выполнен DELETE запрос (.*)id$")
    public void deletePet(String link) {
        link = link + responseBody.getId();
        response = given().delete(link);
    }

    @Когда("^Order выполнен GET запрос (.*)id$")
    public void getId(String link) {
        link = link + requestBody.getId();
        response = given().with().get(link);
    }

    @Когда("Order id изменен на тот, которого нет в базе")
    public void changeId() {
        requestBody.setId(responseBody.getId() + "1");
    }

    @Когда("Order id изменен на невалидный")
    public void changeIdWrong() {
        requestBody.setId(responseBody.getId() + "q");
    }
}
