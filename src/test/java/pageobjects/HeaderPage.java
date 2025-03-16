package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;

public class HeaderPage {

    private WebDriver driver;
    
    // Locators for navigation elements
    private By homeLink = By.xpath("//a[contains(@class,'navbar-brand')]");
    private By allCoursesLink = By.xpath("//a[contains(text(),'ALL COURSES')]");
    private By interviewLink = By.xpath("//a[contains(text(),'INTERVIEW')]");
    private By supportLink = By.xpath("//a[contains(text(),'SUPPORT')]");
    private By blogLink = By.xpath("//a[contains(text(),'BLOG')]");
    
    // Map of navigation sections to their expected URLs or page titles
    private final String HOME_URL_PATTERN = "letskodeit.com/home";
    private final String ALL_COURSES_URL_PATTERN = "letskodeit.com/courses";
    private final String INTERVIEW_URL_PATTERN = "letskodeit.com/interview";
    private final String SUPPORT_URL_PATTERN = "letskodeit.com/support";
    private final String BLOG_URL_PATTERN = "letskodeit.com/blog";
    
    public HeaderPage(WebDriver driver) {
        this.driver = driver;
    }
    
    public String getPageTitle() {
        return driver.getTitle();
    }
    
    public boolean isNavigationSectionDisplayed(String sectionName) {
        By sectionLocator = getSectionLocator(sectionName);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(sectionLocator));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public List<String> getAllDisplayedNavigationSections() {
        List<String> displayedSections = new ArrayList<>();
        
        // Check each section
        if (isElementDisplayed(homeLink)) displayedSections.add("Home");
        if (isElementDisplayed(allCoursesLink)) displayedSections.add("ALL COURSES");
        if (isElementDisplayed(interviewLink)) displayedSections.add("INTERVIEW");
        if (isElementDisplayed(supportLink)) displayedSections.add("SUPPORT");
        if (isElementDisplayed(blogLink)) displayedSections.add("BLOG");
        
        return displayedSections;
    }
    
    public void clickNavigationSection(String sectionName) {
        By sectionLocator = getSectionLocator(sectionName);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(sectionLocator));
            element.click();
        } catch (Exception e) {
            // Fallback to JavaScript click if regular click fails
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement element = driver.findElement(sectionLocator);
            js.executeScript("arguments[0].click();", element);
        }
    }
    
    public boolean isNavigatedToCorrectPage(String sectionName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        
        try {
            // Wait for page to load
            wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
            
            String currentUrl = driver.getCurrentUrl().toLowerCase();
            
            switch(sectionName.toUpperCase()) {
                case "HOME":
                    return currentUrl.contains(HOME_URL_PATTERN);
                case "ALL COURSES":
                    return currentUrl.contains(ALL_COURSES_URL_PATTERN);
                case "INTERVIEW":
                    return currentUrl.contains(INTERVIEW_URL_PATTERN);
                case "SUPPORT":
                    return currentUrl.contains(SUPPORT_URL_PATTERN);
                case "BLOG":
                    return currentUrl.contains(BLOG_URL_PATTERN);
                default:
                    return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
    
    private By getSectionLocator(String sectionName) {
        switch(sectionName.toUpperCase()) {
            case "HOME":
                return homeLink;
            case "ALL COURSES":
                return allCoursesLink;
            case "INTERVIEW":
                return interviewLink;
            case "SUPPORT":
                return supportLink;
            case "BLOG":
                return blogLink;
            default:
                throw new IllegalArgumentException("Unknown section name: " + sectionName);
        }
    }
    
    private boolean isElementDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}