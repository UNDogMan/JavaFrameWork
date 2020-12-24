package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import service.ICanOpenCart;

public class AccountPage extends AbstractPage implements ICanOpenCart<AccountPage> {
    private final By LOGOUT_BUTTON = By.cssSelector("button.account-logout");

    @FindBy(css = "div.default-header-cart")
    private WebElement openCartButton;

    public AccountPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, PAGE_LOAD_TIMEOUT), this);
    }

    public CartModal<AccountPage> openCart() {
        openCartButton.click();
        return new CartModal<AccountPage>(driver, this);
    }

    public MainPage logOut() {
        driver.findElement(LOGOUT_BUTTON).click();
        return new MainPage(driver);
    }

    public CheckoutOrderModal<AccountPage> verifyOrderModal() {
        driver.findElement(OPEN_CHECKOUT_ORDER_MODAL).click();
        return new CheckoutOrderModal<>(driver, this);
    }
}
