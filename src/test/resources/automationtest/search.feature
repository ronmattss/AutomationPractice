Feature: User searches for products on the Products page

  Scenario: User searches for "tshirts" products
    Given User is logged in using valid credentials of "ronmattss@gmail.com" and password "@Ron192000"
    When User navigates to the Products page
    And User searches for "tshirts" products
    Then User should be able to see the "tshirts" products on the search results page