package tests;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.*;

import base.BaseTest;
import pages.LoginPage;
import pages.HomePage;
import utils.ExcelUtil;
import utils.ScreenshotUtil;

@Listeners(listeners.TestListener.class)
public class LoginTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] getData() {
        return ExcelUtil.getLoginData();
    }

    // LOGIN TEST (VALID + INVALID + BLANK)
    @Test(dataProvider = "loginData")
    public void verifyLogin(String user, String pass, String expected) {

        LoginPage lp = new LoginPage(driver);
        lp.login(user, pass);

        // VALID LOGIN
        if (expected.equalsIgnoreCase("valid")) {

            Assert.assertTrue(driver.getTitle().contains("Manager"),
                    "Login failed for valid credentials");
        }

        // INVALID + BLANK LOGIN
        else if (expected.equalsIgnoreCase("invalid") || expected.equalsIgnoreCase("blank")) {

            try {
                Alert alert = driver.switchTo().alert();

                // 🔔 Read alert text
                String alertText = alert.getText();
                System.out.println("Alert message: " + alertText);

                // Screenshot before handling alert
                ScreenshotUtil.capture(driver, "Alert_" + expected);

                // Validate alert message
                Assert.assertTrue(alertText.contains("User or Password is not valid"),
                        "Incorrect alert message");

                // Accept alert
                alert.accept();

            } catch (Exception e) {
                Assert.fail("Alert not displayed for invalid/blank login");
            }
        }
    }

    // 🔹 LOGOUT TEST
    @Test
    public void verifyLogout() {

        LoginPage lp = new LoginPage(driver);

        // Login first
        lp.login("mngr659083", "UjAvEsE");

        HomePage hp = new HomePage(driver);
        hp.clickLogout();

        try {
            Alert alert = driver.switchTo().alert();

            String alertText = alert.getText();
            System.out.println("Logout Alert: " + alertText);

            // Screenshot
            ScreenshotUtil.capture(driver, "Logout");

            alert.accept();

        } catch (Exception e) {
            Assert.fail("Logout alert not displayed");
        }

        // Validate redirection
        Assert.assertTrue(lp.isLoginPageDisplayed(),
                "User not redirected to login page after logout");
    }
}