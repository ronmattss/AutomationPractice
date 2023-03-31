Feature: Remove product from Cart
  As a registered user, I can remove a product from my cart

  Scenario:Login on website and Remove 1 T-Shirt
    Given I am logged in with "ronmattss@gmail.com" and "@Ron192000"
    When I View my cart I have 2 products
    Then I should have 1 product in it
