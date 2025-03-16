package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.InvalidElementStateException;

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
        try {
            WebElement coursesLink = wait.until(ExpectedConditions.elementToBeClickable(allCoursesLink));
            coursesLink.click();
        } catch (Exception e) {
            // If regular click fails, try JavaScript click
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            WebElement element = driver.findElement(allCoursesLink);
            executor.executeScript("arguments[0].click();", element);
        }
    }

    public void searchForCourse(String courseName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(searchBox));
            
            // Try multiple approaches to clear and enter text
            try {
                searchInput.clear();
            } catch (InvalidElementStateException e) {
                // If clear fails, try to use selectAll+delete
                searchInput.sendKeys(Keys.CONTROL + "a");
                searchInput.sendKeys(Keys.DELETE);
            }
            
            // Wait a bit after clearing
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            // Enter the course name
            searchInput.sendKeys(courseName);
            
            // Try different ways to click the search button
            try {
                WebElement button = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
                button.click();
            } catch (Exception e) {
                // If button click fails, try pressing Enter key
                searchInput.sendKeys(Keys.ENTER);
            }
        } catch (ElementNotInteractableException | StaleElementReferenceException e) {
            // If all else fails, use JavaScript to set value and trigger search
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("document.getElementById('search').value=arguments[0]", courseName);
            js.executeScript("document.getElementById('search').dispatchEvent(new KeyboardEvent('keypress', {'key': 'Enter'}))");
        }
    }

    public boolean isSearchResultDisplayed(String courseName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        
        try {
            // Wait for search results to load with a longer timeout
            wait.until(ExpectedConditions.visibilityOfElementLocated(searchResults));
            
            // Get all course titles from search results
            List<WebElement> courseElements = driver.findElements(searchResults);
            
            // If no results found, try a JavaScript approach to get elements
            if (courseElements.isEmpty()) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                courseElements = (List<WebElement>) js.executeScript(
                    "return document.querySelectorAll('.course-listing .course-listing-title')");
            }
            
            // Check if any course title contains the searched course name
            for (WebElement courseElement : courseElements) {
                String courseText = courseElement.getText();
                if (courseText.toLowerCase().contains(courseName.toLowerCase())) {
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
        try {
            WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(searchBox));
            try {
                searchInput.clear();
            } catch (InvalidElementStateException e) {
                // If clear fails, try to use selectAll+delete
                searchInput.sendKeys(Keys.CONTROL + "a");
                searchInput.sendKeys(Keys.DELETE);
            }
        } catch (Exception e) {
            // If standard methods fail, try with JavaScript
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("document.getElementById('search').value=''");
        }
    }
}