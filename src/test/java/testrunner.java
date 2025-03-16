import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = {
        "src/test/resources/features/SignIn.feature",
        "src/test/resources/features/CourseSearch.feature"
    },
    glue = {"stepdefinition", "utility", "pageobjects"}, 
    plugin = {
        "pretty",
        "html:target/cucumber-html-report.html",
        "json:target/cucumber-report.json",
        // Extent Reports adapter
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
        // Allure adapter
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
    },
    monochrome = true
)
public class testrunner extends AbstractTestNGCucumberTests {
    // We do not need to add anything else here unless you want 
    // to customize data providers or hooks for parallel execution.
}