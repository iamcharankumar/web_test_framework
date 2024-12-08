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
        String homePageHeaderText = homePageHeader.getText();
        validateNonEmptyText(homePageHeaderText, "Home Page Header Text is empty!");
        return homePageHeaderText;
    }

    public String getProductHeaderText() {
        String productHeaderText = productHeader.getText();
        validateNonEmptyText(productHeaderText, "Product Header Text is empty!");
        return productHeaderText;
    }

    public boolean isShoppingCartLinkEnabled() {
        validateAction(shoppingCartLink.isEnabled(), "Shopping Cart Link is not enabled!");
        return true;
    }

    public boolean isShoppingCartLinkClicked() {
        clickElement(shoppingCartLink, "Shopping Cart Link is not clicked!");
        return true;
    }

    public String sortNameAtoZ() {
        Select sortAtoZ = new Select(productSortContainer);
        sortAtoZ.selectByVisibleText("Name (A to Z)");
        String backPackText = sauceLabsBackpackText.getText();
        validateNonEmptyText(backPackText, "Backpack Text is empty!");
        return backPackText;
    }

    public String sortNameZtoA() {
        Select sortZtoA = new Select(productSortContainer);
        sortZtoA.selectByVisibleText("Name (Z to A)");
        String redTShirt = redTshirtText.getText();
        validateNonEmptyText(redTShirt, "Red Tshirt Text is empty!");
        return redTShirt;
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
        validateAction(allItems.isEnabled(), "All Items not enabled!");
        return allItems.getText();
    }

    public String getAboutText() {
        homePageBurgerMenu.click();
        waiter.waitForVisibilityOf(about);
        validateAction(about.isEnabled(), "About not enabled!");
        return about.getText();
    }

    public String getLogoutText() {
        homePageBurgerMenu.click();
        waiter.waitForVisibilityOf(logout);
        validateAction(logout.isEnabled(), "Logout is not enabled!");
        return logout.getText();
    }

    public String getResetAppStateText() {
        homePageBurgerMenu.click();
        waiter.waitForVisibilityOf(resetAppState);
        validateAction(resetAppState.isEnabled(), "Reset App State not enabled!");
        return resetAppState.getText();
    }

    public boolean isProductAddedToCart(String productName) {
        boolean isProductPresent = inventoryItemNames.stream().map(WebElement::getText).anyMatch(name -> name.equals(productName));
        validateAction(isProductPresent, "Sauce Labs Backpack not present!");
        switch (productName) {
            case "Sauce Labs Backpack":
                clickElement(addToCardSauceLabsBackpack, "Sauce Labs Backpack not clicked!");
                break;
            case "Sauce Labs Bike Light":
                clickElement(addToCartSauceLabsBikeLight, "Sauce Labs Bike Light not clicked!");
                break;
            case "Sauce Labs Bolt T-Shirt":
                clickElement(addToCardSauceLabsBoltTShirt, "Sauce Labs Bolt T-Shirt not clicked!");
                break;
            case "Sauce Labs Fleece Jacket":
                clickElement(addToCartSauceLabsFleeceJacket, "Sauce Labs Fleece Jacket not clicked!");
                break;
            case "Sauce Labs Onesie":
                clickElement(addToCartSauceLabsOneSize, "Sauce Labs Onesie not clicked!");
                break;
            case "Test.allTheThings() T-Shirt (Red)":
                clickElement(addToCartSauceLabsRedTShirt, "Test.allTheThings() T-Shirt (Red) not clicked!");
                break;
            default:
                return false;
        }
        return true;
    }
}