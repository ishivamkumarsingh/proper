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

    // Updated locators
    private By allCoursesLink = By.xpath("//a[contains(text(),'ALL COURSES')]");
    private By searchBox = By.id("search");
    private By searchButton = By.xpath("//*[@id=\"search\"]/div/button/i");
    private By searchResults = By.xpath("//div[contains(@class,'course-listing')]//h4[contains(@class,'course-listing-title')]");
    private By courseTitle = By.cssSelector("h1.dynamic-heading");

    public CourseSearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickAllCoursesLink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(allCoursesLink));
        driver.findElement(allCoursesLink).click();
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
    
    public void clearSearchBox() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(searchBox));
        searchInput.clear();
    }
}