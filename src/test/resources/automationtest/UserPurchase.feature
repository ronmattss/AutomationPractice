Feature: User buys a product
  A registered user buys a product

  Scenario: User buys tshirt
    Given I am on the homepage
    Then  I can Login
    When I use my credentials "testEmailQac@gmail.com" and "qwerty"
    Then I am logged in and can see the products page
    Given I am in the products page
    When I search for "tshirts"
    Then I should be able to see the products on the search results page
    When I add 2 products to my cart
    Then I should see 2 t-shirts in my cart
    When I am in the cart view
    Then I have at least 2 items in the cart
    When I remove a product and I should have 1 products left
    Then I proceed to checkout and place my order
    And I enter my payment details
    Then I Confirm my order and download my invoice