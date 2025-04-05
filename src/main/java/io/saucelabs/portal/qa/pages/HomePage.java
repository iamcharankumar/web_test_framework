package io.saucelabs.portal.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

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

    @FindBy(className = "inventory_item_name")
    private List<WebElement> inventoryItemNames;

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
        Select sortAtoZ = new Select(productSortContainer);
        sortAtoZ.selectByVisibleText("Name (A to Z)");
        return getWebElementText(sauceLabsBackpackText);
    }

    public String sortNameZtoA() {
        Select sortZtoA = new Select(productSortContainer);
        sortZtoA.selectByVisibleText("Name (Z to A)");
        return getWebElementText(redTshirtText);
    }

    public List<WebElement> getInventoryItemNames() {
        return inventoryItemNames;
    }

    public List<WebElement> getInventoryItemPrices() {
        return inventoryItemPrices;
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

    public boolean isProductAddedToCart(String productName) {
        switch (productName) {
            case "Sauce Labs Backpack":
                if (addToCardSauceLabsBackpack.isEnabled() && getInventoryItemNames().stream().map(WebElement::getText).anyMatch(name -> name.equals(productName)))
                    addToCardSauceLabsBackpack.click();
                break;
            case "Sauce Labs Bike Light":
                if (addToCartSauceLabsBikeLight.isEnabled() && getInventoryItemNames().stream().map(WebElement::getText).anyMatch(name -> name.equals(productName)))
                    addToCartSauceLabsBikeLight.click();
                break;
            case "Sauce Labs Bolt T-Shirt":
                if (addToCardSauceLabsBoltTShirt.isEnabled() && getInventoryItemNames().stream().map(WebElement::getText).anyMatch(name -> name.equals(productName)))
                    addToCardSauceLabsBoltTShirt.click();
                break;
            case "Sauce Labs Fleece Jacket":
                if (addToCartSauceLabsFleeceJacket.isEnabled() && getInventoryItemNames().stream().map(WebElement::getText).anyMatch(name -> name.equals(productName)))
                    addToCartSauceLabsFleeceJacket.click();
                break;
            case "Sauce Labs Onesie":
                if (addToCartSauceLabsOneSize.isEnabled() && getInventoryItemNames().stream().map(WebElement::getText).anyMatch(name -> name.equals(productName)))
                    addToCartSauceLabsOneSize.click();
                break;
            case "Test.allTheThings() T-Shirt (Red)":
                if (addToCartSauceLabsRedTShirt.isEnabled() && getInventoryItemNames().stream().map(WebElement::getText).anyMatch(name -> name.equals(productName)))
                    addToCartSauceLabsRedTShirt.click();
                break;
            default:
                return false;
        }
        return true;
    }
}