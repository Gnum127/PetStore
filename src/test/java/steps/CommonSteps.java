package steps;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.restassured.response.Response;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class CommonSteps {

    public static Response response;
    public static Object requestBody;
    public static Object requestPostBody;
    public static Object responseBody;
    public static Object responsePostBody;
    public static String id;

    @Тогда("^код ответа (\\d{3})$")
    public void getStatusCode(int number) {
        Assert.assertEquals(number, response.getStatusCode());
    }

    @Когда("^выполнен DELETE запрос (.*) id$")
    public void delete(String link) {
        response = given()
                .basePath(link + "/" + id)
                .delete();
    }

    @Когда("^выполнен DELETE запрос (.*) с невалидным значением параметра id = (.*{1})$")
    public void deleteWrongId(String link, String id) {
        response = given()
                .basePath(link + "/" + id)
                .delete();
    }

    @Когда("id изменен на тот, которого нет в базе")
    public void changeId() {
        id = id + "1";
    }

    @Тогда("^тело ответа содержит отправленные параметры")
    public void responseBody() {
        Assert.assertEquals(requestBody, responseBody);
    }

    @Тогда("^тело ответа содержит отправленные в POST запросе параметры")
    public void responsePostBody() {
        Assert.assertEquals(requestPostBody, responsePostBody);
    }
}