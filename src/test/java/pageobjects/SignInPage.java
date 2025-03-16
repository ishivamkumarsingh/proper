package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.TimeoutException;

public class SignInPage {

    private WebDriver driver;

    // Example locators (update them to match the actual site: https://www.letskodeit.com/)
    private By loginLink = By.xpath("//*[@id=\"navbar-inverse-collapse\"]/div/div/a");
    private By emailField = By.xpath("//*[@id=\"email\"]");       // adjust as needed
    private By passwordField = By.xpath("//*[@id=\"login-password\"]"); // adjust as needed
    private By signInButton = By.xpath("//*[@id=\"login\"]");     // adjust as needed
    private By errorMessage = By.xpath("//*[@id=\"incorrectdetails\"]"); // example for negative test
    private By myCoursesHeader = By.xpath("//h1[contains(text(),'My Courses')]");
    public SignInPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }

    public void enterEmail(String email) {
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickSignInButton() {
        driver.findElement(signInButton).click();
    }

    // Positive/Negative check with one type of xPath or dynamic approach:
    public boolean isUserLoggedIn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(myCoursesHeader));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
    public String getErrorMessage() {
        // For negative scenario, we might get an error text
        WebElement error = driver.findElement(errorMessage);
        return error.getText();
    }
}
