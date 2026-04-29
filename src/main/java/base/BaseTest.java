package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.ConfigReader;
import utils.DriverFactory;

public class BaseTest {

    public WebDriver driver;

    @BeforeMethod
    public void setUp() {
        new ConfigReader();
        driver = DriverFactory.initDriver();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}