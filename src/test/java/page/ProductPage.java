package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class ProductPage extends AbstractPage{
    protected String PRODUCT_PAGE = "https://jolybell.com/product/1";

    @FindBy(xpath = "//button[@class='product-information-add-to-cart']")
    protected WebElement addToCart;

    @FindBy(xpath = "//div[div[normalize-space()='Термочашка']]")
    protected WebElement recommendedProduct;

    @FindBy(css = "div.default-header-cart")
    protected WebElement openCartButton;

    public ProductPage(WebDriver driver) {
        super(driver);
        driver.get(PRODUCT_PAGE);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, PAGE_LOAD_TIMEOUT), this);
    }

    public CartModal<ProductPage> clickAddToCartButton() {
        addToCart.click();
        saveLastNotification();
        return new CartModal<ProductPage>(driver, this);
    }

    public CartModal<ProductPage> openCart() {
        openCartButton.click();
        return new CartModal<ProductPage>(driver, this);
    }

    public RecommendedProductModal selectFirstRecommendedProduct() {
        recommendedProduct.click();
        return new RecommendedProductModal(driver, this);
    }
}
