Feature: User verify details on checkout page
  Scenario: I verify my purchase in the checkout page
    Given I have a product to buy
    Given I am in the checkout page
    When I checkout my address should be correct
    Then I can place my order