package stepdefinition;

import io.cucumber.java.en.*;
import org.testng.Assert;

import pageobjects.CourseSearchPage;
import utility.BaseClass;
import utility.Log4jConfig;
import utility.ScreenshotUtil;

public class CourseSearchSteps extends BaseClass {

    CourseSearchPage courseSearchPage;

    @Given("I open the application home page")
    public void i_open_the_application_home_page() {
        Log4jConfig.info("Launching browser and navigating to Let's Kode It...");
        driver = getDriver(); // from BaseClass
        driver.get("https://www.letskodeit.com/"); // Direct URL
        ScreenshotUtil.captureScreenshotReturnPath(driver, "HomePage");
    }

    @When("I click on My Courses link")
    public void i_click_on_my_courses_link() {
        Log4jConfig.info("Clicking on My Courses link...");
        courseSearchPage = new CourseSearchPage(driver);
        courseSearchPage.clickMyCoursesLink();
        ScreenshotUtil.captureScreenshotReturnPath(driver, "MyCoursesPage");
    }

    @When("I search for {string} in the search box")
    public void i_search_for_in_the_search_box(String courseName) {
        Log4jConfig.info("Searching for course: " + courseName);
        courseSearchPage.searchForCourse(courseName);
        ScreenshotUtil.captureScreenshotReturnPath(driver, "SearchFor_" + courseName.replaceAll("\\s+", "_"));
    }

    @Then("I should see search results containing {string}")
    public void i_should_see_search_results_containing(String courseName) {
        Log4jConfig.info("Verifying search results for: " + courseName);
        boolean courseFound = courseSearchPage.isSearchResultDisplayed(courseName);
        Assert.assertTrue(courseFound, "Course '" + courseName + "' was not found in search results");
        Log4jConfig.info("Course '" + courseName + "' was found successfully");
        ScreenshotUtil.captureScreenshotReturnPath(driver, "SearchResults_" + courseName.replaceAll("\\s+", "_"));
    }
}