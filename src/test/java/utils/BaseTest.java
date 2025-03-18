package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class BaseTest {
    protected static WebDriver driver;

    @BeforeClass
    public void setUp() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
