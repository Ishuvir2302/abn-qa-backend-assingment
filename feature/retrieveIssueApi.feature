#Author: Ishuvir Singh

@E2E
Feature: Test GitLab Issues API with request operations
Description: The purpose of this feature is to test the Gitlab issue Api Request operations with edge cases

@Regression @Sanity
  Scenario: IssueApi GET request with valid token
    Given I created the GET request with Valid token
    When I sent the request with issue route
    Then I validate the response status is '200'
    And I validate the Status line code
    And I verify the issue type in response
    And I verify the issue count in response

@Regression
  Scenario Outline: Issue API project response verification
    Given I created the GET request with Valid token
    When I sent the request with <projectNumber>
    Then I validate the response status is '200'
    And I verified the response body contains <projectNumber>

    Examples: 
      | projectNumber |
      | "46543056"    |
      | "46543119"    |

@Regression
  Scenario: IssueApi issue error response validation
    Given I created the GET request with Invalid token
    When I sent the request with issue route
    Then I validate the response status is '401'
    And I validate the error Status line code


@Sanity
  Scenario: IssueApi group response verification
    Given I created the GET request with Valid token
    When I sent the request with group route
    Then I validate the response status is '200'
    And I validate the Status line code
    And I validate the header count
    And I logged all headers
    And I validated the Content-Type header response

@Regression @Sanity
  Scenario Outline: validating existance of issue in correct project
    Given I created the GET request with Valid token
    When I sent the request with <projectNumber> & <issueNumber>
    Then I validate the response status is '200'
    And I verified <projectNumber> contains <issueNumber>
    And I validated the <author> name

    Examples: 
      | projectNumber | issueNumber | author        |
      | "46643991"    | "128989636" | "Ishuvir2302" |
      | "46543056"    | "128865527" | "Ishuvir2302" |
