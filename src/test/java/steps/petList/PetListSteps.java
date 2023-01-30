package steps.petList;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Тогда;

public class PetListSteps {

    String link = "https://petstore.swagger.io/v2/pet/findByStatus?status=";
    Response response;

    @Дано("выполнен GET запрос \\/pet\\/findPetsByStatus с параметром status = {string}")
    public void petList(String status) {
        response = RestAssured.get(link + status)
                .andReturn();
    }

    @Тогда("код ответа {int}")
    public void getStatus(int number) {
        String message = "expecting " + number + ", but it was " + response.getStatusCode();
        Assert.assertEquals(message, number, response.getStatusCode());
    }

    @Тогда("тело ответа содержит параметр status = {string}")
    public void getParam(String status) {
        String message = "pet in status: \"" + status + "\" not found";
        Assert.assertTrue(message, response.getBody().asPrettyString().contains(status));

    }

}
