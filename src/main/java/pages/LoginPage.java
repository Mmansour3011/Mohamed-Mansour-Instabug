package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utils;

import java.time.Duration;

public class LoginPage{
    private static WebDriver driver;

    private By addButton = By.cssSelector(".orangehrm-header-container button");
    private By item = By.xpath("//a[@class='card'][1]");
    private By emailField = By.cssSelector("#user-name");
    private By passwordField = By.cssSelector("#password");
    private By loginButton = By.cssSelector("#login-button");
    private By homeButton = By.xpath("//a[@data-test='nav-home']");
    private By accountName = By.cssSelector("#menu");

    public LoginPage(WebDriver driver) {
        LoginPage.driver = driver;
    }

    public void login(String username, String password) throws InterruptedException {
        Utils.insertData(driver, emailField, username);
        Utils.insertData(driver, passwordField, password);
        Utils.clickOnElement(driver, loginButton);
    }

    public ShoppingPage goToShoppingPage() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(accountName), "test test"));
        Utils.clickOnElement(driver, homeButton);
        return new ShoppingPage(driver);
    }


}
