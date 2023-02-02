package hooks;

import io.cucumber.java.BeforeAll;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;

import java.util.HashMap;

public class Hooks {

    @BeforeAll
    public static void prepareData() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Content-type", "application/json");
        RestAssured.requestSpecification = new RequestSpecBuilder().addHeaders(headers).build();
    }
}
