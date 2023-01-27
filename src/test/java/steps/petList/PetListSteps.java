package steps.petList;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Тогда;

public class PetListSteps {

    String link = "https://petstore.swagger.io/v2/pet/findByStatus?status=";
    Response response;

    @Дано("животные в статусе {string}")
    public void petList(String status) {
        response = RestAssured.get(link + status)
                .andReturn();
    }

    @Тогда("статус ответа {int}")
    public void getStatus(int number) {
        if (!(number == response.getStatusCode())) {
            Assert.fail("expecting " + number + ", but it was " + response.getStatusCode());
        }
    }

    @Тогда("тело ответа содержит {string}")
    public void getParam(String category) {
        if (!response.getBody().asPrettyString().contains(category)){
            Assert.fail("response body is:\n" + response.getBody().prettyPrint());
        }

    }

}
