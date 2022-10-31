Feature: Login feature of FeDex. All users can be logged in with their valid credentials but couldn't be with invalid ones

  Scenario: : Successful Login
    Given Choose Location Page
    And Select "English" language for TURKEY
    And Go to login page
    When typing VALID_USER_ID in the user ID input
    And typing VALID_PASSWORD in the password input
    And Click login button
    Then The user should be log in successfully

  Scenario: : Invalid password
    Given Go to login page
    When typing VALID_USER_ID in the user ID input
    And typing INVALID_PASSWORD in the password input
    And Click login button
    Then Verify that the error message is "invalidCredentials"

  Scenario: : Invalid user id
    Given Go to login page
    When typing INVALID_USER_ID in the user ID input
    And typing VALID_PASSWORD in the password input
    And Click login button
    Then Verify that the error message is "invalidCredentials"

  Scenario: : No credentials
    Given Go to login page
    And Click login button
    Then Verify that the error message is "user_id_and_password_is_required"