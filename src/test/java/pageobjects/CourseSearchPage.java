package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.TimeoutException;

public class CourseSearchPage {

    private WebDriver driver;

    // Locators with exact XPaths provided
    private By myCoursesLink = By.xpath("//*[@id=\"navbar-inverse-collapse\"]/ul/li[2]/a");
    private By searchBox = By.xpath("//*[@id=\"search\"]");
    private By searchButton = By.xpath("//*[@id=\"search\"]/div/button/i");
    
    // You'll need to update this with the actual XPath for course listings
    private By searchResults = By.xpath("//div[contains(@class,'course-listing')]//h4[contains(@class,'course-listing-title')]");

    public CourseSearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickMyCoursesLink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(myCoursesLink));
        driver.findElement(myCoursesLink).click();
    }

    public void searchForCourse(String courseName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(searchBox));
        searchInput.clear();
        searchInput.sendKeys(courseName);
        
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        button.click();
    }

    public boolean isSearchResultDisplayed(String courseName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        try {
            // Wait for search results to load
            wait.until(ExpectedConditions.visibilityOfElementLocated(searchResults));
            
            // Get all course titles from search results
            List<WebElement> courseElements = driver.findElements(searchResults);
            
            // Check if any course title contains the searched course name
            for (WebElement courseElement : courseElements) {
                if (courseElement.getText().contains(courseName)) {
                    return true;
                }
            }
            
            return false;
        } catch (TimeoutException e) {
            return false;
        }
    }
}