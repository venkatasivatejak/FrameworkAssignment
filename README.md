# FrameworkAssignment
Cucumber Testng Selenium Framework for web automation

Preferred Java Version : JDK-11

Libraries Used:
    
    Selenium4 - Web automation
    TestNG - Testing framework &  Execution Strategy
    Cucumber - Draft Tests & Reporting
    Owner - properties/config file management
    
Sample Test:

    @CPTest2
    Scenario: Core Product Test2
    Given Launch Core Product Home Page
    And Navigate To New & Features
    And Count total number of Videos Feeds and print to report
    And count the videos feeds those are present in the page >= 3

To Run Web Tests:

        Full Regression:
            mvn clean test -Prun-testng-xml -Dtestngxmlfile=src/test/resources/testngrunners/fullregression.xml
        
        Selective Tests:
            Eg: To run the sample test @CPTest2 above
            mvn clean test -Prun-testng-xml -Dtestngxmlfile=src/test/resources/testngrunners/selectivetestrun.xml -Dcucumber.filter.tags=@DP2Test1

        Selective Features:
            mvn clean test -Prun-testng-xml -Dtestngxmlfile=src/test/resources/testngrunners/selectivefeature.xml -Dcucumber.features=src/test/java/features/CoreProduct.feature
        Note: run-testng-xml is surefire run profile
        


    