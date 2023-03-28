# User needs to Login

Feature: User goes to Login page
  As a user
  I want to login to the website
  So that I can access the product page

  Scenario Outline: User logs in with valid and invalid credentials
    Given the user is on the Login Page
    When the user logs in with username "<username>" and password "<password>"
    Then the user should see the product page and the login result should be "<result>"

    Examples:
      | username           | password   | result |
      | ronmattss@gmail.com| @Ron192000 | true   |
      | ronmattss@gmail.com| Ron192000  | false  |
      | ronmattss          | Ron192000  | false  |

