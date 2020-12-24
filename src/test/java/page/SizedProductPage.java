package page;

import model.ProductSize;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SizedProductPage extends ProductPage{
    private String BUTTON_SELECT_SIZE_LOCATOR = "//button[normalize-space()='$']";

    public SizedProductPage(WebDriver driver) {
        super(driver);
    }

    public SizedProductPage setSize(ProductSize size) {
        WebElement setSizeButton = driver.findElement(By.xpath(BUTTON_SELECT_SIZE_LOCATOR.replace("$", size.getSize())));
        setSizeButton.click();
        return this;
    }
}
