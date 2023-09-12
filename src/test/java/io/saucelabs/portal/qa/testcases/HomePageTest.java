package io.saucelabs.portal.qa.testcases;

import io.saucelabs.portal.qa.testdata.SauceLabsDataProvider;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@Slf4j
public class HomePageTest extends SauceLabsPortalTestBase {

    @Test(description = "To verify, the home page header text.")
    public void testHomePageHeaderText() {
        String homePageHeaderText = SAUCELABS_PORTAL.get().HOME_PAGE.getHomePageHeaderText();
        Assert.assertEquals(homePageHeaderText, "Swag Labs", "Incorrect Home Page Header Text!");
        log.info("Verified the Home Page Header Text {}", homePageHeaderText);
    }

    @Test(description = "To verify, the home page products header text.")
    public void testHomePageProductsText() {
        String productsText = SAUCELABS_PORTAL.get().HOME_PAGE.getProductHeaderText();
        Assert.assertEquals(productsText, "Products", "Incorrect Products Header Text!");
        log.info("Verified the Home Page Products Text {}", productsText);
    }

    @Test(description = "To verify, the shopping cart link is enabled.")
    public void testShoppingCartLink() {
        boolean isShoppingCartLinkEnabled = SAUCELABS_PORTAL.get().HOME_PAGE.isShoppingCartLinkEnabled();
        Assert.assertTrue(isShoppingCartLinkEnabled);
        log.info("Verified the Shopping Card Link Enabled Status {}", isShoppingCartLinkEnabled);
    }

    @Test(description = "To verify, the first product name by sorting A-Z.")
    public void testProductSortAtoZ() {
        String firstProductName = SAUCELABS_PORTAL.get().HOME_PAGE.sortNameAtoZ();
        Assert.assertEquals(firstProductName, "Sauce Labs Backpack", "First Product Name Mismatched!");
        log.info("Verified the First Product Name {} sorted A to Z.", firstProductName);
    }

    @Test(description = "To verify, the first product name by sorting Z-A.")
    public void testProductSortZtoA() {
        String firstProductName = SAUCELABS_PORTAL.get().HOME_PAGE.sortNameZtoA();
        Assert.assertEquals(firstProductName, "Test.allTheThings() T-Shirt (Red)");
        log.info("Verified the First Product Name {} sorted Z to A.", firstProductName);
    }

    @Test(description = "To verify all the inventory item names and prices.", dataProvider = "products", dataProviderClass = SauceLabsDataProvider.class)
    public void testInventoryItems(String productName, String productPrice) {
        List<WebElement> inventoryItemNames = SAUCELABS_PORTAL.get().HOME_PAGE.getInventoryItemNames();
        boolean isProductNamePresent = inventoryItemNames.stream().map(WebElement::getText).anyMatch(name -> name.equals(productName));
        Assert.assertTrue(isProductNamePresent, "Product Name not present!");
        List<WebElement> inventoryItemPrices = SAUCELABS_PORTAL.get().HOME_PAGE.getInventoryItemPrices();
        boolean isProductPricePresent = inventoryItemPrices.stream().map(WebElement::getText).anyMatch(name -> name.equals(productPrice));
        Assert.assertTrue(isProductPricePresent, "Product Price not present!");
        log.info("Verified the Product Name {} and Product Price {}", productName, productPrice);
    }

    @Test(description = "To verify the burger menu item name - All Items.")
    public void testMenuAllItemsName() {
        String actualMenuAllItemsText = SAUCELABS_PORTAL.get().HOME_PAGE.getAllItemsText();
        Assert.assertEquals(actualMenuAllItemsText, "All Items", "Burger Menu All Items Text Mismatched!");
        log.info("Verified the menu item {}.", actualMenuAllItemsText);
    }

    @Test(description = "To verify the burger menu item name - About.")
    public void testMenuAboutName() {
        String actualMenuAboutText = SAUCELABS_PORTAL.get().HOME_PAGE.getAboutText();
        Assert.assertEquals(actualMenuAboutText, "About", "Burger Menu About Text Mismatched!");
        log.info("Verified the menu item {}.", actualMenuAboutText);
    }

    @Test(description = "To verify the burger menu item name - Logout.")
    public void testMenuLogoutName() {
        String actualMenuLogoutText = SAUCELABS_PORTAL.get().HOME_PAGE.getLogoutText();
        Assert.assertEquals(actualMenuLogoutText, "Logout", "Burger Menu Logout Text Mismatched!");
        log.info("Verified the menu item {}.", actualMenuLogoutText);
    }

    @Test(description = "To verify the burger menu item name - Reset App State.")
    public void testMenuResetAppStateName() {
        String actualMenuResetAppStateText = SAUCELABS_PORTAL.get().HOME_PAGE.getResetAppStateText();
        Assert.assertEquals(actualMenuResetAppStateText, "Reset App State", "Burger Menu Reset App State Text Mismatched!");
        log.info("Verified the menu item {}.", actualMenuResetAppStateText);
    }

    @Test(description = "To verify, all the products added to the shopping cart.", dataProvider = "add-products", dataProviderClass = SauceLabsDataProvider.class)
    public void testProductAddedToShoppingCart(String productName) {
        boolean isProductAdded = SAUCELABS_PORTAL.get().HOME_PAGE.isProductAddedToCart(productName);
        Assert.assertTrue(isProductAdded, "Product Name Mismatched!");
        log.info("Verified adding the product {}", productName);
    }
}