# User needs to Login

  Feature: User goes to Login page
    User will go to the website, then login


    Scenario Outline: User will try to Login
      Given User is on Login Page
      When User will login to the website using "<username>" username and "<password>" password
      Then User will know if they are login if they can see the product page and have a result of "<result>"

      Examples:
      | username           | password  |result |
      | ronmattss@gmail.com| @Ron192000|Success |
      | ronmattss@gmail.com| Ron192000 |Fail    |
      | ronmattss| Ron192000 |Please Insert email Address|


