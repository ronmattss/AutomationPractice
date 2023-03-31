Feature: User searches for products on the Products page

  Scenario: User searches for "tshirts" products
    Given I logged in using "ronmattss@gmail.com" and password "@Ron192000"
    When I  navigate to the Products page
    And I search for "tshirts"
    Then I should be able to see the products on the search results page