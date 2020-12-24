package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import service.ICanOpenCart;

public class MainPage extends AbstractPage implements ICanOpenCart<MainPage> {
    private static final String MAIN_PAGE_URL = "https://jolybell.com/";

    @FindBy(css = "div.default-header-cart")
    private WebElement openCartButton;

    public MainPage(WebDriver driver) {
        super(driver);
        if (!driver.getCurrentUrl().equals(MAIN_PAGE_URL)){
            driver.get(MAIN_PAGE_URL);
        }
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, PAGE_LOAD_TIMEOUT), this);
    }

    public CartModal<MainPage> openCart() {
        openCartButton.click();
        return new CartModal<MainPage>(driver, this);
    }
}
