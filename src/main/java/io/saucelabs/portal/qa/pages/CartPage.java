package io.saucelabs.portal.qa.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends SauceLabsBasePage {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "title")
    private WebElement yourCartTitle;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartProductCount;

    @FindBy(className = "cart_quantity_label")
    private WebElement cartQuantityLabel;

    @FindBy(className = "cart_desc_label")
    private WebElement cartDescriptionLabel;

    @Getter
    @FindBy(className = "inventory_item_name")
    private List<WebElement> inventoryItemNames;

    @Getter
    @FindBy(className = "inventory_item_price")
    private List<WebElement> inventoryItemPrices;

    @Getter
    @FindBy(className = "inventory_item_desc")
    private List<WebElement> inventoryItemDescriptions;

    public String getYourCartTitle() {
        return getWebElementText(yourCartTitle);
    }

    public int getCartProductCount() {
        return Integer.parseInt(getWebElementText(cartProductCount));
    }

    public String getCartQuantityText() {
        return getWebElementText(cartQuantityLabel);
    }

    public String getCartDescriptionText() {
        return getWebElementText(cartDescriptionLabel);
    }
}