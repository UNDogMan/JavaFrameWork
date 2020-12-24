package test;

import driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import util.TestListener;

@Listeners(TestListener.class)
public class CommonConditions {
    protected WebDriver driver;

    @BeforeTest
    public void setUp() {
        driver = DriverSingleton.getDriver();
    }

    @BeforeMethod
    public void deleteCookies() {
        DriverSingleton.deleteAllCookies();
    }

    @AfterTest
    public void tearDown() {
        DriverSingleton.closeDriver();
    }
}
