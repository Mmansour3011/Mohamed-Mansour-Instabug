package StepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.BasePage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ShoppingPage;

import java.util.Arrays;

public class StepDefinitions {
    private WebDriver driver;
    private LoginPage loginPage;
    private ShoppingPage shoppingPage;
    private CheckoutPage checkoutPage;

    @Before
    public void setUp() {
        BasePage.setUp();
        driver = BasePage.driver;
        loginPage = new LoginPage(driver);
        shoppingPage = new ShoppingPage(driver);
    }

//    @After
    public void tearDown() {
        BasePage.tearDown();
    }

    @Given("I log in with username {string} and password {string}")
    public void i_log_in_with_username_and_password(String username, String password) throws InterruptedException {
        loginPage.login(username, password);

    }

    @When("I add an item to cart")
    public void i_add_an_item_to_cart() {
        shoppingPage.addItemsToCart(new int[]{0});
    }

    @And("Go to checkout page")
    public void go_to_checkout_page() throws InterruptedException {
        checkoutPage = shoppingPage.goToCheckoutPage();
        completeCheckoutProcess();
    }

    private void completeCheckoutProcess() throws InterruptedException {
        checkoutPage.proceedToCheckout();
        checkoutPage.insertInfo();
        checkoutPage.proceedToCheckout2();
        checkoutPage.proceedToCheckout3();
    }

    @When("Go to checkout page without adding info")
    public void go_to_checkout_page_without_adding_info() throws InterruptedException {
        checkoutPage = shoppingPage.goToCheckoutPage();
        checkoutPage.proceedToCheckout();
        checkoutPage.proceedToCheckout2();
    }

    @Then("The process is completed")
    public void the_process_is_completed() {
        String paymentStatus = checkoutPage.getStatus();
        Assert.assertEquals(paymentStatus, "Thank you for your order!", "Expected order completion but got: " + paymentStatus);
    }

    @Then("Checkout process doesn't get completed")
    public void checkout_process_doesnt_get_completed() {
        String paymentStatus = checkoutPage.getStatus();
        Assert.assertEquals(paymentStatus, "The checkout process failed.", "Checkout process should have failed but got: " + paymentStatus);
    }

    @Then("The item is added successfully to cart")
    public void the_item_is_added_successfully_to_cart() {
        int cartItemCount = shoppingPage.getCartItemsCount();
        Assert.assertEquals(cartItemCount, 1, "Expected 1 item in cart, but found: " + cartItemCount);
    }

    @Then("The item is removed successfully from cart")
    public void the_item_is_removed_successfully_from_cart() {
        int cartItemCount = shoppingPage.getCartItemsCount();
        Assert.assertEquals(cartItemCount, 0, "Expected no items in cart, but found: " + cartItemCount);
    }

    @When("I remove the item from cart")
    public void i_remove_the_item_from_cart() {
        shoppingPage.removeItemsFromCart(new int[]{0});
    }

    @When("I click sort by price from low to high")
    public void i_click_sort_by_price_from_low_to_high() {
        shoppingPage.sortByPriceLowToHigh();
    }

    @When("I add no items to cart")
    public void i_add_no_items_to_cart() {
    }

    @Then("Items are sorted successfully")
    public void items_are_sorted_successfully() {
        boolean isSorted = shoppingPage.areItemsSortedByPriceLowToHigh();
        Assert.assertTrue(isSorted, "Items are not sorted correctly by price.");
    }

    @Then("Error message appears")
    public void error_message_appears() {
        String errorMessage = checkoutPage.getStatus();
        Assert.assertTrue(errorMessage.contains("Error: First Name is required"), "Expected user info error, but got: " + errorMessage);
    }
}
