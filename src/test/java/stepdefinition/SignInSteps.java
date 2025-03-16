package stepdefinition;

import io.cucumber.java.en.*;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;

import pageobjects.SignInPage;
import utility.BaseClass;
import utility.Log4jConfig;
import utility.ScreenshotUtil;

import java.util.Set;

public class SignInSteps extends BaseClass {

    SignInPage signInPage;

    @Given("I open the application home page")
    public void i_open_the_application_home_page() {
        Log4jConfig.info("Launching browser and navigating to Let's Kode It...");
        driver = getDriver(); // from BaseClass
        driver.get("https://www.letskodeit.com/"); // Directly using URL, skipping config.properties
        signInPage = new SignInPage(driver);
        ScreenshotUtil.captureScreenshotReturnPath(driver, "HomePage"); // capture screenshot for each step
    }

    @When("I click on the {string} link")
    public void i_click_on_the_link(String linkText) {
        Log4jConfig.info("Clicking on link: " + linkText);
        signInPage.clickLoginLink();
        ScreenshotUtil.captureScreenshotReturnPath(driver, "ClickLoginLink");
    }

    @When("I enter username {string} and password {string}")
    public void i_enter_username_and_password(String username, String password) {
        Log4jConfig.info("Entering credentials: " + username + " / " + password);
        signInPage.enterEmail(username);
        signInPage.enterPassword(password);
        signInPage.clickSignInButton();

        // Example: If the site spawns a new window, handle it:
        handleMultipleWindowsIfAny();

        // Example: If the site triggers an alert, handle it:
        handleAlertIfPresent();

        ScreenshotUtil.captureScreenshotReturnPath(driver, "AfterEnteringCredentials");
    }

    @Then("I should see a {string} login outcome")
    public void i_should_see_a_login_outcome(String expectedOutcome) {
        Log4jConfig.info("Verifying login outcome: " + expectedOutcome);

        boolean isLoggedIn = signInPage.isUserLoggedIn();

        if (expectedOutcome.equalsIgnoreCase("success")) {
            Assert.assertTrue(isLoggedIn, "Expected user to be logged in, but they are not.");
            Log4jConfig.info("Login success scenario verified.");
        } else if (expectedOutcome.equalsIgnoreCase("failure")) {
            Assert.assertFalse(isLoggedIn, "Expected user to NOT be logged in, but they are.");
            // Optionally verify an error message
            String err = signInPage.getErrorMessage();
            Log4jConfig.info("Error message displayed: " + err);
        } else {
            Assert.fail("Unexpected outcome parameter: " + expectedOutcome);
        }

        ScreenshotUtil.captureScreenshotReturnPath(driver, "FinalOutcome_" + expectedOutcome);
    }

    // Utility method to handle unexpected alerts
    private void handleAlertIfPresent() {
        try {
            driver.switchTo().alert().accept();
            Log4jConfig.info("Alert found and accepted.");
        } catch (NoAlertPresentException e) {
            // No alert
        }
    }

    // Utility method to handle multiple windows if they appear
    private void handleMultipleWindowsIfAny() {
        String mainWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();

        for (String handle : allWindows) {
            if (!handle.equals(mainWindow)) {
                driver.switchTo().window(handle);
                Log4jConfig.info("Switched to a new window: " + handle);

                // If you want to close it:
                // driver.close();
                // driver.switchTo().window(mainWindow);
            }
        }
    }
}
