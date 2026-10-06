Feature: Application Login

  Background:
    Given the app is launched
    And the user skips the splash screen
    And the user selects "Ya tengo una cuenta"
    And the form login is displayed

  Scenario: Login successfully with valid credentials
    When the user enters a valid email
    And the user enters a valid password
    And the user taps on "Iniciar sesión"
    Then the user should be redirected to the home screen

  Scenario: Login fails with an incorrect password
    When the user enters a valid email
    And the user enters an incorrect password "Dafds123"
    And the user taps on "Iniciar sesión"
    Then the user should see an incorrect password error message