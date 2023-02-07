package steps;

import io.cucumber.java.ru.Тогда;
import io.restassured.response.Response;
import org.junit.Assert;

public class CommonSteps {

    Response response;

    @Тогда("^код ответа (\\d*)$")
    public void getStatusCode(int number) {
        Assert.assertEquals(number, response.getStatusCode());
    }

}