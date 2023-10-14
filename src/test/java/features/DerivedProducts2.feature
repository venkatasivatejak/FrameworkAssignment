Feature: Derived Product2 Tests

  @DP2Test1
  Scenario: Derived Product2 Test1
    Given Launch DP2 Home Page
    And Scroll down to the footer
    Then Find all the hyperlinks of the Footer links into a CSV file "footerlinks.csv" and report duplicates
    And Embed CSV file "footerlinks.csv" as attachment to the html report