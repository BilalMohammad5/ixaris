package Zerodha;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageObject extends BaseClass {

    //Landing page Elements

    By players = By.linkText("Players");
    By view = By.linkText("view");
    By playersList = By.xpath("/html/body/app-root/ng-component/div/table/tbody/tr");
    By playerName = By.xpath("//div[@class='col-4 form-inline']/input");
    By addPlayer = By.xpath("//button[@class='btn btn-primary mb-2']");


    public PageObject(WebDriver driver) {
        // TODO Auto-generated constructor stub
        this.driver = driver;  // assigning driver instance from Launch class to local instance
    }

    //returning elements
    public WebElement players() {

        return driver.findElement(players);

    }
    public WebElement playersList() {

        return driver.findElement(playersList);

    }
    public WebElement playerName() {

        return driver.findElement(playerName);

    }

    public WebElement view() {

        return driver.findElements(view).get(0);

    }
    public WebElement addPlayer() {

        return driver.findElement(addPlayer);

    }


}
