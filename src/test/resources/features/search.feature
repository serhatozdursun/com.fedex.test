Feature: search function on home page

  @SearchSanity @RegressionTest
  Scenario Outline: when a keyword search is performed with the search box in the header of the home page,
  the search page should be displayed and all relevant content should be populated on the page.
  If there is more than ten results, there should be pagination.

    Given The <keyword> as a keyword to type in search-box on the home page
    When Click the search button magnifying glass icon
    Then The search page should be display
    And Check if there is more than ten results and pagination
    Examples:
      | keyword                                            |
      | "rates"                                            |
      | "Customs Clearance Guide \| Export \| FedEx China" |

  @SearchSanity @RegressionTest @Smoke
  Scenario: The user should be able to navigate between the pages by right, left arrow, if there is more than ten result.
    Given The "rates" as a keyword to type in search-box on the home page
    When Click the search button magnifying glass icon
    Then The search page should be display
    And Should be able to navigate right and left between pages
