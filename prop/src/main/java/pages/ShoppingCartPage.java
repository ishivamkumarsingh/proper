package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;

public class ShoppingCartPage {
    private WebDriver driver;
    private WaitUtils waitUtils;

    @FindBy(xpath="//span[@class='product-unit-price']")
    private WebElement unitPrice;

    @FindBy(xpath="//a[@class='product-name']")
    private WebElement productName;

    @FindBy(xpath="//input[contains(@name,'itemquantity')]")
    private WebElement quantity;

    @FindBy(xpath="//button[contains(@id,'checkout')]")
    private WebElement checkoutButton;

    @FindBy(xpath="//input[@id='termsofservice']")
    private WebElement termsOfServiceCheckbox;

    @FindBy(xpath="//div[@id='terms-of-service-warning-box']")
    private WebElement termsWarningBox;

    @FindBy(xpath="//button[@class='ui-button ui-corner-all ui-widget']")
    private WebElement closePopupButton;

    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
        PageFactory.initElements(driver, this);
    }

    public String getUnitPrice() {
        waitUtils.waitForElementToBeVisible(unitPrice);
        return unitPrice.getText();
    }

    public String getProductName() {
        waitUtils.waitForElementToBeVisible(productName);
        return productName.getText();
    }

    public String getQuantity() {
        waitUtils.waitForElementToBeVisible(quantity);
        return quantity.getAttribute("value");
    }

    public void clickCheckout() {
        waitUtils.waitForElementToBeClickable(checkoutButton);
        checkoutButton.click();
    }

    public boolean isErrorMessageDisplayed() {
        waitUtils.waitForElementToBeVisible(termsWarningBox);
        return termsWarningBox.isDisplayed();
    }

    public void closeWarningPopup() {
        waitUtils.waitForElementToBeClickable(closePopupButton);
        closePopupButton.click();
    }

    public void acceptTermsOfService() {
        waitUtils.waitForElementToBeClickable(termsOfServiceCheckbox);
        termsOfServiceCheckbox.click();
    }
}
