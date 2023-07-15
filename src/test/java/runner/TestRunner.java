package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/CurrentWeather.feature",
        glue ={"stepDefinition"},
        plugin = {
                "pretty",
                "html:target/html/cucumber-reports.html",
        },
        monochrome = true


)
public class TestRunner {

}
