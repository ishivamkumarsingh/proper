Feature: Advanced Practice Page Interactions

  @AdvancedInteractions
  Scenario: Verify alert and confirm functionality
    Given I navigate to the application homepage
    When I click on the "PRACTICE" navigation link
    Then I should be navigated to the "PRACTICE" page
    When I enter "Test Name" in the name field
    And I click on the "Alert" button
    Then I should see an alert with text containing "Test Name"
    When I accept the alert
    And I click on the "Confirm" button
    Then I should see a confirmation dialog
    When I accept the confirmation dialog
    Then I should see a success message

  @AdvancedInteractions
  Scenario: Verify window handling functionality
    Given I navigate to the application homepage
    When I click on the "PRACTICE" navigation link
    Then I should be navigated to the "PRACTICE" page
    When I click on the "Open Window" button
    Then A new window should open
    When I switch to the new window
    Then I should see content related to "Courses"
    When I close the new window and switch back to main window
    Then I should be on the practice page

  @AdvancedInteractions
  Scenario: Verify tab handling functionality
    Given I navigate to the application homepage
    When I click on the "PRACTICE" navigation link
    Then I should be navigated to the "PRACTICE" page
    When I click on the "Open Tab" button
    Then A new tab should open
    When I switch to the new tab
    Then I should see the courses page
    When I close the new tab and switch back to main window
    Then I should be on the practice page

  @AdvancedInteractions
  Scenario: Verify element displayed/enabled functionality
    Given I navigate to the application homepage
    When I click on the "PRACTICE" navigation link
    Then I should be navigated to the "PRACTICE" page
    When I click on the "Hide" button
    Then The displayed text field should be hidden
    When I click on the "Show" button
    Then The displayed text field should be visible
    When I click on the "Disable" button
    Then The enabled text field should be disabled
    When I click on the "Enable" button
    Then The enabled text field should be enabled