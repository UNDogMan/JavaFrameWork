package page;

import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbstractPage {
    protected final static int PAGE_LOAD_TIMEOUT = 2;
    protected final WebDriver driver;
    protected final Logger logger = LogManager.getRootLogger();
    protected String lastNotification;
    protected final By OPEN_CHECKOUT_ORDER_MODAL = By.xpath("//li[contains(@class,'default-header-menu-item')]/a[normalize-space()='Статус заказа']");

    private final By LAST_NOTIFICATION = By.xpath("//div[contains(@class,'default-notifications-notification')]//p");
    private final By LOGIN_FORM = By.cssSelector("div.default-header-account");
    private final By ACCOUNT_BUTTON = By.cssSelector("a.default-header-account");
    private final By EMAIL_FORM = By.xpath("//input[@type='email']");
    private final By PASSWORD_FORM = By.xpath("//input[@type='password']");
    private final By LOGIN_BUTTON = By.xpath("//button[@type='submit']");

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isLoggedIn() {
        waitUntilPageLoad();
        return driver.findElements(ACCOUNT_BUTTON).size() > 0;
    }

    public AccountPage logIn(User user){
        if (!isLoggedIn()) {
            driver.findElement(LOGIN_FORM).click();
            driver.findElement(EMAIL_FORM).sendKeys(user.getEmail());
            driver.findElement(PASSWORD_FORM).sendKeys(user.getPassword());
            driver.findElement(LOGIN_BUTTON).click();
        } else {
            driver.findElement(ACCOUNT_BUTTON).click();
        }
        saveLastNotification();
        return new AccountPage(driver);
    }

    protected void saveLastNotification() {
        Wait wait = new FluentWait(driver)
                .withTimeout(Duration.ofMillis(1000))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);
        try {
            WebElement notification = (WebElement) wait
                    .until(ExpectedConditions.visibilityOfElementLocated(LAST_NOTIFICATION));
            lastNotification = notification.getText().trim();
            notification.click();
            logger.info("Last saved notification text: " + getLastNotification());
            wait.until(ExpectedConditions.invisibilityOfElementLocated(LAST_NOTIFICATION));
        } catch (Exception e){
            logger.info("Cannot find notification");
        }
    }

    public String getLastNotification() {
        return lastNotification;
    }

    protected void waitUntilPageLoad() {
        new WebDriverWait(driver, PAGE_LOAD_TIMEOUT).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }
}
