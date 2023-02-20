package steps;

import io.cucumber.java.ru.Когда;
import pojo.Order;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static pojo.Order.orderBuild;
import static steps.CommonSteps.response;
import static steps.CommonSteps.requestBody;
import static steps.CommonSteps.responseBody;
import static steps.CommonSteps.id;

public class OrderSteps {

    Order requestOrderBody;
    Order responseOrderBody;

    @Когда("^выполнен POST запрос (.*) с параметрами заказа")
    public void postOrder(String link, Map<String, String> params) {
        requestOrderBody = orderBuild(params);
        response = given()
                .basePath(link)
                .body(requestOrderBody)
                .post();
        responseOrderBody = response.getBody().as(Order.class);
        id = responseOrderBody.getId();
        requestOrderBody.setId(responseOrderBody.getId());
        responseBody = responseOrderBody;
        requestBody = requestOrderBody;
    }

    @Когда("^выполнен GET запрос (.*) с параметром id заказа")
    public void getOrderId(String link) {
        response = given()
                .basePath(link + "/" + id)
                .get();
        responseOrderBody = response.getBody().as(Order.class);
        responseBody = responseOrderBody;
    }
}