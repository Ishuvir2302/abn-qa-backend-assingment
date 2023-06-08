#Author: Ishuvir singh

@E2E
Feature: Gitlab issue Api with update operation
  The purpose of this feature is to test the Gitlab issue Api Update operations

  @Regression
  Scenario Outline: Issue title update validation in gitlab issue api
  Given I created the put request with updated <title>
  When I updated the <issue> in <project>
  Then I verified response Status_as '200'
  Then <issue> is updated with <title> 
  
  Examples:
  | project | issue |     title    |
  | 46543056 |  38  |  "TestAutomation2"  |    
  | 46543056 |  38  |  "TestAutomationTitleUpdate"  | 
 
 @Sanity
  Scenario Outline: Issue title update validation with invalid token
  Given I created the put request with invalid token
  When I updated the <issue> in <project>
  Then I verified response Status_as '401'
  And I verified status line code 
  
  Examples:
  | project | issue |     title    |
  | 46543056 |  38  |  "TestAutomation2"  |    
  
 

  @Regression
  Scenario Outline: Issue due date update validation in gitlab issue api
  Given I created the put request with updated <dueDate>
  When I updated the <issue> in <project>
  Then I verified response Status_as '200'
  Then <issue> is updated with <dueDate> 
  
  Examples:
  | project | issue |     dueDate    |
  | 46643991 |  2  |  "2023-08-10"  |    
  | 46643991 |  2  |  "2023-09-26" | 
 
 
 