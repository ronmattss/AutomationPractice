Feature: I am able to view products on my cart
  Scenario: I am viewing my cart
    Given I have at least 1 product to my cart
    Given I am logged in with "ronmattss@gmail.com" and "@Ron192000" and in the cart view
    When I proceed to checkout
    Then I check if my details are correct
