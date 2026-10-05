package runners;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "org.sndivad.selenium.steps",
        plugin = {"pretty", "html:target/cucumber-report.html"}
)

public class TestRunner extends AbstractTestNGCucumberTests{

//    @BeforeClass
//    public void setup() throws Exception {
//        // Start driver before running tests
//        DriverManager.getDriver();
//    }
//
//    @AfterClass
//    public void teardown() {
//        // Quit driver after running tests
//        DriverManager.quitDriver();
//    }
}
