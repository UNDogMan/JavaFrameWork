package page;

import model.ProductInfo;
import model.ProductSize;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class CartModal<T extends AbstractPage> extends AbstractPage {
    private static final By CART_PRODUCT_LIST = By.cssSelector("div.default-modals-cart-product-information");
    private static final By CART_PRODUCT_NAME = By.cssSelector("a.default-modals-cart-product-name");
    private static final By CART_PRODUCT_COUNT = By.cssSelector("div.default-modals-cart-product-count-value");
    private static final By CART_PRODUCT_PRICE = By.cssSelector("div.default-modals-cart-product-price");
    private static final By CART_PRODUCT_SIZES = By.cssSelector("div.default-modals-cart-product-sizes");
    private static final By CART_PRODUCT_ACTIVE_SIZE = By.xpath("//button[@active='true' and contains(@class, 'default-modals-cart-product-sizes-size')]");
    private static final String BUTTON_SELECT_CART_PRODUCT_SIZE_LOCATOR = "//button[contains(@class, 'default-modals-cart-product-sizes-size') and normalize-space()='$']";
    private static final By BUTTON_REMOVE_PRODUCT_FROM_CART = By.xpath("//div[contains(@class, 'default-modals-cart-product-remove')]");
    private static final By BUTTON_INCREASE_PRODUCT_FROM_CART = By.xpath("//button[contains(@class, 'default-modals-cart-product-count-plus')]");
    private static final By BUTTON_DECREASE_PRODUCT_FROM_CART = By.xpath("//button[contains(@class, 'default-modals-cart-product-count-minus')]");

    private T previousPage;

    @FindBy(xpath = "//button[@class='default-modals-cart-continue-shopping']")
    private WebElement closeCartButton;

    @FindBy(css = "input.default-modals-cart-promo-code-input")
    private WebElement promoCodeInput;

    @FindBy(css = "button.default-modals-cart-promo-code-apply")
    private WebElement promoCodeApplyButton;

    @FindBy(css = "button.default-modals-cart-checkout span")
    private WebElement checkoutCartButton;

    public CartModal(WebDriver driver, T previousPage) {
        super(driver);
        this.previousPage = previousPage;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, PAGE_LOAD_TIMEOUT), this);
    }

    public T continueShopping() {
        closeCartButton.click();
        return this.previousPage;
    }

    public boolean checkoutCart() {
        checkoutCartButton.click();
        saveLastNotification();
        return driver.getCurrentUrl().contains("details");
    }

    public CartModal<T> interPromoCode(String promo) {
        promoCodeInput.sendKeys(promo);
        return this;
    }

    public CartModal<T> activatePromoCode() {
        promoCodeApplyButton.click();
        saveLastNotification();
        return this;
    }

    private List<WebElement> getCartProductElements() {
        Wait wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(3)).pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
        try {
            return (List<WebElement>) wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(CART_PRODUCT_LIST));
        } catch (Exception e) {
            logger.info("Cannot find product elements in cart");
            return new ArrayList<>();
        }
    }

    private ProductInfo extractProductInfoFromElement(WebElement productElement) {
        String name = productElement.findElement(CART_PRODUCT_NAME).getText().trim();
        String count = productElement.findElement(CART_PRODUCT_COUNT).getText().trim();
        String price = productElement.findElement(CART_PRODUCT_PRICE).getText().trim();
        String size = null;
        if (productElement.findElements(CART_PRODUCT_SIZES).size() > 0) {
            size = productElement.findElement(CART_PRODUCT_ACTIVE_SIZE).getText().trim();
        }
        return new ProductInfo(name, count, price, size);
    }

    public List<ProductInfo> getInCartProductInfo() {
        List<ProductInfo> productInfoList = new ArrayList<ProductInfo>();
        for (WebElement productElement : getCartProductElements()) {
            productInfoList.add(extractProductInfoFromElement(productElement));
        }
        return productInfoList;
    }

    public CartModal<T> changeOrderedProductSize(ProductInfo productInfo, ProductSize productSize) {
        Optional<WebElement> productElement = getCartProductElements().stream()
                .filter(s -> productInfo.equals(extractProductInfoFromElement(s))).findFirst();
        if (productElement.isPresent()) {
            WebElement setSizeButton = productElement.get()
                    .findElement(By.xpath(BUTTON_SELECT_CART_PRODUCT_SIZE_LOCATOR
                            .replace("$", productSize.getSize())));
            productInfo.setSize(productSize.getSize());
            setSizeButton.click();
            saveLastNotification();
        }
        return this;
    }

    private CartModal<T> clickElementInOrderedProductLocatedBy(ProductInfo productInfo, By by) {
        Optional<WebElement> productElement = getCartProductElements().stream()
                .filter(s -> productInfo.equals(extractProductInfoFromElement(s))).findFirst();
        if (productElement.isPresent()) {
            WebElement setSizeButton = productElement.get()
                    .findElement(by);
            setSizeButton.click();
            saveLastNotification();
        }
        return this;
    }

    public CartModal<T> removeOrderedProduct(ProductInfo productInfo) {
        return clickElementInOrderedProductLocatedBy(productInfo, BUTTON_REMOVE_PRODUCT_FROM_CART);
    }

    public CartModal<T> increaseOrderedProduct(ProductInfo productInfo) {
        return clickElementInOrderedProductLocatedBy(productInfo, BUTTON_INCREASE_PRODUCT_FROM_CART);
    }

    public CartModal<T> decreaseOrderedProduct(ProductInfo productInfo) {
        return clickElementInOrderedProductLocatedBy(productInfo, BUTTON_DECREASE_PRODUCT_FROM_CART);
    }
}