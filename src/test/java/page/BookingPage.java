package page;

import model.Location;
import model.UserInfo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import service.ICanOpenCart;

public class BookingPage extends AbstractPage implements ICanOpenCart<BookingPage> {
    @FindBy(css = "div.default-header-cart")
    private WebElement openCartButton;

    @FindBy(xpath = "//p[normalize-space()='Имя']/../input")
    private WebElement nameInputField;

    @FindBy(xpath = "//p[normalize-space()='Фамилия']/../input")
    private WebElement surnameInputField;

    @FindBy(xpath = "d//p[normalize-space()='Отчество']/../input")
    private WebElement patronymicInputField;

    @FindBy(xpath = "//p[normalize-space()='Телефон']/../input")
    private WebElement phoneNumberInputField;

    @FindBy(xpath = "//p[normalize-space()='Эл.Почта']/../input")
    private WebElement emailInputField;

    @FindBy(xpath = "//p[normalize-space()='Страна']/../input")
    private WebElement countryInputField;

    @FindBy(xpath = "//p[normalize-space()='Город']/../input")
    private WebElement cityInputField;

    @FindBy(xpath = "//p[normalize-space()='Край/Область/Регион']/../input")
    private WebElement regionInputField;

    @FindBy(xpath = "//p[normalize-space()='Улица, Дом, Квартира']/../input")
    private WebElement addressInputField;

    @FindBy(xpath = "//p[normalize-space()='Почтовый индекс']/../input")
    private WebElement postcodeInputField;

    @FindBy(css = "button.booking-details-next-step")
    private WebElement submitOrderDetails;

    @FindBy(css = "span.booking-products-products-cost-value")
    private WebElement totalProductsCost;

    @FindBy(css = "span.booking-products-delivery-cost-value")
    private WebElement totalDeliveryCost;

    public BookingPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, PAGE_LOAD_TIMEOUT), this);
    }

    @Override
    public CartModal<BookingPage> openCart() {
        openCartButton.click();
        return new CartModal<BookingPage>(driver, this);
    }

    public BookingPage enterLocation(Location location) {
        postcodeInputField.sendKeys(location.getPostcode());
        countryInputField.sendKeys(location.getCountry());
        cityInputField.sendKeys(location.getCity());
        regionInputField.sendKeys(location.getRegion());
        addressInputField.sendKeys(location.getAddress());
        return this;
    }

    public BookingPage enterUserInfo(UserInfo userInfo) {
        nameInputField.sendKeys(userInfo.getName());
        surnameInputField.sendKeys(userInfo.getSurname());
        patronymicInputField.sendKeys(userInfo.getPatronymic());
        phoneNumberInputField.sendKeys(userInfo.getPhoneNumber());
        emailInputField.sendKeys(userInfo.getEmail());
        return this;
    }

    public boolean ConfirmOrderDetails(){
        submitOrderDetails.click();
        saveLastNotification();
        return !driver.getCurrentUrl().contains("details");
    }

    public String getTotalOrderPrice() {
        return totalProductsCost.getText().trim();
    }

    public String getDeliveryOrederPrice() {
        return totalDeliveryCost.getText().trim();
    }
}
