Feature: Calculate EMI for different loans
@search
  Scenario Outline: Home Loan EMI Calculation
    Given User is on EMI Calculator Home page
    When User navigates to Home Loan EMI section
    And User sets Home Loan Amount to <LoanAmount>
    And User enters Interest Rate as <InterestRate>
    And User sets Loan Tenure to <Tenure>
    Then Validate Loan EMI, Total Interest Payable, and Total Payment

    Examples:
      | LoanAmount | InterestRate | Tenure |
      | 5000000    | 10%          | 20     |

  Scenario Outline: Personal Loan EMI Calculation
    Given User is on EMI Calculator Home page
    When User navigates to Personal Loan EMI section
    And User enters Personal Loan Amount as <LoanAmount>
    And User sets Interest Rate using slider to <InterestRate>
    And User enters Loan Tenure as <Tenure>
    Then Validate Loan EMI, Total Interest Payable, and Total Payment

    Examples:
      | LoanAmount | InterestRate | Tenure |
      | 750000     | 15%          | 5      |

  Scenario Outline: Car Loan EMI Calculation
    Given User is on EMI Calculator Home page
    When User navigates to Car Loan EMI section
    And User enters Car Loan Amount as <LoanAmount>
    And User enters Interest Rate as <InterestRate>
    And User sets Loan Tenure using slider to <Tenure>
    Then Validate Loan EMI, Total Interest Payable, and Total Payment

    Examples:
      | LoanAmount | InterestRate | Tenure |
      | 2000000    | 9%           | 6      |
