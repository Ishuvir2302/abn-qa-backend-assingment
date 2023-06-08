#Author: Ishuvir singh
@E2E
Feature: Gitlab issue Api with delete operation
  The purpose of this feature is to test the Gitlab issue Api delete operations

  Background: issue is available for deletion
    Given I created issue for deletion in '46543119'

  @Regression
  Scenario: Issue API delete issue verification
    When I sent the delete request with issue in '46543119'
    Then I verified the delete status code as '204'
    And I verified the deleted issue from Get request in '46543119'

    
     @Regression
  Scenario: Issue API delete issue verification with invalid token
    When I sent the invalid delete request with issue in '46543119'
    Then I verified the delete status code as '401'
    
    