package steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CommonSteps {

    public static Response response;
    public static Object requestBody;
    public static Object responseBody;
    public static String id;

    @Тогда("^код ответа (\\d{3})$")
    public void getStatusCode(int number) {
        Assert.assertEquals(number, response.getStatusCode());
    }

    @Дано("^выполнен GET запрос (.*) с параметрами$")
    public void getWithParams(String link, Map<String, String> params) {
        response = given().params(params).get(link);
    }

    @Когда("^выполнен GET запрос (.*) id$")
    public void getId(String link) {
        response = given()
                .pathParams("id", id, "link", link)
                .get("/{link}/{id}");
    }

    @Когда("^выполнен DELETE запрос (.*) id$")
    public void delete(String link) {
        response = given()
                .pathParams("id", id, "link", link)
                .delete("/{link}/{id}");
    }

    @Когда("^выполнен DELETE запрос (.*) с невалидным значением параметра id = (.*{1})$")
    public void deleteWrongId(String link, String id) {
        response = given()
                .pathParams("id", id, "link", link)
                .delete("/{link}/{id}");
    }

    @Когда("id изменен на тот, которого нет в базе")
    public void changeId() {
        id = id + "1";
    }

    @Тогда("^тело ответа содержит отправленные параметры")
    public void responseBody() {
        Assert.assertEquals(requestBody, responseBody);
    }
}