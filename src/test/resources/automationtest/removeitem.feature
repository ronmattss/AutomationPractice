Feature: User wants to remove a product

  Scenario: I want to remove a product I don't like
    Given I am logged in with "ronmattss@gmail.com" and "@Ron192000"
    When I have at least 2 products in my cart
    Then I remove a product and I should have 1 product/s left