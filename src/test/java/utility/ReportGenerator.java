package utility;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ReportGenerator {
    
    public static ExtentReports extent;
    public static ExtentTest test;

    // Initialize the Extent report once (e.g. at the start of the test run)
    public static void initReport() {
        if (extent == null) {
            // The first boolean param indicates whether to overwrite (true) or append (false)
            extent = new ExtentReports("target/ExtentReport.html", true);
            // You can add system info if desired:
            // extent.addSystemInfo("Browser", "Edge");
            // extent.addSystemInfo("Environment", "QA");
        }
    }

    // Start a test for each scenario
    public static void startTest(String scenarioName) {
        test = extent.startTest(scenarioName);
    }

    // End test for each scenario
    public static void endTest() {
        if (extent != null && test != null) {
            extent.endTest(test);
        }
    }

    // Flush any pending logs
    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }

    // Close the report (usually done after all tests)
    public static void closeReport() {
        if (extent != null) {
            extent.flush();
            extent.close();
        }
    }
}
