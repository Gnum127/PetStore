package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
    @Before
    public void prepareData() {
        // действия перед каждым тестом
    }

    @After
    public void clearData() {
        // действия после каждого теста
    }
}
