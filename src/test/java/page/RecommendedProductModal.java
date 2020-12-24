package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class RecommendedProductModal extends AbstractPage{
    private AbstractPage previousPage;

    @FindBy(css = "svg.default-modals-modal-close")
    private WebElement closeRecommendationProduct;

    @FindBy(css = "div.default-modals-recommendation-information button.product-information-add-to-cart")
    private WebElement addRecommendedToCart;

    public RecommendedProductModal(WebDriver driver, AbstractPage previousPage) {
        super(driver);
        this.previousPage = previousPage;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, PAGE_LOAD_TIMEOUT), this);
    }

    public AbstractPage continueShopping() {
        closeRecommendationProduct.click();
        return this.previousPage;
    }

    public CartModal<RecommendedProductModal> addProductToCart() {
        addRecommendedToCart.click();
        saveLastNotification();
        return new CartModal<RecommendedProductModal>(driver, this);
    }
}
