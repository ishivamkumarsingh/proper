package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EMICalculatorPage {
    private WebDriver driver;

    // Locators
    private By homeLoanTab = By.xpath("//a[text()='Home Loan']");
    private By loanAmountSlider = By.id("loanamountslider");
    private By interestRateSlider = By.id("loaninterestslider");
    private By loanTenureSlider = By.id("loantermslider");
    private By emiResult = By.id("emiamount");
    private By totalInterestResult = By.id("loaninterest");
    private By totalPaymentResult = By.id("loantotal");

//    private By homeLoanTab = By.xpath("//a[contains(text(),'Home Loan EMI Calculator')]");
    private By interestRateField = By.id("loaninterest");

   

    // Navigate to Home Loan EMI Section
    public void navigateToHomeLoanEMI() {
        driver.findElement(homeLoanTab).click();
    }

    // Enter Interest Rate
    public void enterInterestRate(int rate) {
        WebElement rateField = driver.findElement(interestRateField);
        rateField.clear();
        rateField.sendKeys(String.valueOf(rate));
    }
    // Constructor
       public EMICalculatorPage(WebDriver driver) {
        this.driver = driver;
    }

    // Select Loan Type
    public void selectLoanType(String loanType) {
        if (loanType.equalsIgnoreCase("Home Loan")) {
            driver.findElement(homeLoanTab).click();
        }
    }
    private By loanAmountField = By.id("loanamount");  // Use input field instead of slider

    // Set Loan Amount (Handling ElementNotInteractableException)
    public void setLoanAmount(int amount) {
        try {
            WebElement amountField = driver.findElement(loanAmountField);
            amountField.clear();  // Clear existing input
            amountField.sendKeys(String.valueOf(amount));  // Enter new value
        } catch (Exception e) {
            System.out.println("⚠ Loan Amount field is not directly interactable. Trying JavaScript...");
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].value='" + amount + "';", driver.findElement(loanAmountField));
        }
    }
    // Set Interest Rate
    public void setInterestRate(double rate) {
        WebElement slider = driver.findElement(interestRateSlider);
        slider.sendKeys(String.valueOf(rate));
    }

    // Set Loan Tenure
    public void setLoanTenure(int tenure) {
        WebElement slider = driver.findElement(loanTenureSlider);
        slider.sendKeys(String.valueOf(tenure));
    }

    // Validate EMI Calculation
    public boolean validateEMIDetails() {
        WebElement emiElement = driver.findElement(emiResult);
        WebElement interestElement = driver.findElement(totalInterestResult);
        WebElement paymentElement = driver.findElement(totalPaymentResult);

        return emiElement.isDisplayed() && interestElement.isDisplayed() && paymentElement.isDisplayed();
    }
}
