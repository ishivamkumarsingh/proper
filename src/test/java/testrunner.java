import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = {
        "src/test/resources/features/SignIn.feature",
        "src/test/resources/features/CourseSearch.feature",
        "src/test/resources/features/HeaderVerification.feature",
        "src/test/resources/features/PracticePageFormElements.feature",
        "src/test/resources/features/AdvancedInteractions.feature"
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
    // We do not nee
}