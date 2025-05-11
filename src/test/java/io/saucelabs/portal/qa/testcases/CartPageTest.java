package io.saucelabs.portal.qa.testcases;

import io.saucelabs.portal.qa.constants.WebTestGroups;
import io.saucelabs.portal.qa.testdata.SauceLabsDataProvider;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@Slf4j
public class CartPageTest extends SauceLabsPortalTestBase {

    @Test(description = "To verify, the cart page title.", groups = {WebTestGroups.SAUCE_LABS_SMOKE})
    public void testCartPageTitleText() {
        boolean isCartClicked = SAUCE_LABS_PORTAL.get().HOME_PAGE.isShoppingCartLinkClicked();
        Assert.assertTrue(isCartClicked, "Shopping Cart Link click failed!");
        String yourCartTitleText = SAUCE_LABS_PORTAL.get().CART_PAGE.getYourCartTitle();
        Assert.assertEquals(yourCartTitleText, "Your Cart", "Incorrect Cart Title Text!");
        log.info("Verified the Cart Page Title Text {}", yourCartTitleText);
    }

    @Test(description = "To verify, the cart quantity label QTY.", groups = {WebTestGroups.SAUCE_LABS_REGRESSION})
    public void testCartQuantityText() {
        boolean isCartClicked = SAUCE_LABS_PORTAL.get().HOME_PAGE.isShoppingCartLinkClicked();
        Assert.assertTrue(isCartClicked, "Shopping Cart Link click failed!");
        String cartQuantityText = SAUCE_LABS_PORTAL.get().CART_PAGE.getCartQuantityText();
        Assert.assertEquals(cartQuantityText, "QTY", "Cart Quantity Text Mismatched!");
        log.info("Verified the Cart Quantity Label {}.", cartQuantityText);
    }

    @Test(description = "To verify, the cart quantity label Description.", groups = {WebTestGroups.SAUCE_LABS_REGRESSION})
    public void testCartDescriptionText() {
        boolean isCartClicked = SAUCE_LABS_PORTAL.get().HOME_PAGE.isShoppingCartLinkClicked();
        Assert.assertTrue(isCartClicked, "Shopping Cart Link click failed!");
        String cartDescriptionText = SAUCE_LABS_PORTAL.get().CART_PAGE.getCartDescriptionText();
        Assert.assertEquals(cartDescriptionText, "Description", "Cart Description Text Mismatched!");
        log.info("Verified the Cart Description Label {}.", cartDescriptionText);
    }

    @Test(description = "To verify, the cart entries.", dataProvider = "cart-products",
            dataProviderClass = SauceLabsDataProvider.class, groups = {WebTestGroups.SAUCE_LABS_REGRESSION})
    public void testShoppingCartEntries(String productName, String productPrice, String productDescription) {
        List<WebElement> productNames = SAUCE_LABS_PORTAL.get().CART_PAGE.getInventoryItemNames();
        List<WebElement> productPrices = SAUCE_LABS_PORTAL.get().CART_PAGE.getInventoryItemPrices();
        List<WebElement> productDescriptions = SAUCE_LABS_PORTAL.get().CART_PAGE.getInventoryItemDescriptions();
        String actualProductName = productNames.stream().map(WebElement::getText).filter(name -> name.equals(productName)).findFirst().orElse(null);
        Assert.assertEquals(actualProductName, productName, "Cart Page Product Name Mismatched!");
        String actualProductPrice = productPrices.stream().map(WebElement::getText).filter(name -> name.equals(productPrice)).findFirst().orElse(null);
        Assert.assertEquals(actualProductPrice, productPrice, "Cart Page Product Price Mismatched!");
        String actualProductDescription = productDescriptions.stream().map(WebElement::getText).filter(name -> name.equals(productDescription)).findFirst().orElse(null);
        Assert.assertEquals(actualProductDescription, productDescription, "Cart Page Product Description Mismatched!");
        log.info("Verified the Product Name {}, Product Price {}, Product Description {} in the Shopping Cart Page.", productName, productPrice, productDescription);
    }
}