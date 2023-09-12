package io.saucelabs.portal.qa.pages;

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

    @FindBy(className = "inventory_item_name")
    private List<WebElement> inventoryItemNames;

    @FindBy(className = "inventory_item_price")
    private List<WebElement> inventoryItemPrices;

    @FindBy(className = "inventory_item_desc")
    private List<WebElement> inventoryItemDescriptions;

    public String getYourCartTitle() {
        return yourCartTitle.getText();
    }

    public int getCartProductCount() {
        return Integer.parseInt(cartProductCount.getText());
    }

    public String getCartQuantityText() {
        return cartQuantityLabel.getText();
    }

    public String getCartDescriptionText() {
        return cartDescriptionLabel.getText();
    }

    public List<WebElement> getInventoryItemNames() {
        return inventoryItemNames;
    }

    public List<WebElement> getInventoryItemPrices() {
        return inventoryItemPrices;
    }

    public List<WebElement> getInventoryItemDescriptions() {
        return inventoryItemDescriptions;
    }
}