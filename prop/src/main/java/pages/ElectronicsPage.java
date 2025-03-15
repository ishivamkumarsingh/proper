package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;

public class ElectronicsPage {
    private WebDriver driver;
    private WaitUtils waitUtils;

    @FindBy(xpath="//h2[@class='product-title']/a[contains(text(),'Smartphone')]")
    private WebElement smartphoneProduct;

    public ElectronicsPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
        PageFactory.initElements(driver, this);
    }

    public void selectSmartphone() {
        waitUtils.waitForElementToBeClickable(smartphoneProduct);
        smartphoneProduct.click();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
