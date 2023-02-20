package hooks;

import io.cucumber.java.BeforeAll;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;

public class Hooks {

    @BeforeAll
    public static void prepareData() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Content-type", "application/json")
                .build();
    }
}
