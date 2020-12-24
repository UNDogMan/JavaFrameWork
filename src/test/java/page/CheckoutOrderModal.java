package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class CheckoutOrderModal<T extends AbstractPage> extends AbstractPage{
    private T previousPage;

    @FindBy(css = "svg.default-modals-modal-close")
    private WebElement closeCheckoutOrderModalButton;

    @FindBy(css = "button.default-modals-order-status-form-submit")
    private WebElement submitCheckoutOrderModalButton;

    @FindBy(css = "input.default-modals-order-status-form-field-input")
    private WebElement inputOrderId;

    public CheckoutOrderModal(WebDriver driver, T previousPage) {
        super(driver);
        this.previousPage = previousPage;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, PAGE_LOAD_TIMEOUT), this);
    }

    public CheckoutOrderModal<T> sendOrderId(String keys) {
        inputOrderId.sendKeys(keys);
        return this;
    }

    public CheckoutOrderModal<T> submitCheckout() {
        submitCheckoutOrderModalButton.click();
        saveLastNotification();
        return this;
    }

    public T closeCheckoutOrderModal() {
        closeCheckoutOrderModalButton.click();
        return previousPage;
    }
}
