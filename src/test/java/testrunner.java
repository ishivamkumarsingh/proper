import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = {
        "src/test/resources/features/Signin.feature",
        "src/test/resources/features/coursesearch.feature",
        "src/test/resources/features/headerverification.feature",
        "src/test/resources/features/practicepageelements.feature",
        "src/test/resources/features/advanceinteraction.feature"
    },
    glue = {"stepdefinition", "utility"}, 
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
    // No additional implementation needed as we're using the parent class methods
}