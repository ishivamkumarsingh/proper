Feature: Course Search Functionality

  @CourseSearchTest
  Scenario Outline: User searches for courses
    Given I navigate to application homepage
    When I click on My Courses link
    And I search for "<courseName>" in the search box
    Then I should see search results containing "<courseName>"

    Examples:
      | courseName                     |
      | Complete Test Automation Bundle |
      | JavaScript for beginners       |
      | Cypress.io Test Automation     |