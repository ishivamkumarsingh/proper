package utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class BaseClass {

    public static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            // Since you already have msedgedriver.exe in the project root:
            // (assuming "msedgedriver.exe" is at the same level as pom.xml)
            System.setProperty("webdriver.edge.driver", 
                System.getProperty("user.dir") + "/msedgedriver.exe");

            driver = new EdgeDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
