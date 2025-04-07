package io.saucelabs.portal.qa.pages;

import io.saucelabs.portal.qa.exceptions.WebUtilsException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class HomePage extends SauceLabsBasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String getHomePageHeaderText() {
        return getWebElementText(locateElements.getByClassName("app_logo"));
    }

    public String getProductHeaderText() {
        return getWebElementText(locateElements.getByClassName("title"));
    }

    public boolean isShoppingCartLinkEnabled() {
        return isWebElementEnabled(locateElements.getByClassName("shopping_cart_link"));
    }

    public boolean isShoppingCartLinkClicked() {
        clickWebElement(locateElements.getByClassName("shopping_cart_link"));
        return true;
    }

    public String sortNameAtoZ() {
        selectWebElementByVisibleText(locateElements.getByClassName("product_sort_container"), "Name (A to Z)");
        return getWebElementText(locateElements.getById("item_4_title_link"));
    }

    public String sortNameZtoA() {
        selectWebElementByVisibleText(locateElements.getByClassName("product_sort_container"), "Name (Z to A)");
        return getWebElementText(locateElements.getById("item_3_title_link"));
    }

    public String getAllItemsText() {
        clickWebElement(locateElements.getByXpath("//button[contains(text(), 'Open Menu')]"));
        waiter.waitForVisibilityOf(locateElements.getById("inventory_sidebar_link"));
        return getWebElementText(locateElements.getById("inventory_sidebar_link"));
    }

    public String getAboutText() {
        clickWebElement(locateElements.getByXpath("//button[contains(text(), 'Open Menu')]"));
        waiter.waitForVisibilityOf(locateElements.getById("about_sidebar_link"));
        return getWebElementText(locateElements.getById("about_sidebar_link"));
    }

    public String getLogoutText() {
        clickWebElement(locateElements.getByXpath("//button[contains(text(), 'Open Menu')]"));
        waiter.waitForVisibilityOf(locateElements.getById("logout_sidebar_link"));
        return getWebElementText(locateElements.getById("logout_sidebar_link"));
    }

    public String getResetAppStateText() {
        clickWebElement(locateElements.getByXpath("//button[contains(text(), 'Open Menu')]"));
        waiter.waitForVisibilityOf(locateElements.getById("reset_sidebar_link"));
        return getWebElementText(locateElements.getById("reset_sidebar_link"));
    }

    public List<WebElement> getInventoryItemNames() {
        return locateElements.getByClassNameList("inventory_item_name");
    }

    public List<WebElement> getInventoryItemPrices() {
        return locateElements.getByClassNameList("inventory_item_price");
    }

    private void clickProductInCart(WebElement webElement, String productName) {
        boolean isProductInCart = getInventoryItemNames().stream().map(WebElement::getText).anyMatch(product -> product.equals(productName));
        if (!isProductInCart)
            throw new WebUtilsException("Product: %s is not in the cart!".formatted(productName));
        clickWebElement(webElement);
    }

    public boolean isProductAddedToCart(String productName) {
        var productMap = Map.ofEntries(
                Map.entry("Sauce Labs Backpack", locateElements.getById("add-to-cart-sauce-labs-backpack")),
                Map.entry("Sauce Labs Bike Light", locateElements.getById("add-to-cart-sauce-labs-bike-light")),
                Map.entry("Sauce Labs Bolt T-Shirt", locateElements.getById("add-to-cart-sauce-labs-bolt-t-shirt")),
                Map.entry("Sauce Labs Fleece Jacket", locateElements.getById("add-to-cart-sauce-labs-fleece-jacket")),
                Map.entry("Sauce Labs Onesie", locateElements.getById("add-to-cart-sauce-labs-onesie")),
                Map.entry("Test.allTheThings() T-Shirt (Red)", locateElements.getById("add-to-cart-test.allthethings()-t-shirt-(red)"))
        );
        WebElement productElement = Optional.ofNullable(productMap.get(productName))
                .orElseThrow(() -> new WebUtilsException("Product: %s is not available!".formatted(productName)));
        clickProductInCart(productElement, productName);
        return true;
    }
}