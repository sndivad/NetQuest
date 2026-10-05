Feature: Application Login

  Background:
    Given The User is on the login page
    When the user press the burger button
    And the user press log in button
    Then The form login is displayed

  Scenario: Login with a valid email and password
    When the user enters a valid email
    And  the user enters a valid password
    And the user tap log in button
    Then the home page should be displayed

  Scenario: Login with an invalid password
    When the user enters a valid email
    And  the user enters an invalid password "Davi444dd@"
    And the user tap log in button
    Then invalid password or user should be displayed