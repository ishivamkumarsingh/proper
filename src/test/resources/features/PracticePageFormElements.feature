Feature: Practice Page Form Elements

  @PracticePageElements
  Scenario: Verify radio buttons on practice page
    Given I navigate to the application homepage
    When I click on the "PRACTICE" navigation link
    Then I should be navigated to the "PRACTICE" page
    When I click on the "BMW" radio button
    Then The "BMW" radio button should be selected
    When I click on the "Benz" radio button
    Then The "Benz" radio button should be selected
    When I click on the "Honda" radio button
    Then The "Honda" radio button should be selected

  @PracticePageElements
  Scenario: Verify checkboxes on practice page
    Given I navigate to the application homepage
    When I click on the "PRACTICE" navigation link
    Then I should be navigated to the "PRACTICE" page
    When I check the "BMW" checkbox
    Then The "BMW" checkbox should be checked
    When I check the "Benz" checkbox
    Then The "Benz" checkbox should be checked
    When I check the "Honda" checkbox
    Then The "Honda" checkbox should be checked
    When I uncheck the "BMW" checkbox
    Then The "BMW" checkbox should be unchecked

  @PracticePageElements
  Scenario: Verify select dropdown on practice page
    Given I navigate to the application homepage
    When I click on the "PRACTICE" navigation link
    Then I should be navigated to the "PRACTICE" page
    When I select "BMW" from the car dropdown
    Then "BMW" should be selected in the dropdown
    When I select "Benz" from the car dropdown
    Then "Benz" should be selected in the dropdown
    When I select "Honda" from the car dropdown
    Then "Honda" should be selected in the dropdown

  @PracticePageElements
  Scenario: Verify multiple select on practice page
    Given I navigate to the application homepage
    When I click on the "PRACTICE" navigation link
    Then I should be navigated to the "PRACTICE" page
    When I select multiple options from fruits dropdown:
      | apple      |
      | orange     |
      | peach      |
    Then All selected fruits should be highlighted in the dropdown