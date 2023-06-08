#Author: Ishuvir singh
@E2E
Feature: Gitlab issue Api with create operation
  The purpose of this feature is to test the Gitlab issue Api create operations

  @Regression @Sanity
  Scenario Outline: Validating new issue creation in Issue API
    Given I created the POST request with Valid token
    When I created issue in <projectNumber>
    Then I verified response Status_is '201'
    Then Issue ID is generated in <<projectNumber>>

    Examples: 
      | projectNumber |
      | "46543056"    |
      | "46543119"    |

  @Regression
  Scenario Outline: Validating new issue creation with invalid OATH2 token
    Given I created the POST request with inValid token
    When I created issue in <projectNumber>
    Then I verified response Status_is '401'
    And I verified response Body throws "401 Unauthorized"

    Examples: 
      | projectNumber |
      | "46543056"    |

  @Sanity
  Scenario Outline: Moving issue to other project insufficient permission verification
    Given I created the POST request with <destinationProject>
    When I moved <issue> to <projectNumber>
    Then I verified response Status_is '401'

    Examples: 
      | projectNumber | issue | destinationProject |
      |      46543056 |    27 |           46643991 |

  @Regression
  Scenario: Subscribing to an issue verifcation
    Given I created the POST request with Valid OATH2 token
    When I sent the to subscribe issue '30' in project '46543056'
    Then I verified subscribe Status
    And I validate the status line code

  @Regression
  Scenario: Unsubscribing to an issue verifcation
    Given I created the POST request with Valid OATH2 token
    When I sent the to unsubscribe issue '30' in project '46543056'
    Then I verified subscribe Status
    And I validate the status line code
    And I validate the subscribe status in response body
