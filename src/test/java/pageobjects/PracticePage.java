package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;
import java.util.List;

public class PracticePage {

    private WebDriver driver;
    
    // Locators
    private String radioButtonXpath = "//input[@type='radio' and @name='cars' and @value='%s']";
    private String checkboxXpath = "//input[@type='checkbox' and @name='cars' and @value='%s']";
    private By carDropdown = By.id("carselect");
    private By fruitMultiSelect = By.id("multiple-select-example");
    private By nameField = By.id("name");
    private By alertButton = By.id("alertbtn");
    private By confirmButton = By.id("confirmbtn");
    private By openWindowButton = By.id("openwindow");
    private By openTabButton = By.id("opentab");
    private By hideButton = By.id("hide-textbox");
    private By showButton = By.id("show-textbox");
    private By disableButton = By.id("disabled-button");
    private By enableButton = By.id("enabled-button");
    private By displayedTextField = By.id("displayed-text");
    private By enabledTextField = By.id("enabled-example-input");
    private By practiceHeader = By.xpath("//h1[contains(text(),'Practice Page')]");
    private By successMessage = By.id("success-message"); // Adjust as needed
    
    public PracticePage(WebDriver driver) {
        this.driver = driver;
    }
    
    public void clickRadioButton(String buttonName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String xpath = String.format(radioButtonXpath, buttonName.toLowerCase());
        try {
            WebElement radioButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
            radioButton.click();
        } catch (Exception e) {
            // Fall back to JavaScript click
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement radioButton = driver.findElement(By.xpath(xpath));
            js.executeScript("arguments[0].click();", radioButton);
        }
    }
    
    public boolean isRadioButtonSelected(String buttonName) {
        String xpath = String.format(radioButtonXpath, buttonName.toLowerCase());
        WebElement radioButton = driver.findElement(By.xpath(xpath));
        return radioButton.isSelected();
    }
    
    public void checkCheckbox(String checkboxName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String xpath = String.format(checkboxXpath, checkboxName.toLowerCase());
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        
        if (!checkbox.isSelected()) {
            try {
                checkbox.click();
            } catch (Exception e) {
                // Fall back to JavaScript click
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", checkbox);
            }
        }
    }
    
    public void uncheckCheckbox(String checkboxName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String xpath = String.format(checkboxXpath, checkboxName.toLowerCase());
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        
        if (checkbox.isSelected()) {
            try {
                checkbox.click();
            } catch (Exception e) {
                // Fall back to JavaScript click
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", checkbox);
            }
        }
    }
    
    public boolean isCheckboxChecked(String checkboxName) {
        String xpath = String.format(checkboxXpath, checkboxName.toLowerCase());
        WebElement checkbox = driver.findElement(By.xpath(xpath));
        return checkbox.isSelected();
    }
    
    public void selectFromDropdown(String option) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(carDropdown));
        Select select = new Select(dropdown);
        select.selectByVisibleText(option);
    }
    
    public String getSelectedDropdownOption() {
        WebElement dropdown = driver.findElement(carDropdown);
        Select select = new Select(dropdown);
        return select.getFirstSelectedOption().getText();
    }
    
    public void selectMultipleFromDropdown(List<String> options) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement multiSelect = wait.until(ExpectedConditions.elementToBeClickable(fruitMultiSelect));
        Select select = new Select(multiSelect);
        
        // First clear any existing selections
        select.deselectAll();
        
        // Then select each option
        for (String option : options) {
            select.selectByVisibleText(option);
        }
    }
    
    public boolean areAllFruitsSelected() {
        WebElement multiSelect = driver.findElement(fruitMultiSelect);
        Select select = new Select(multiSelect);
        
        List<WebElement> selectedOptions = select.getAllSelectedOptions();
        return !selectedOptions.isEmpty();
    }
    
    public void enterName(String name) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement nameInput = wait.until(ExpectedConditions.elementToBeClickable(nameField));
        nameInput.clear();
        nameInput.sendKeys(name);
    }
    
    public void clickButton(String buttonName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By buttonLocator;
        
        switch (buttonName.toUpperCase()) {
            case "ALERT":
                buttonLocator = alertButton;
                break;
            case "CONFIRM":
                buttonLocator = confirmButton;
                break;
            case "OPEN WINDOW":
                buttonLocator = openWindowButton;
                break;
            case "OPEN TAB":
                buttonLocator = openTabButton;
                break;
            case "HIDE":
                buttonLocator = hideButton;
                break;
            case "SHOW":
                buttonLocator = showButton;
                break;
            case "DISABLE":
                buttonLocator = disableButton;
                break;
            case "ENABLE":
                buttonLocator = enableButton;
                break;
            default:
                throw new IllegalArgumentException("Unknown button: " + buttonName);
        }
        
        try {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(buttonLocator));
            button.click();
        } catch (Exception e) {
            // Fall back to JavaScript click
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement button = driver.findElement(buttonLocator);
            js.executeScript("arguments[0].click();", button);
        }
    }
    
    public boolean isSuccessMessageDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isPracticePageDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(practiceHeader)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isDisplayedTextFieldVisible() {
        try {
            return driver.findElement(displayedTextField).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isEnabledTextFieldEnabled() {
        try {
            return driver.findElement(enabledTextField).isEnabled();
        } catch (Exception e) {
            return false;
        }
    }
}