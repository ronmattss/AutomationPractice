Feature: I want to buy a t-shirt

  Background: Iam in the website and I want to login
    Given I am on the homepage
    Then I can Login
    When I use my credentials "testEmailQac@gmail.com" and "qwerty"
    Then I am logged in and can see the products page

  Scenario: User searches for "tshirts" products
    Given I am in the products page
    When I search for "tshirts"
    Then I should be able to see the products on the search results page

  Scenario: User add T-Shirts to Cart
    Given I searched "tshirts"
    When I add 2 products to my cart
    Then I should see 2 t-shirts in my cart

  Scenario: User view cart
    Given I am logged in and in the products page
    When I am  in the cart view
    Then I have at least 2 items in the cart

  Scenario: User remove a product I don't like
    Given I view my cart
    When I have 2 products in my cart
    Then I remove a product and I should have 1 products left

  Scenario: User place order and Download Invoice
    Given I am ready to checkout orders
    When I proceed to checkout and place my order
    And I enter my payment details
    Then I Confirm my order and download my invoice

