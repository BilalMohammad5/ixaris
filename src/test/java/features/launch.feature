Feature: World Cup Home Page

  @smoke @regression @home  @tc1  @view
  Scenario:Launch World cup home page
    Given user launches world cup
    When user is on home screen
    Then user should be able to see welcome message

  @smoke @regression @player  @tc2 @view
  Scenario:user views to players details
    Given user launches world cup
    When user navigates to players tab
    Then user should be able see players details
