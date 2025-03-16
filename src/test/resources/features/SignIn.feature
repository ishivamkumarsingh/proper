Feature: Sign In

  # We will demonstrate reading from an Examples table 
  # for positive/negative tests. 
  # (Alternatively, you could read from Excel in the steps.)

  @SignIn
  Scenario Outline: User attempts to sign in
    Given I open the application home page
    When I click on the "Login" link
    And I enter username "<username>" and password "<password>"
    Then I should see a "<result>" login outcome

    Examples:
      | username             | password   | result    |
      | zoi@gmail.com | qwerty12345@ | success   |
 