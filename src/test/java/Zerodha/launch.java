package Zerodha;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Listeners(listernerConfig.Listeners.class)
public class launch extends BaseClass {


    public static Logger log = LogManager.getLogger(BaseClass.class.getName());
    String expectedTitle = "WorldCupAppFrontendAngular";
    String expectedTitlePlayers = "Players List";
    String expectedTitlePlayerInfo = "Player Info";
    String title;
    String baseurl = "http://localhost:4200/"; //locally hosted world cup app url
    String playersURL = "http://localhost:4200/players"; //locally hosted world cup app url
    WebDriver driver = initDriver();
    PageObject element = new PageObject(driver); // Creating Page Object reference

    public launch() throws SQLException, IOException, ClassNotFoundException {
    }

    @Given("user launches world cup")

    public void User_launches_world_cup() throws ClassNotFoundException, IOException, SQLException {

        driver.get(baseurl);
    }

    @When("user is on home screen")
    public void user_is_on_home_screen() {

        try {
            WebDriverWait wait = new WebDriverWait(driver, 30); //Explicit wait
            wait.until(ExpectedConditions.titleIs(expectedTitle));
            title = driver.getTitle();
            System.out.println("Actual title from the test is " + "  " + title);
        } catch (Exception e) {
            e.printStackTrace();
            String url = driver.getCurrentUrl();
            if (baseurl != url) {
                driver.get(baseurl);
                title = driver.getTitle();
                System.out.println("Actual title from the test is " + "  " + title);
            } else {
                System.out.println("user is on wrong domain" + "  " + title);
            }


        }
    }

    @Then("user should be able to see welcome message")
    public void welcome_message() {

        Assert.assertEquals(title, expectedTitle);
        System.out.println("user is on correct domain - login succesful");

    }

    @When("user navigates to players tab")
    public void players_tab() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 30); //Explicit wait
            wait.until(ExpectedConditions.elementToBeClickable(element.players()));
            element.players().click();
            System.out.println("players icon clicked");


        } catch (NoSuchElementException e) {
            driver.navigate().refresh();
            JavascriptExecutor js = (JavascriptExecutor) driver;

            js.executeScript("arguments[0].click();", element.players()); // clicking players icon using java script executor

            System.out.println("players icon clicked");

        } finally {
            System.out.println("user navigated to players screen");
        }

    }


    @Then("user should be able see players details")
    public void players_details() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 30); //Explicit wait
            wait.until(ExpectedConditions.elementToBeClickable(element.view()));

            List<WebElement> playersList = new ArrayList<>();
            playersList = driver.findElements(By.xpath("/html/body/app-root/ng-component/div/table/tbody/tr"));

            for (WebElement element : playersList) {
                String player = element.findElement(By.xpath("/html/body/app-root/ng-component/div/table/tbody/tr")).getText();
                System.out.println(player);
                Assert.assertTrue(player.contains("Melvin"));  //assertion for players data

            }



        } catch (NoSuchElementException e) {
            driver.navigate().refresh();
            JavascriptExecutor js = (JavascriptExecutor) driver;

            js.executeScript("arguments[0].click();", element.view()); // clicking players icon using java script executor
            List<WebElement> playersList = new ArrayList<>();
            playersList = driver.findElements(By.xpath("/html/body/app-root/ng-component/div/table/tbody/tr"));

            for (WebElement element : playersList) {
                String player = element.findElement(By.xpath("/html/body/app-root/ng-component/div/table/tbody/tr")).getText();
                System.out.println(player);
                Assert.assertTrue(player.contains("Melvin"));  //assertion for players data

            }

        } finally {
            System.out.println("user can view all players details");
        }

    }

    @Then("user enters player name")
    public void user_enters_player_name(){
        try {
            element.playerName().sendKeys("test player");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Then("user selects player team from dropdwon")
    public void team_dropdown(){
        Select objSelect =new Select(driver.findElement(By.className("custom-select ng-pristine ng-valid ng-touched")));
        objSelect.selectByVisibleText("Spain"); //selecting country drop down

    }
    @When("user clicks add player")
    public void add_player(){
        try {
            element.addPlayer().click();
            System.out.println("Add player clicked");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Then("user should be able to see newly added player details in the list")
    public void new_player_verify() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 30); //Explicit wait
            wait.until(ExpectedConditions.elementToBeClickable(element.view()));

            List<WebElement> playersList = new ArrayList<>();
            playersList = driver.findElements(By.xpath("/html/body/app-root/ng-component/div/table/tbody/tr"));

            for (WebElement element : playersList) {
                String player = element.findElement(By.xpath("/html/body/app-root/ng-component/div/table/tbody/tr")).getText();
                System.out.println(player);
                Assert.assertTrue(player.contains("test player"));  //assertion for players data

            }



        } catch (NoSuchElementException e) {
            driver.navigate().refresh();
            JavascriptExecutor js = (JavascriptExecutor) driver;

            js.executeScript("arguments[0].click();", element.view()); // clicking players icon using java script executor
            List<WebElement> playersList = new ArrayList<>();
            playersList = driver.findElements(By.xpath("/html/body/app-root/ng-component/div/table/tbody/tr"));

            for (WebElement element : playersList) {
                String player = element.findElement(By.xpath("/html/body/app-root/ng-component/div/table/tbody/tr")).getText();
                System.out.println(player);
                Assert.assertTrue(player.contains("test player"));  //assertion for players data

            }

        } finally {
            System.out.println("user addition succesful");
        }

    }
}
