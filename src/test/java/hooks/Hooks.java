package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.restassured.RestAssured;

public class Hooks {
    @BeforeAll
    public static void prepareData() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @After
    public void clearData() {
        // действия после каждого теста
    }
}
