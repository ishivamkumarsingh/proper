package stepdefinition;

import io.cucumber.java.en.*;
import io.cucumber.datatable.DataTable;
import org.testng.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageobjects.PracticePage;
import utility.BaseClass;
import utility.Log4jConfig;
import utility.ScreenshotUtil;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class PracticePageSteps extends BaseClass {

    PracticePage practicePage;
    private String mainWindowHandle;
    private Alert alert;

    @When("I click on the {string} radio button")
    public void i_click_on_the_radio_button(String buttonName) {
        Log4jConfig.info("Clicking on radio button: " + buttonName);
        practicePage = new PracticePage(driver);
        practicePage.clickRadioButton(buttonName);
        ScreenshotUtil.captureScreenshotReturnPath(driver, "RadioButton_" + buttonName);
    }

    @Then("The {string} radio button should be selected")
    public void the_radio_button_should_be_selected(String buttonName) {
        Log4jConfig.info("Verifying radio button is selected: " + buttonName);
        boolean isSelected = practicePage.isRadioButtonSelected(buttonName);
        Assert.assertTrue(isSelected, "Radio button '" + buttonName + "' is not selected");
        ScreenshotUtil.captureScreenshotReturnPath(driver, "RadioSelected_" + buttonName);
    }

    @When("I check the {string} checkbox")
    public void i_check_the_checkbox(String checkboxName) {
        Log4jConfig.info("Checking checkbox: " + checkboxName);
        practicePage.checkCheckbox(checkboxName);
        ScreenshotUtil.captureScreenshotReturnPath(driver, "Checkbox_" + checkboxName);
    }

    @Then("The {string} checkbox should be checked")
    public void the_checkbox_should_be_checked(String checkboxName) {
        Log4jConfig.info("Verifying checkbox is checked: " + checkboxName);
        boolean isChecked = practicePage.isCheckboxChecked(checkboxName);
        Assert.assertTrue(isChecked, "Checkbox '" + checkboxName + "' is not checked");
        ScreenshotUtil.captureScreenshotReturnPath(driver, "CheckboxChecked_" + checkboxName);
    }

    @When("I uncheck the {string} checkbox")
    public void i_uncheck_the_checkbox(String checkboxName) {
        Log4jConfig.info("Unchecking checkbox: " + checkboxName);
        practicePage.uncheckCheckbox(checkboxName);
        ScreenshotUtil.captureScreenshotReturnPath(driver, "UncheckBox_" + checkboxName);
    }

    @Then("The {string} checkbox should be unchecked")
    public void the_checkbox_should_be_unchecked(String checkboxName) {
        Log4jConfig.info("Verifying checkbox is unchecked: " + checkboxName);
        boolean isChecked = practicePage.isCheckboxChecked(checkboxName);
        Assert.assertFalse(isChecked, "Checkbox '" + checkboxName + "' is still checked");
        ScreenshotUtil.captureScreenshotReturnPath(driver, "CheckboxUnchecked_" + checkboxName);
    }

    @When("I select {string} from the car dropdown")
    public void i_select_from_the_car_dropdown(String option) {
        Log4jConfig.info("Selecting from dropdown: " + option);
        practicePage.selectFromDropdown(option);
        ScreenshotUtil.captureScreenshotReturnPath(driver, "Dropdown_" + option);
    }

    @Then("{string} should be selected in the dropdown")
    public void should_be_selected_in_the_dropdown(String option) {
        Log4jConfig.info("Verifying dropdown selection: " + option);
        String selected = practicePage.getSelectedDropdownOption();
        Assert.assertEquals(selected, option, "Expected option '" + option + "' but got '" + selected + "'");
        ScreenshotUtil.captureScreenshotReturnPath(driver, "DropdownSelected_" + option);
    }

    @When("I select multiple options from fruits dropdown:")
    public void i_select_multiple_options_from_fruits_dropdown(DataTable fruitsTable) {
        Log4jConfig.info("Selecting multiple options from dropdown");
        List<String> fruits = fruitsTable.asList();
        practicePage.selectMultipleFromDropdown(fruits);
        ScreenshotUtil.captureScreenshotReturnPath(driver, "MultipleSelect");
    }

    @Then("All selected fruits should be highlighted in the dropdown")
    public void all_selected_fruits_should_be_highlighted_in_the_dropdown() {
        Log4jConfig.info("Verifying multiple selections in dropdown");
        boolean allSelected = practicePage.areAllFruitsSelected();
        Assert.assertTrue(allSelected, "Not all selected fruits are highlighted");
        ScreenshotUtil.captureScreenshotReturnPath(driver, "MultipleSelectVerified");
    }

    @When("I enter {string} in the name field")
    public void i_enter_in_the_name_field(String name) {
        Log4jConfig.info("Entering name: " + name);
        practicePage.enterName(name);
        ScreenshotUtil.captureScreenshotReturnPath(driver, "EnterName_" + name);
    }

    @When("I click on the {string} button")
    public void i_click_on_the_button(String buttonName) {
        Log4jConfig.info("Clicking on button: " + buttonName);
        practicePage.clickButton(buttonName);
        ScreenshotUtil.captureScreenshotReturnPath(driver, "Button_" + buttonName);
    }

    @Then("I should see an alert with text containing {string}")
    public void i_should_see_an_alert_with_text_containing(String text) {
        Log4jConfig.info("Verifying alert text contains: " + text);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            alert = wait.until(ExpectedConditions.alertIsPresent());
            String alertText = alert.getText();
            Assert.assertTrue(alertText.contains(text), 
                    "Alert text '" + alertText + "' does not contain '" + text + "'");
            Log4jConfig.info("Alert verified with text: " + alertText);
        } catch (Exception e) {
            Assert.fail("Alert did not appear or contained unexpected text");
        }
    }

    @When("I accept the alert")
    public void i_accept_the_alert() {
        Log4jConfig.info("Accepting alert");
        if (alert != null) {
            alert.accept();
        } else {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
        }
    }

    @Then("I should see a confirmation dialog")
    public void i_should_see_a_confirmation_dialog() {
        Log4jConfig.info("Verifying confirmation dialog appears");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            alert = wait.until(ExpectedConditions.alertIsPresent());
            Log4jConfig.info("Confirmation dialog verified");
        } catch (Exception e) {
            Assert.fail("Confirmation dialog did not appear");
        }
    }

    @When("I accept the confirmation dialog")
    public void i_accept_the_confirmation_dialog() {
        Log4jConfig.info("Accepting confirmation dialog");
        if (alert != null) {
            alert.accept();
        } else {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
        }
    }

    @Then("I should see a success message")
    public void i_should_see_a_success_message() {
        Log4jConfig.info("Verifying success message appears");
        boolean messageDisplayed = practicePage.isSuccessMessageDisplayed();
        Assert.assertTrue(messageDisplayed, "Success message is not displayed");
        ScreenshotUtil.captureScreenshotReturnPath(driver, "SuccessMessage");
    }

    @When("I click on the {string} Window button")
    public void i_click_on_the_window_button(String buttonName) {
        Log4jConfig.info("Clicking on window button: " + buttonName);
        mainWindowHandle = driver.getWindowHandle(); // Store the main window handle
        practicePage.clickButton(buttonName + " Window");
        ScreenshotUtil.captureScreenshotReturnPath(driver, "WindowButton_" + buttonName);
    }

    @Then("A new window should open")
    public void a_new_window_should_open() {
        Log4jConfig.info("Verifying new window opened");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver -> driver.getWindowHandles().size() > 1);
        Set<String> windowHandles = driver.getWindowHandles();
        Assert.assertTrue(windowHandles.size() > 1, "No new window was opened");
        Log4jConfig.info("New window verified");
    }

    @When("I switch to the new window")
    public void i_switch_to_the_new_window() {
        Log4jConfig.info("Switching to new window");
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            if (!handle.equals(mainWindowHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        ScreenshotUtil.captureScreenshotReturnPath(driver, "NewWindow");
    }

    @Then("I should see content related to {string}")
    public void i_should_see_content_related_to(String content) {
        Log4jConfig.info("Verifying content related to: " + content);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        By contentLocator = By.xpath("//*[contains(text(),'" + content + "')]");
        boolean contentFound = wait.until(ExpectedConditions.visibilityOfElementLocated(contentLocator)).isDisplayed();
        Assert.assertTrue(contentFound, "Content related to '" + content + "' was not found");
        ScreenshotUtil.captureScreenshotReturnPath(driver, "ContentVerified_" + content);
    }

    @When("I close the new window and switch back to main window")
    public void i_close_the_new_window_and_switch_back_to_main_window() {
        Log4jConfig.info("Closing new window and switching back");
        driver.close(); // Close current window
        driver.switchTo().window(mainWindowHandle); // Switch back to main window
        ScreenshotUtil.captureScreenshotReturnPath(driver, "BackToMainWindow");
    }

    @Then("I should be on the practice page")
    public void i_should_be_on_the_practice_page() {
        Log4jConfig.info("Verifying we're back on practice page");
        boolean onPracticePage = practicePage.isPracticePageDisplayed();
        Assert.assertTrue(onPracticePage, "Not on practice page");
        ScreenshotUtil.captureScreenshotReturnPath(driver, "OnPracticePage");
    }

    @When("I click on the {string} Tab button")
    public void i_click_on_the_tab_button(String buttonName) {
        Log4jConfig.info("Clicking on tab button: " + buttonName);
        mainWindowHandle = driver.getWindowHandle(); // Store the main window handle
        practicePage.clickButton(buttonName + " Tab");
        ScreenshotUtil.captureScreenshotReturnPath(driver, "TabButton_" + buttonName);
    }

    @Then("A new tab should open")
    public void a_new_tab_should_open() {
        Log4jConfig.info("Verifying new tab opened");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver -> driver.getWindowHandles().size() > 1);
        Set<String> windowHandles = driver.getWindowHandles();
        Assert.assertTrue(windowHandles.size() > 1, "No new tab was opened");
        Log4jConfig.info("New tab verified");
    }

    @When("I switch to the new tab")
    public void i_switch_to_the_new_tab() {
        Log4jConfig.info("Switching to new tab");
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            if (!handle.equals(mainWindowHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        ScreenshotUtil.captureScreenshotReturnPath(driver, "NewTab");
    }

    @Then("I should see the courses page")
    public void i_should_see_the_courses_page() {
        Log4jConfig.info("Verifying courses page is displayed");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        By coursesLocator = By.xpath("//h1[contains(text(),'Courses')]");
        boolean coursesPageFound = wait.until(ExpectedConditions.visibilityOfElementLocated(coursesLocator)).isDisplayed();
        Assert.assertTrue(coursesPageFound, "Courses page not found");
        ScreenshotUtil.captureScreenshotReturnPath(driver, "CoursesPage");
    }

    @When("I close the new tab and switch back to main window")
    public void i_close_the_new_tab_and_switch_back_to_main_window() {
        Log4jConfig.info("Closing new tab and switching back");
        driver.close(); // Close current tab
        driver.switchTo().window(mainWindowHandle); // Switch back to main window
        ScreenshotUtil.captureScreenshotReturnPath(driver, "BackToMainTab");
    }
    
    @When("I click on the {string} button")
    public void i_click_on_the_display_button(String buttonName) {
        Log4jConfig.info("Clicking on element display/enable button: " + buttonName);
        practicePage.clickButton(buttonName);
        ScreenshotUtil.captureScreenshotReturnPath(driver, "Button_" + buttonName);
    }
    
    @Then("The displayed text field should be hidden")
    public void the_displayed_text_field_should_be_hidden() {
        Log4jConfig.info("Verifying text field is hidden");
        boolean isHidden = !practicePage.isDisplayedTextFieldVisible();
        Assert.assertTrue(isHidden, "Text field is still visible");
        ScreenshotUtil.captureScreenshotReturnPath(driver, "TextField_Hidden");
    }
    
    @Then("The displayed text field should be visible")
    public void the_displayed_text_field_should_be_visible() {
        Log4jConfig.info("Verifying text field is visible");
        boolean isVisible = practicePage.isDisplayedTextFieldVisible();
        Assert.assertTrue(isVisible, "Text field is not visible");
        ScreenshotUtil.captureScreenshotReturnPath(driver, "TextField_Visible");
    }
    
    @Then("The enabled text field should be disabled")
    public void the_enabled_text_field_should_be_disabled() {
        Log4jConfig.info("Verifying text field is disabled");
        boolean isDisabled = !practicePage.isEnabledTextFieldEnabled();
        Assert.assertTrue(isDisabled, "Text field is still enabled");
        ScreenshotUtil.captureScreenshotReturnPath(driver, "TextField_Disabled");
    }
    
    @Then("The enabled text field should be enabled")
    public void the_enabled_text_field_should_be_enabled() {
        Log4jConfig.info("Verifying text field is enabled");
        boolean isEnabled = practicePage.isEnabledTextFieldEnabled();
        Assert.assertTrue(isEnabled, "Text field is not enabled");
        ScreenshotUtil.captureScreenshotReturnPath(driver, "TextField_Enabled");
    }
}