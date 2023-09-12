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
        return homePageHeader.getText();
    }

    public String getProductHeaderText() {
        return productHeader.getText();
    }

    public boolean isShoppingCartLinkEnabled() {
        return shoppingCartLink.isEnabled();
    }

    public boolean isShoppingCartLinkClicked() {
        if (isShoppingCartLinkEnabled()) {
            shoppingCartLink.click();
            return true;
        }
        return false;
    }

    public String sortNameAtoZ() {
        Select sortAtoZ = new Select(productSortContainer);
        sortAtoZ.selectByVisibleText("Name (A to Z)");
        return sauceLabsBackpackText.getText();
    }

    public String sortNameZtoA() {
        Select sortZtoA = new Select(productSortContainer);
        sortZtoA.selectByVisibleText("Name (Z to A)");
        return redTshirtText.getText();
    }

    public List<WebElement> getInventoryItemNames() {
        return inventoryItemNames;
    }

    public List<WebElement> getInventoryItemPrices() {
        return inventoryItemPrices;
    }

    public String getAllItemsText() {
        homePageBurgerMenu.click();
        waiter.waitForVisibilityOf(allItems);
        if (allItems.isEnabled())
            return allItems.getText();
        else
            return null;
    }

    public String getAboutText() {
        homePageBurgerMenu.click();
        waiter.waitForVisibilityOf(about);
        if (about.isEnabled())
            return about.getText();
        else
            return null;
    }

    public String getLogoutText() {
        homePageBurgerMenu.click();
        waiter.waitForVisibilityOf(logout);
        if (logout.isEnabled())
            return logout.getText();
        else
            return null;
    }

    public String getResetAppStateText() {
        homePageBurgerMenu.click();
        waiter.waitForVisibilityOf(resetAppState);
        if (resetAppState.isEnabled())
            return resetAppState.getText();
        else
            return null;
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