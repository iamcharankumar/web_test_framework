package io.saucelabs.portal.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends SauceLabsBasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getYourCartTitle() {
        return getWebElementText(locateElements.getByClassName("title"));
    }

    public int getCartProductCount() {
        return Integer.parseInt(getWebElementText(locateElements.getByClassName("shopping_cart_badge")));
    }

    public String getCartQuantityText() {
        return getWebElementText(locateElements.getByClassName("cart_quantity_label"));
    }

    public String getCartDescriptionText() {
        return getWebElementText(locateElements.getByClassName("cart_desc_label"));
    }

    public List<WebElement> getInventoryItemNames() {
        return locateElements.getByClassNameList("inventory_item_name");
    }

    public List<WebElement> getInventoryItemPrices() {
        return locateElements.getByClassNameList("inventory_item_price");
    }

    public List<WebElement> getInventoryItemDescriptions() {
        return locateElements.getByClassNameList("inventory_item_desc");
    }
}