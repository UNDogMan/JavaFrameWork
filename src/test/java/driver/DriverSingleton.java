package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import service.ResourceDataReader;

public class DriverSingleton {
    private static WebDriver driver;

    private DriverSingleton(){

    }

    public static WebDriver getDriver(){
        if (null == driver) {
            switch (System.getProperty("browser")){
                case "firefox": {
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    if (!ResourceDataReader.getEnvironmentData("options").isEmpty()) {
                        firefoxOptions.addArguments(ResourceDataReader.getEnvironmentData("options"));
                    }
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver(firefoxOptions);
                }
                default: {
                    ChromeOptions chromeOptions = new ChromeOptions();
                    if (!ResourceDataReader.getEnvironmentData("options").isEmpty()) {
                        chromeOptions.addArguments(ResourceDataReader.getEnvironmentData("options"));
                    }
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(chromeOptions);
                }
            }
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void deleteAllCookies(){
        if (null != driver){
            driver.manage().deleteAllCookies();
        }
    }

    public static void closeDriver(){
        driver.close();
        driver = null;
    }
}
