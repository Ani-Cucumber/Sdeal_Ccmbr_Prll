@Homescreen
Feature: Filter Books and sort Dress

  @Books
  Scenario Outline: Select  Thriller novels and Educational books having more than 4 Stars in Reviews
                    Given user is at Homescreen
                    When user searches for "<searchTerm>"
                    And user taps on Filter
                    And selects Customer rating of 4 and above
                    And discount of "<discountPercent>"
                    And taps om Apply Filter
                    Then user is moved to result screen
                    And screen has matching labels
  Examples:
  | searchTerm         | discountPercent |
  | Thriller novels    | 0-10            |
  | Educational books  | 0-10            |


  @Clothes
  Scenario Outline: Select Dress and arrange them by discount
    Given user is at Homescreen
    When user searches for "<searchTerm>"
    Then user is moved to result screen
    And selects size of "<size>"
    And taps om Apply Filter
    When user applies Sort by discount
    Then user is shown sorted list
    And user can see scratched price and new price

    Examples:
      | searchTerm   | size  |
      | Jeans        |  34   |
      | Denim Shirt  |  42   |
