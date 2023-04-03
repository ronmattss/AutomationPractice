Feature: I want to buy a t-shirt

  Background:
    Given I am on the homepage
    Then I can Login

  Scenario: Login on website
    Given I am on the website homepage
    When I use my credentials "ronmattss@gmail.com" and "@Ron192000"
    Then I am logged in and can see the products page

  Scenario: User searches for "tshirts" products
    Given I logged in using "ronmattss@gmail.com" and password "@Ron192000"
    When I  navigate to the Products page
    And I search for "tshirts"
    Then I should be able to see the products on the search results page

  Scenario: Add T-Shirts to Cart
    Given I logged in with my credentials "ronmattss@gmail.com" and "@Ron192000"
    Given I searched "Tshirts"
    When I add 2 products to my cart
    Then I should see 2 t-shirts in my cart

  Scenario:Login on website and Remove 1 T-Shirt
    Given I am logged in with "ronmattss@gmail.com" and "@Ron192000"
    When I have at least 2 products in my cart
    Then I remove a product and I should have 1 products left

  Scenario: Place order and Download Invoice
    Given I logged in with my credentials "ronmattss@gmail.com" and "@Ron192000"
    Given I have at least 1 products in my cart
    Given I am on the Payment page
    When I enter my card details
    Then I am ready to checkout orders

    Scenario: I am ready to checkout
      Given I am in the website and logged in with my credentials "ronmattss@gmail.com" and "@Ron192000"
      Given I have at least 1 product to my cart
      Given I am on the checkout page
    When I proceed to checkout and place my order
    Then I enter my card details
    When I Confirm my order
    Then I should be able to download my invoice
