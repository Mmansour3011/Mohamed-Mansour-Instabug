package pages;

import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Utils;

import java.util.List;

public class ShoppingPage {
    private static WebDriver driver;
    private By cart = By.cssSelector("#shopping_cart_container");
    private By addItemButtons = By.xpath("//button[@class='btn btn_primary btn_small btn_inventory ']");
    private By removeItemButtons = By.xpath("//button[contains(@id, 'remove')]");
    private By cartBadge = By.cssSelector(".shopping_cart_badge");
    private By sortProducts = By.cssSelector(".product_sort_container");
    private By itemPrices = By.cssSelector(".inventory_item_price");


    private static final Logger LOGGER = Logger.getLogger(ShoppingPage.class.getName());


    public ShoppingPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getAddItems() {
        List<WebElement> items = driver.findElements(addItemButtons);
        if (items.isEmpty()) {
            LOGGER.warning("No items found to add to cart");
        }
        return items;
    }

    public List<WebElement> getRemoveItems() {
        List<WebElement> items = driver.findElements(removeItemButtons);
        if (items.isEmpty()) {
            LOGGER.warning("No items found to remove from cart");
        }
        return items;
    }

    // Add specific items to the cart by their index in the item list
    public void addItemsToCart(int[] items) {
        List<WebElement> availableItems = getAddItems();
        if (availableItems.isEmpty()) {
            LOGGER.warning("No items available to add to cart");
            return;
        }

        for (int index : items) {
            if (index >= 0 && index < availableItems.size()) {
                LOGGER.info("Adding item at index: " + index);
                availableItems.get(index).click();
            } else {
                LOGGER.warning("Invalid item index: " + index);
            }
        }
    }

    public void removeItemsFromCart(int[] items) {
        List<WebElement> availableItems = getRemoveItems();
        if (availableItems.isEmpty()) {
            LOGGER.warning("No items available to add to cart");
            return;
        }

        for (int index : items) {
            if (index >= 0 && index < availableItems.size()) {
                LOGGER.info("Removing item at index: " + index);
                availableItems.get(index).click();
            } else {
                LOGGER.warning("Invalid item index: " + index);
            }
        }
    }

    public CheckoutPage goToCheckoutPage() throws InterruptedException {
        Utils.clickOnElement(driver, cart);
        return new CheckoutPage(driver);
    }

    public int getCartItemsCount() {
        List<WebElement> cartSizeElements = driver.findElements(cartBadge);

        // If the cartBadge element is not found, return 0
        if (cartSizeElements.isEmpty()) {
            LOGGER.warning("No cart badge found. Returning 0.");
            return 0;
        }

        // If the element is found, parse and return the cart size
        WebElement cartSizeElement = cartSizeElements.get(0);
        try {
            int cartSize = Integer.parseInt(cartSizeElement.getText());
            return cartSize;
        } catch (NumberFormatException e) {
            LOGGER.severe("Failed to parse cart size. Returning 0.");
            return 0;
        }
    }

    public void sortByPriceLowToHigh() {
        Utils.selectFromDropDownList(driver, sortProducts, "Price (low to high)");
    }

    public boolean areItemsSortedByPriceLowToHigh() {
        List<WebElement> items = driver.findElements(itemPrices);
        double firstItemPrice = Double.parseDouble(items.get(0).getText().replace("$", ""));
        double lastItemPrice = Double.parseDouble(items.get(items.size() - 1).getText().replace("$", ""));

        if (firstItemPrice < lastItemPrice)
            return true;
        else return false;
    }
}
