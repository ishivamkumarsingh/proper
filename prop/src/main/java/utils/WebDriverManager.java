package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class WebDriverManager {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    
    public static WebDriver getDriver() {
        if (driver.get() == null) {
            // Default to Chrome browser if no browser is specified
            initializeDriver("chrome");
        }
        return driver.get();
    }
    
    public static void initializeDriver(String browserName) {
        WebDriver webDriver;
        
        switch (browserName.toLowerCase()) {
            case "firefox":
                webDriver = new FirefoxDriver();
                break;
            case "edge":
                webDriver = new EdgeDriver();
                break;
            case "chrome":
            default:
                webDriver = new ChromeDriver();
                break;
        }
        
        webDriver.manage().window().maximize();
        driver.set(webDriver);
    }
    
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}