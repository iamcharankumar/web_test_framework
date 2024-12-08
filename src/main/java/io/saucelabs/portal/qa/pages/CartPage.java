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
        String cartTitleText = yourCartTitle.getText();
        validateNonEmptyText(cartTitleText, "The Cart title text is empty!");
        return cartTitleText;
    }

    public String getCartQuantityText() {
        String cartQuantityText = cartQuantityLabel.getText();
        validateNonEmptyText(cartQuantityText, "Cart Quantity Text is empty!");
        return cartQuantityText;
    }

    public String getCartDescriptionText() {
        String cartDescriptionText = cartDescriptionLabel.getText();
        validateNonEmptyText(cartDescriptionText, "The Cart Description text is empty!");
        return cartDescriptionText;
    }

    public List<WebElement> getInventoryItemNames() {
        validateAction(inventoryItemNames.isEmpty(), "Inventory Item Names List is empty!");
        return inventoryItemNames;
    }

    public List<WebElement> getInventoryItemPrices() {
        validateAction(inventoryItemPrices.isEmpty(), "Inventory Item Prices List is empty!");
        return inventoryItemPrices;
    }

    public List<WebElement> getInventoryItemDescriptions() {
        validateAction(inventoryItemDescriptions.isEmpty(), "Inventory Item Descriptions List is empty!");
        return inventoryItemDescriptions;
    }
}