#I can add 2 T-Shirts I like to cart. I can remove 1 tshirt from the cart

Feature: Purchasing T-Shirts

  As a registered user,
  I want to purchase T-Shirts from the website,
  So that I can have them delivered to me.

  Scenario: Place order and Download Invoice
    Given I logged in with my credentials "ronmattss@gmail.com" and "@Ron192000"
    And I am ready to checkout orders
    When I proceed to checkout and place my order
    Then I enter my card details
    When I Confirm my order
    Then I should be able to download my invoice

