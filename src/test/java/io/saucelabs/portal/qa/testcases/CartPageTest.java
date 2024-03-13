package io.saucelabs.portal.qa.testcases;

import io.saucelabs.portal.qa.constants.TestGroups;
import io.saucelabs.portal.qa.testdata.SauceLabsDataProvider;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

@Slf4j
public class CartPageTest extends SauceLabsPortalTestBase {

    @Test(description = "To verify, the cart page title.", groups = {TestGroups.SAUCE_LABS_SMOKE})
    public void testCartPageTitleText() {
        boolean isCartClicked = SAUCELABS_PORTAL.get().HOME_PAGE.isShoppingCartLinkClicked();
        softAssert.assertTrue(isCartClicked, "Shopping Cart Link click failed!");
        String yourCartTitleText = SAUCELABS_PORTAL.get().CART_PAGE.getYourCartTitle();
        softAssert.assertEquals(yourCartTitleText, "Your Cart", "Incorrect Cart Title Text!");
        log.info("Verified the Cart Page Title Text {}", yourCartTitleText);
    }

    @Test(description = "To verify, the cart page title.", groups = {TestGroups.SAUCE_LABS_REGRESSION})
    public void testCartProductCount() {
        boolean isCartClicked = SAUCELABS_PORTAL.get().HOME_PAGE.isShoppingCartLinkClicked();
        softAssert.assertTrue(isCartClicked, "Shopping Cart Link click failed!");
        int cartProductCount = SAUCELABS_PORTAL.get().CART_PAGE.getCartProductCount();
        softAssert.assertTrue((cartProductCount >= 0), "Invalid Card Product Count!");
        log.info("Verified the Cart Product Count {}.", cartProductCount);
    }

    @Test(description = "To verify, the cart quantity label QTY.", groups = {TestGroups.SAUCE_LABS_REGRESSION})
    public void testCartQuantityText() {
        boolean isCartClicked = SAUCELABS_PORTAL.get().HOME_PAGE.isShoppingCartLinkClicked();
        softAssert.assertTrue(isCartClicked, "Shopping Cart Link click failed!");
        String cartQuantityText = SAUCELABS_PORTAL.get().CART_PAGE.getCartQuantityText();
        softAssert.assertEquals(cartQuantityText, "QTY", "Cart Quantity Text Mismatched!");
        log.info("Verified the Cart Quantity Label {}.", cartQuantityText);
    }

    @Test(description = "To verify, the cart quantity label Description.", groups = {TestGroups.SAUCE_LABS_REGRESSION})
    public void testCartDescriptionText() {
        boolean isCartClicked = SAUCELABS_PORTAL.get().HOME_PAGE.isShoppingCartLinkClicked();
        softAssert.assertTrue(isCartClicked, "Shopping Cart Link click failed!");
        String cartDescriptionText = SAUCELABS_PORTAL.get().CART_PAGE.getCartDescriptionText();
        softAssert.assertEquals(cartDescriptionText, "Description", "Cart Description Text Mismatched!");
        log.info("Verified the Cart Description Label {}.", cartDescriptionText);
    }

    @Test(description = "To verify, the cart entries.", dataProvider = "cart-products",
            dataProviderClass = SauceLabsDataProvider.class, groups = {TestGroups.SAUCE_LABS_REGRESSION})
    public void testShoppingCartEntries(String productName, String productPrice, String productDescription) {
        boolean isCartClicked = SAUCELABS_PORTAL.get().HOME_PAGE.isShoppingCartLinkClicked();
        softAssert.assertTrue(isCartClicked, "Shopping Cart Link click failed!");
        List<WebElement> productNames = SAUCELABS_PORTAL.get().CART_PAGE.getInventoryItemNames();
        List<WebElement> productPrices = SAUCELABS_PORTAL.get().CART_PAGE.getInventoryItemPrices();
        List<WebElement> productDescriptions = SAUCELABS_PORTAL.get().CART_PAGE.getInventoryItemDescriptions();
        String actualProductName = productNames.stream().map(WebElement::getText).filter(name -> name.equals(productName)).findFirst().orElse(null);
        softAssert.assertEquals(actualProductName, productName, "Cart Page Product Name Mismatched!");
        String actualProductPrice = productPrices.stream().map(WebElement::getText).filter(name -> name.equals(productPrice)).findFirst().orElse(null);
        softAssert.assertEquals(actualProductPrice, productPrice, "Cart Page Product Price Mismatched!");
        String actualProductDescription = productDescriptions.stream().map(WebElement::getText).filter(name -> name.equals(productDescription)).findFirst().orElse(null);
        softAssert.assertEquals(actualProductDescription, productDescription, "Cart Page Product Description Mismatched!");
        log.info("Verified the Product Name {}, Product Price {}, Product Description {} in the Shopping Cart Page.", productName, productPrice, productDescription);
    }
}