import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"src.test.java.steps", "src.test.java.hooks"},
        tags = "@all"
)
public class RunCucumberTest {
}
