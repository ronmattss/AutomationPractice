# User needs to Login

Feature: Login to the website
  As a user,
  I want to be able to login to the website,
  so that I can access the product page.

  Scenario Outline: Logging in with valid and invalid credentials
    Given I am on the Login Page
    When I login using "<username>" as my username and "<password>" as my password
    Then I should see  the login result as "<result>"

    Examples:
      | username           | password   | result |
      | ronmattss@gmail.com| @Ron192000 | true   |
      | ronmattss@gmail.com| Ron192000  | false  |
      | ronmattss          | Ron192000  | false  |

