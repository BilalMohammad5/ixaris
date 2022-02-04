Feature: World Cup Home Page

  @smoke @regression @home  @tc1  @view @test
  Scenario:Launch World cup home page
    Given user launches world cup
    When user is on home screen
    Then user should be able to see welcome message

  @smoke @regression @player  @tc2 @view @test
  Scenario:user views to players details
    Given user launches world cup
    When user navigates to players tab
    Then user should be able see players details

  @smoke @regression @player  @tc3 @view
  Scenario:user views player info
    Given user launches world cup
    When user navigates to players tab
    Then user should be able see players details
    When user clicks view
    Then user should be able to see player info

  @smoke @regression @player  @tc4 @edit @test
  Scenario:user adds new player
    Given user launches world cup
    When user navigates to players tab
    Then user should be able see players details
    Then user enters player name
    Then user selects player team from dropdwon
    When user clicks add player
    Then user should be able to see newly added player details in the list


  @smoke @regression @player  @tc5 @view
  Scenario:user views to teams details
    Given user launches world cup
    When user navigates to teams tab
    Then user should be able to see team details

  @smoke @regression @player  @tc6 @view
  Scenario:user views team info
    Given user launches world cup
    When user navigates to teams tab
    Then user should be able see teams details
    When user clicks view
    Then user should be able to see team info

  @smoke @regression @player  @tc7 @edit
  Scenario:user adds new team
    Given user launches world cup
    When user navigates to teams tab
    Then user should be able see team details
    Then user enters team name
    When user clicks add team
    Then user should be able to see newly added team details in the list
