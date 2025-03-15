package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;

public class ProductDetailsPage {
    private WebDriver driver;
    private WaitUtils waitUtils;

    @FindBy(xpath="//span[@itemprop='price' and contains(@class, 'price-value')]")
    private WebElement productPrice;

    @FindBy(xpath="//input[contains(@id,'add-to-cart-button')]")
    private WebElement addToCartButton;

    @FindBy(xpath="//span[@class='cart-label' and contains(text(),'Shopping cart')]")
    private WebElement shoppingCartLink;

    @FindBy(xpath="//span[@class='cart-qty']")
    private WebElement cartQuantity;

    @FindBy(xpath="//h1[@itemprop='name']")
    private WebElement productName;

    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
        PageFactory.initElements(driver, this);
    }

    public String getProductPrice() {
        waitUtils.waitForElementToBeVisible(productPrice);
        return productPrice.getText();
    }

    public void addToCart() {
        waitUtils.waitForElementToBeClickable(addToCartButton);
        addToCartButton.click();
    }

    public String getCartQuantity() {
        waitUtils.waitForElementToBeVisible(cartQuantity);
        return cartQuantity.getText();
    }

    public void goToCart() {
        waitUtils.waitForElementToBeClickable(shoppingCartLink);
        shoppingCartLink.click();
    }

    public String getProductName() {
        waitUtils.waitForElementToBeVisible(productName);
        return productName.getText();
    }
}
