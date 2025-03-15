package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;

public class HomePage {
    private WebDriver driver;
    private WaitUtils waitUtils;

    @FindBy(xpath="//ul[contains(@class,'top-menu')]//a[contains(text(),'Electronics')]")
    private WebElement electronicsMenu;

    @FindBy(xpath="//ul[contains(@class,'top-menu')]//a[contains(text(),'Cell phones')]")
    private WebElement cellPhonesSubMenu;

    @FindBy(xpath="//span[@class='cart-label' and contains(text(),'Shopping cart')]")
    private WebElement shoppingCartLabel;

    @FindBy(xpath="//span[@class='cart-qty']")
    private WebElement cartQuantity;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
        PageFactory.initElements(driver, this);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void navigateToElectronicsCellPhones() {
        Actions actions = new Actions(driver);
        waitUtils.waitForElementToBeVisible(electronicsMenu);
        actions.moveToElement(electronicsMenu).perform();
        waitUtils.waitForElementToBeVisible(cellPhonesSubMenu);
        cellPhonesSubMenu.click();
    }

    public String getShoppingCartQuantity() {
        waitUtils.waitForElementToBeVisible(cartQuantity);
        return cartQuantity.getText();
    }

    public boolean isShoppingCartEmpty() {
        return getShoppingCartQuantity().equals("(0)");
    }
}
