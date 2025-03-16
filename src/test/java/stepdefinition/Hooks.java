package stepdefinition;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;

import com.relevantcodes.extentreports.LogStatus;

import utility.ReportGenerator;
import utility.ScreenshotUtil;
import utility.BaseClass;
import org.openqa.selenium.WebDriver;

public class Hooks {

    private WebDriver driver;

    @Before
    public void beforeScenario(Scenario scenario) {
        // Ensure the Extent report is initialized only once
        ReportGenerator.initReport();

        // Start a new Extent test for each scenario
        ReportGenerator.startTest(scenario.getName());

        // If you want a driver instance started here:
        driver = BaseClass.getDriver(); 
    }

    @After
    public void afterScenario(Scenario scenario) {
        // Check scenario status
        if (scenario.isFailed()) {
            // Mark as failed in the report
            ReportGenerator.test.log(LogStatus.FAIL, "Scenario Failed: " + scenario.getName());

            // Optionally attach a screenshot path if you want
            String screenshotPath = ScreenshotUtil.captureScreenshotReturnPath(driver, scenario.getName());
            if (screenshotPath != null) {
                // Attach screenshot in Extent
                ReportGenerator.test.log(LogStatus.FAIL, 
                    "Screenshot: " + ReportGenerator.test.addScreenCapture(screenshotPath));
            }
        } else {
            // Mark as passed
            ReportGenerator.test.log(LogStatus.PASS, "Scenario Passed: " + scenario.getName());
        }

        // End test
        ReportGenerator.endTest();

        // Flush after each scenario to update the report
        ReportGenerator.flushReport();
    }
}
