package io.saucelabs.portal.qa.pages;

import io.saucelabs.portal.qa.exceptions.WebUtilsException;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class HomePage extends SauceLabsBasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "app_logo")
    private WebElement homePageHeader;

    @FindBy(xpath = "//button[contains(text(), 'Open Menu')]")
    private WebElement homePageBurgerMenu;

    @FindBy(id = "inventory_sidebar_link")
    private WebElement allItems;
    @FindBy(id = "about_sidebar_link")
    private WebElement about;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logout;

    @FindBy(id = "reset_sidebar_link")
    private WebElement resetAppState;

    @FindBy(className = "title")
    private WebElement productHeader;

    @FindBy(className = "shopping_cart_link")
    private WebElement shoppingCartLink;

    @FindBy(className = "product_sort_container")
    private WebElement productSortContainer;

    @FindBy(id = "item_4_title_link")
    private WebElement sauceLabsBackpackText;

    @FindBy(id = "item_3_title_link")
    private WebElement redTshirtText;

    @Getter
    @FindBy(className = "inventory_item_name")
    private List<WebElement> inventoryItemNames;

    @Getter
    @FindBy(className = "inventory_item_price")
    private List<WebElement> inventoryItemPrices;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement addToCardSauceLabsBackpack;

    @FindBy(id = "add-to-cart-sauce-labs-bike-light")
    private WebElement addToCartSauceLabsBikeLight;

    @FindBy(id = "add-to-cart-sauce-labs-bolt-t-shirt")
    private WebElement addToCardSauceLabsBoltTShirt;

    @FindBy(id = "add-to-cart-sauce-labs-fleece-jacket")
    private WebElement addToCartSauceLabsFleeceJacket;

    @FindBy(id = "add-to-cart-sauce-labs-onesie")
    private WebElement addToCartSauceLabsOneSize;

    @FindBy(id = "add-to-cart-test.allthethings()-t-shirt-(red)")
    private WebElement addToCartSauceLabsRedTShirt;

    public String getHomePageHeaderText() {
        return getWebElementText(homePageHeader);
    }

    public String getProductHeaderText() {
        return getWebElementText(productHeader);
    }

    public boolean isShoppingCartLinkEnabled() {
        return isWebElementEnabled(shoppingCartLink);
    }

    public boolean isShoppingCartLinkClicked() {
        clickWebElement(shoppingCartLink);
        return true;
    }

    public String sortNameAtoZ() {
        selectWebElementByVisibleText(productSortContainer, "Name (A to Z)");
        return getWebElementText(sauceLabsBackpackText);
    }

    public String sortNameZtoA() {
        selectWebElementByVisibleText(productSortContainer, "Name (Z to A)");
        return getWebElementText(redTshirtText);
    }

    public String getAllItemsText() {
        clickWebElement(homePageBurgerMenu);
        waiter.waitForVisibilityOf(allItems);
        return getWebElementText(allItems);
    }

    public String getAboutText() {
        clickWebElement(homePageBurgerMenu);
        waiter.waitForVisibilityOf(about);
        return getWebElementText(about);
    }

    public String getLogoutText() {
        clickWebElement(homePageBurgerMenu);
        waiter.waitForVisibilityOf(logout);
        return getWebElementText(logout);
    }

    public String getResetAppStateText() {
        clickWebElement(homePageBurgerMenu);
        waiter.waitForVisibilityOf(resetAppState);
        return getWebElementText(resetAppState);
    }

    private void clickProductInCart(WebElement webElement, String productName) {
        boolean isProductInCart = getInventoryItemNames().stream().map(WebElement::getText).anyMatch(name -> name.equals(productName));
        if (!isProductInCart) {
            throw new WebUtilsException("Product: %s is not in the cart!".formatted(productName));
        }
        clickWebElement(webElement);
    }

    public boolean isProductAddedToCart(String productName) {
        var productMap = Map.ofEntries(
                Map.entry("Sauce Labs Backpack", addToCardSauceLabsBackpack),
                Map.entry("Sauce Labs Bike Light", addToCartSauceLabsBikeLight),
                Map.entry("Sauce Labs Bolt T-Shirt", addToCardSauceLabsBoltTShirt),
                Map.entry("Sauce Labs Fleece Jacket", addToCartSauceLabsFleeceJacket),
                Map.entry("Sauce Labs Onesie", addToCartSauceLabsOneSize),
                Map.entry("Test.allTheThings() T-Shirt (Red)", addToCartSauceLabsRedTShirt)
        );
        WebElement productElement = Optional.ofNullable(productMap.get(productName))
                .orElseThrow(() -> new WebUtilsException("Product: %s is not available!".formatted(productName)));
        clickProductInCart(productElement, productName);
        return true;
    }
}