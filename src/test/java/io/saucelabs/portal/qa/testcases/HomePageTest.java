package io.saucelabs.portal.qa.testcases;

import io.saucelabs.portal.qa.constants.TestGroups;
import io.saucelabs.portal.qa.testdata.SauceLabsDataProvider;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

@Slf4j
public class HomePageTest extends SauceLabsPortalTestBase {

    @Test(description = "To verify, the home page header text.", groups = {TestGroups.SAUCE_LABS_SMOKE, TestGroups.SAUCE_LABS_REGRESSION})
    public void testHomePageHeaderText() {
        String homePageHeaderText = SAUCELABS_PORTAL.get().HOME_PAGE.getHomePageHeaderText();
        softAssert.assertEquals(homePageHeaderText, "Swag Labs", "Incorrect Home Page Header Text!");
        log.info("Verified the Home Page Header Text {}", homePageHeaderText);
    }

    @Test(description = "To verify, the home page products header text.", groups = {TestGroups.SAUCE_LABS_SMOKE, TestGroups.SAUCE_LABS_REGRESSION})
    public void testHomePageProductsText() {
        String productText = SAUCELABS_PORTAL.get().HOME_PAGE.getProductHeaderText();
        softAssert.assertEquals(productText, "Products", "Incorrect Products Header Text!");
        log.info("Verified the Home Page Products Text {}", productText);
    }

    @Test(description = "To verify, the shopping cart link is enabled.", groups = {TestGroups.SAUCE_LABS_SMOKE, TestGroups.SAUCE_LABS_REGRESSION})
    public void testShoppingCartLink() {
        boolean isShoppingCartLinkEnabled = SAUCELABS_PORTAL.get().HOME_PAGE.isShoppingCartLinkEnabled();
        softAssert.assertTrue(isShoppingCartLinkEnabled);
        log.info("Verified the Shopping Card Link Enabled Status {}", isShoppingCartLinkEnabled);
    }

    @Test(description = "To verify, the first product name by sorting A-Z.", groups = {TestGroups.SAUCE_LABS_SMOKE, TestGroups.SAUCE_LABS_REGRESSION})
    public void testProductSortAtoZ() {
        String firstProductName = SAUCELABS_PORTAL.get().HOME_PAGE.sortNameAtoZ();
        softAssert.assertEquals(firstProductName, "Sauce Labs Backpack", "First Product Name Mismatched!");
        log.info("Verified the First Product Name {} sorted A to Z.", firstProductName);
    }

    @Test(description = "To verify, the first product name by sorting Z-A.", groups = {TestGroups.SAUCE_LABS_SMOKE, TestGroups.SAUCE_LABS_REGRESSION})
    public void testProductSortZtoA() {
        String firstProductName = SAUCELABS_PORTAL.get().HOME_PAGE.sortNameZtoA();
        softAssert.assertEquals(firstProductName, "Test.allTheThings() T-Shirt (Red)");
        log.info("Verified the First Product Name {} sorted Z to A.", firstProductName);
    }

    @Test(description = "To verify all the inventory item names and prices.", dataProvider = "products",
            dataProviderClass = SauceLabsDataProvider.class, groups = {TestGroups.SAUCE_LABS_SMOKE, TestGroups.SAUCE_LABS_REGRESSION})
    public void testInventoryItems(String productName, String productPrice) {
        List<WebElement> inventoryItemNames = SAUCELABS_PORTAL.get().HOME_PAGE.getInventoryItemNames();
        boolean isProductNamePresent = inventoryItemNames.stream().map(WebElement::getText).anyMatch(name -> name.equals(productName));
        softAssert.assertTrue(isProductNamePresent, "Product Name not present!");
        List<WebElement> inventoryItemPrices = SAUCELABS_PORTAL.get().HOME_PAGE.getInventoryItemPrices();
        boolean isProductPricePresent = inventoryItemPrices.stream().map(WebElement::getText).anyMatch(name -> name.equals(productPrice));
        softAssert.assertTrue(isProductPricePresent, "Product Price not present!");
        log.info("Verified the Product Name {} and Product Price {}", productName, productPrice);
    }

    @Test(description = "To verify the burger menu item name - All Items.", groups = {TestGroups.SAUCE_LABS_SMOKE, TestGroups.SAUCE_LABS_REGRESSION})
    public void testMenuAllItemsName() {
        String actualMenuAllItemsText = SAUCELABS_PORTAL.get().HOME_PAGE.getAllItemsText();
        softAssert.assertEquals(actualMenuAllItemsText, "All Items", "Burger Menu All Items Text Mismatched!");
        log.info("Verified the menu item {}.", actualMenuAllItemsText);
    }

    @Test(description = "To verify the burger menu item name - About.", groups = {TestGroups.SAUCE_LABS_SMOKE, TestGroups.SAUCE_LABS_REGRESSION})
    public void testMenuAboutName() {
        String actualMenuAboutText = SAUCELABS_PORTAL.get().HOME_PAGE.getAboutText();
        softAssert.assertEquals(actualMenuAboutText, "About", "Burger Menu About Text Mismatched!");
        log.info("Verified the menu item {}.", actualMenuAboutText);
    }

    @Test(description = "To verify the burger menu item name - Logout.", groups = {TestGroups.SAUCE_LABS_SMOKE, TestGroups.SAUCE_LABS_REGRESSION})
    public void testMenuLogoutName() {
        String actualMenuLogoutText = SAUCELABS_PORTAL.get().HOME_PAGE.getLogoutText();
        softAssert.assertEquals(actualMenuLogoutText, "Logout", "Burger Menu Logout Text Mismatched!");
        log.info("Verified the menu item {}.", actualMenuLogoutText);
    }

    @Test(description = "To verify the burger menu item name - Reset App State.", groups = {TestGroups.SAUCE_LABS_SMOKE, TestGroups.SAUCE_LABS_REGRESSION})
    public void testMenuResetAppStateName() {
        String actualMenuResetAppStateText = SAUCELABS_PORTAL.get().HOME_PAGE.getResetAppStateText();
        softAssert.assertEquals(actualMenuResetAppStateText, "Reset App State", "Burger Menu Reset App State Text Mismatched!");
        log.info("Verified the menu item {}.", actualMenuResetAppStateText);
    }

    @Test(description = "To verify, all the products added to the shopping cart.", dataProvider = "add-products",
            dataProviderClass = SauceLabsDataProvider.class, groups = {TestGroups.SAUCE_LABS_SMOKE, TestGroups.SAUCE_LABS_REGRESSION})
    public void testProductAddedToShoppingCart(String productName) {
        boolean isProductAdded = SAUCELABS_PORTAL.get().HOME_PAGE.isProductAddedToCart(productName);
        softAssert.assertTrue(isProductAdded, "Product Name Mismatched!");
        log.info("Verified adding the product {}", productName);
    }
}