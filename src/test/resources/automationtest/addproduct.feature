Feature: Adding T-shirts

  Scenario: Add T-Shirts to Cart
    Given I logged in with my credentials "ronmattss@gmail.com" and "@Ron192000"
    Given I searched "Tshirts"
    When I add 2 products to my cart
    Then I should see 2 t-shirts in my cart