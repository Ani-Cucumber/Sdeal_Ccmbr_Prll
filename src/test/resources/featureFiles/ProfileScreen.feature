@ProfileScreen
Feature: Login & check Legal Policies

@invalidNumber
Scenario Outline: User tries to login with invalid numbers
                  Given user is at Homescreen
                  When user taps at Profile tab
                  And taps on Signup button
                  Then user is asked for mobileNumber
                  When user enters number "<number>"
                  And taps on Continue button
                  Then user is not asked for name
  Examples:
  | number     | message                                                           |
  |            | Please enter a valid mobile number to continue                    |
  | 5656       | Please enter a valid mobile number to continue                    |
  | 5656565656 | Your mobile number should start with 6,7,8 or 9. Please try again |


@validNumber
Scenario Outline: User tries to login with valid number
          Given user is at Homescreen
          When user taps at Profile tab
          And taps on Signup button
          Then user is asked for mobileNumber
          When user enters number "<number>"
          And taps on Continue button
          Then user is asked for name
          When user enters "<name>"
          And  taps on Continue button
          Then user is moved to Verify OTP screen
Examples:
| number       | name  |
| 7550134740   | Anil  |

@legal
Scenario: User taps on Legal Policies
          Given user is at Homescreen
          When user taps at Profile tab
          Then Terms button is not visible
          And  Report button is not visible
          When user taps on Legal button
          Then Terms button is visible
          And Report button is visible
