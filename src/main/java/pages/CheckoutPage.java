package pages;

import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Utils;

public class CheckoutPage {

    private static WebDriver driver;

    private By checkoutButton = By.cssSelector("#checkout");
    private By firstName = By.cssSelector("#first-name");
    private By lastName = By.cssSelector("#last-name");
    private By zipCode = By.cssSelector("#postal-code");
    private By checkoutButton2 = By.cssSelector("#continue");
    private By checkoutButton3 = By.cssSelector("#finish");
    private By message = By.cssSelector(".checkout_complete_container h2");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void proceedToCheckout() throws InterruptedException {
        Utils.clickOnElement(driver,checkoutButton);
    }
    public void insertInfo(){
        Utils.insertData(driver,firstName,"firstName");
        Utils.insertData(driver,lastName,"lastName");
        Utils.insertData(driver,zipCode,"zipCode");
    }

    public void proceedToCheckout2() throws InterruptedException {
        Utils.clickOnElement(driver,checkoutButton2);
    }

    public void proceedToCheckout3() throws InterruptedException {
        Utils.clickOnElement(driver,checkoutButton3);
    }

    public String getStatus() {
        return driver.findElement(message).getText();
    }
}
