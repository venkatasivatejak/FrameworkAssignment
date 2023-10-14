Feature: Derived Product1 Tests

  @DP1Test1
  Scenario: Derived Product1 Test1
    Given Launch DP1 Home Page
    Then verify number of slides present are 4
    Then validate each slide title with expected test data
      | Slide Title                                |
      | Sixers Fall To Celtics At Home             |
      | Media Day Recap                            |
      | 23-24 Single Game Tickets Are On Sale Now  |
      | Important Dates and Key Matchups for 23-24 |
    Then Count and validate duration of each slide with the expected duration
      | Slide Title                                | Duration |
      | Sixers Fall To Celtics At Home             | 10       |
      | Media Day Recap                            | 10       |
      | 23-24 Single Game Tickets Are On Sale Now  | 10       |
      | Important Dates and Key Matchups for 23-24 | 10       |