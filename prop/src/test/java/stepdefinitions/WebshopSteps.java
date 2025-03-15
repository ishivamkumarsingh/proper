package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.ElectronicsPage;
import pages.HomePage;
import pages.ProductDetailsPage;
import pages.ShoppingCartPage;
import utils.WebDriverManager;
import utils.WaitUtils;

public class WebshopSteps {
    private WebDriver driver;
    private HomePage homePage;
    private ElectronicsPage electronicsPage;
    private ProductDetailsPage productDetailsPage;
    private ShoppingCartPage shoppingCartPage;
    private WaitUtils waitUtils;
    
    @Before
    public void setup() {
        driver = WebDriverManager.getDriver();
        homePage = new HomePage(driver);
        electronicsPage = new ElectronicsPage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
        shoppingCartPage = new ShoppingCartPage(driver);
        waitUtils = new WaitUtils(driver);
    }
    
    @After
    public void tearDown() {
        WebDriverManager.quitDriver();
    }
    
    @Given("the user opens the demo webshop application")
    public void openDemoWebshop() {
        driver.get("https://demo.nopcommerce.com/");
    }
    
    @Then("the user should see the correct page title")
    public void verifyPageTitle() {
        String actualTitle = homePage.getPageTitle();
        Assert.assertTrue(actualTitle.contains("nopCommerce demo store"), 
                "Page title does not match expected. Actual: " + actualTitle);
    }
    
    @When("the user navigates to Electronics and then Cell Phones")
    public void navigateToElectronicsCellPhones() {
        homePage.navigateToElectronicsCellPhones();
        String pageTitle = electronicsPage.getPageTitle();
        Assert.assertTrue(pageTitle.contains("Cell phones"), 
                "Page title does not indicate Cell phones page. Actual: " + pageTitle);
    }
    
    @When("the user selects a smartphone")
    public void selectSmartphone() {
        electronicsPage.selectSmartphone();
    }
    
    @Then("the user should see the correct price")
    public void verifyPrice() {
        String price = productDetailsPage.getProductPrice();
        Assert.assertFalse(price.isEmpty(), "Product price is not displayed");
        // You could also verify the exact price if known
    }
    
    @Then("the shopping cart should be empty")
    public void verifyEmptyCart() {
        Assert.assertTrue(homePage.isShoppingCartEmpty(), 
                "Shopping cart should be empty but isn't");
    }
    
    @When("the user adds the product to cart")
    public void addProductToCart() {
        productDetailsPage.addToCart();
    }
    
    @Then("the shopping cart should show one item")
    public void verifyCartHasOneItem() {
        String cartQty = productDetailsPage.getCartQuantity();
        Assert.assertEquals(cartQty, "(1)", 
                "Shopping cart should have 1 item but shows: " + cartQty);
    }
    
    @Then("the cart should display correct product details")
    public void verifyProductDetails() {
        String productName = productDetailsPage.getProductName();
        Assert.assertTrue(productName.toLowerCase().contains("smartphone"), 
                "Product name should contain 'smartphone' but is: " + productName);
    }
    
    @When("the user goes to the cart")
    public void goToCart() {
        productDetailsPage.goToCart();
    }
    
    @When("the user attempts to checkout")
    public void attemptCheckout() {
        shoppingCartPage.clickCheckout();
    }
    
    @Then("an error message should appear")
    public void verifyErrorMessage() {
        Assert.assertTrue(shoppingCartPage.isErrorMessageDisplayed(), 
                "Error message about terms of service is not displayed");
        shoppingCartPage.closeWarningPopup();
    }
    
    @When("the user accepts terms of service")
    public void acceptTermsOfService() {
        shoppingCartPage.acceptTermsOfService();
    }
    
    @When("the user attempts to checkout again")
    public void attemptCheckoutAgain() {
        shoppingCartPage.clickCheckout();
    }
    
    @Then("the user should proceed with the checkout process")
    public void verifyCheckoutProcess() {
        // This step would verify you're on the checkout page
        // This assertion would depend on what happens in the actual application
        String pageTitle = driver.getTitle();
        Assert.assertTrue(pageTitle.contains("Checkout"), 
                "User should be on the checkout page. Actual title: " + pageTitle);
    }
}
