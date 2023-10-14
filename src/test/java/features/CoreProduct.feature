Feature: Core Product Tests
  ######- Shop -> Men's  Is not Accessible - Getting Access Denied
#  Scenario: Core Product Test1
#    Given Lauch Core Product Home Page
#    And Navigate To Mens Shop Menu
#    And find all jackets from all the pages
#    And store the jacket info into text file

  @CPTest2
  Scenario: Core Product Test2
    Given Launch Core Product Home Page
    And Navigate To New & Features
    And Count total number of Videos Feeds and print to report
    And count the videos feeds those are present in the page >= 3