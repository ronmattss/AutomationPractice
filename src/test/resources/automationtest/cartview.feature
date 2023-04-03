Feature: I am able to view products on my cart
  Scenario: I am viewing my cart
    Given I am logged in with "ronmattss@gmail.com" and "@Ron192000"
    When I am and in the cart view
    Then I have at least 2 items in the cart