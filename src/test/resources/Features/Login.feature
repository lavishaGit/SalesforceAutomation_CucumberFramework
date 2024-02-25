Feature: Login Page Functionality

  Background: 
    Given User is on Landing Page
    When User enters  valid "UserName" in a User name field

  #Structure of the screnario
  Scenario: Validate Error Message when Password is empty
    And leaves Password field empty
    And clicks login button
    Then Verify User should see "Please enter your password." error message
    And Verify user should  remain on a Login Page

  Scenario: Validate User login sucessfully
    And enters valid "Password" in a password field
    And clicks login button
    Then User should redirects to Home Page
    And User should verify the home page title

  Scenario: Validate User Sees Username After Relogin with Remember Me Checked
    And enters valid "Password" in a password field and clicks Remember me checkbox
    And clicks login button
    Then User should redirects to Home Page
    And clicks on User account link and clicks on logout button
    And Verify User redirects to the login page with  prefilled "Username" in  username field

  Scenario: Validate User can  Reset the forgot password
    Then clicks Forgot Password button
    And clicks on Continue button
    And Verify User redirects to the Reset Password Page with Page Title "Check Your Email | Salesforce"

  Scenario: Validate Error Message when Password is invalid
    And enters invalid Password "22345" in a password field
    And clicks login button
    Then Verify User should see the message "Please check your username and password. If you still can't log in, contact your Salesforce administrator."
    And Verify user should  remain on a Login Page
