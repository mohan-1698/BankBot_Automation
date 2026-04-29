package tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.NewCustomerPage;
import utils.ConfigReader;

@Listeners(listeners.TestListener.class)
public class FormValidationTest extends BaseTest {

    @Test
    public void formValidationFlow() {

        System.out.println("===== STARTING FORM VALIDATION TEST =====");

        // LOGIN
        LoginPage lp = new LoginPage(driver);
        lp.login(ConfigReader.get("user"), ConfigReader.get("pass"));
        System.out.println("Login successful");

        NewCustomerPage np = new NewCustomerPage(driver);
        np.clickNewCustomer();

        // EMPTY FIELD VALIDATION
        System.out.println("Testing empty form...");
        np.submitEmptyForm();

        Assert.assertTrue(np.getNameError().contains("must not be blank"));
        Assert.assertTrue(np.getCityError().contains("must not be blank"));
        Assert.assertTrue(np.getStateError().contains("must not be blank"));

        System.out.println("Empty validation passed");

        //NON-NUMERIC PIN
        System.out.println("🔹 Testing invalid PIN...");
        np.enterPin(ConfigReader.get("invalidPin"));

        String pinError = np.getPinError();
        System.out.println("PIN Error: " + pinError);

        Assert.assertTrue(pinError.contains("must be numeric"));

        System.out.println("Non-numeric validation passed");

        // FUTURE DOB
        System.out.println("Testing future DOB...");
        np.enterDOB(ConfigReader.get("futureDob"));

        String pageSource = driver.getPageSource();

        Assert.assertTrue(
                pageSource.toLowerCase().contains(ConfigReader.get("validationText")),
                "Future DOB validation failed"
        );

        System.out.println("Future DOB validation passed");

        System.out.println("===== FORM VALIDATION COMPLETED =====");
    }
}