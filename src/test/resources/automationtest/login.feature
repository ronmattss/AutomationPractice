# User needs to Login

Feature: Login to the website
  As a user,
  I want to be able to login to the website,
  so that I can access the product page.

  Scenario: Login on website
    Given I am on the website homepage
    When I use my credentials "ronmattss@gmail.com" and "@Ron192000"
    Then I am logged in and can see the products page

