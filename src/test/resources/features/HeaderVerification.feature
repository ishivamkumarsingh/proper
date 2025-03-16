Feature: Header Section Verification

  @HeaderVerification
  Scenario: Verify webpage title and major navigation sections
    Given I navigate to the application homepage
    Then The webpage title should be "Let's Kode It"
    And The following navigation sections should be displayed
      | Home       |
      | ALL COURSES |
      | INTERVIEW   |
      | SUPPORT     |
      | BLOG        |
    
  @NavigationCheck
  Scenario Outline: Verify navigation to major sections
    Given I navigate to the application homepage
    When I click on the "<section>" navigation link
    Then I should be navigated to the "<section>" page
    
    Examples:
      | section     |
      | ALL COURSES |
      | INTERVIEW   |
      | SUPPORT     |
      | BLOG        |