package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import pages.EMICalculatorPage;
import utils.BaseTest;

public class EMISteps {
	   WebDriver driver = new ChromeDriver(); // Inherit driver
	  EMICalculatorPage emiCalculatorPage = new EMICalculatorPage(driver);
	    @Given("User is on EMI Calculator Home page")
	    public void user_is_on_emi_calculator_home_page() {
	        if (driver == null) {
	            throw new IllegalStateException("WebDriver is not initialized. Check BaseTest setup.");
	        }
	        driver.get("https://www.emicalculator.net/");
	        System.out.println("Navigated to EMI Calculator Home Page");
	    }

    @When("User navigates to {string} EMI section")
    public void user_navigates_to_emi_section(String loanType) {
        emiCalculatorPage.selectLoanType(loanType);
        System.out.println("Navigated to " + loanType + " EMI section");
    }

    @When("User sets Home Loan Amount to {int}")
    public void user_sets_home_loan_amount(int amount) {
        emiCalculatorPage.setLoanAmount(amount);
        System.out.println("Set Home Loan Amount: " + amount);
    }
    @When("User navigates to Home Loan EMI section")
    public void user_navigates_to_home_loan_emi_section() {
    	emiCalculatorPage.navigateToHomeLoanEMI();
        System.out.println("✅ Navigated to Home Loan EMI section.");
    }

    @When("User enters Interest Rate as {int}%")
    public void user_enters_interest_rate_as(Integer interestRate) {
    	emiCalculatorPage.enterInterestRate(interestRate);
        System.out.println("✅ Entered Interest Rate: " + interestRate + "%");
    }

    @When("User enters Interest Rate as {double}")
    public void user_enters_interest_rate(double rate) {
        emiCalculatorPage.setInterestRate(rate);
        System.out.println("Entered Interest Rate: " + rate);
    }

    @When("User sets Loan Tenure to {int}")
    public void user_sets_loan_tenure(int tenure) {
        emiCalculatorPage.setLoanTenure(tenure);
        System.out.println("Set Loan Tenure: " + tenure);
    }

    @And("User sets Home Loan Amount to {int}")
    public void user_sets_home_loan_amount(int amount) {
    	emiCalculatorPage.setLoanAmount(amount);
        System.out.println("✅ Home Loan Amount set to: " + amount);
    }

    @Then("Validate Loan EMI, Total Interest Payable, and Total Payment")
    public void validate_loan_emi_and_total_payment() {
        boolean isValid = emiCalculatorPage.validateEMIDetails();
        Assert.assertTrue(isValid, "Loan EMI validation failed!");
        System.out.println("Loan EMI details validated successfully");
    }
}
