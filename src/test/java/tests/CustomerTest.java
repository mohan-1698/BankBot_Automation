package tests;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.NewCustomerPage;
import pages.EditCustomerPage;
import utils.ConfigReader;

@Listeners(listeners.TestListener.class)  // CONNECT LISTENER
public class CustomerTest extends BaseTest {

    public String generateEmail() {
        return "test" + System.currentTimeMillis() + "@gmail.com";
    }

    @Test
    public void customerManagementFlow() {

        // 🔹 LOGIN
        LoginPage lp = new LoginPage(driver);
        lp.login(ConfigReader.get("user"), ConfigReader.get("pass"));

        NewCustomerPage np = new NewCustomerPage(driver);
        String email = generateEmail();

        // 🔹 CREATE CUSTOMER
        np.clickNewCustomer();
        np.fillCustomerForm(email);
        np.submit();

        // HANDLE ALERT (FORM ERROR)
        try {
            Alert alert = driver.switchTo().alert();
            System.out.println("Alert: " + alert.getText());
            alert.accept();
            Assert.fail("Customer creation failed");
        } catch (Exception e) {
            // no alert
        }

        // VERIFY CREATION
        String customerId = np.getCustomerId();
        System.out.println("Customer ID: " + customerId);

        Assert.assertTrue(
                np.getSuccessMessage().contains("Customer Registered Successfully"),
                "Customer creation failed"
        );

        // 🔹 DUPLICATE EMAIL
        np.clickNewCustomer();
        np.fillCustomerForm(email);
        np.submit();

        boolean isDuplicateHandled = false;

        try {
            Alert alert = driver.switchTo().alert();
            System.out.println("Duplicate Alert: " + alert.getText());
            alert.accept();
            isDuplicateHandled = true;

        } catch (Exception e) {
            System.out.println("No alert, checking page...");
        }

        if (!isDuplicateHandled) {
            if (driver.getPageSource().contains("Email Already Exist")) {
                isDuplicateHandled = true;
            }
        }

        if (!isDuplicateHandled) {
            Assert.fail("Duplicate email validation failed");
        }

        // 🔹 EDIT CUSTOMER
        EditCustomerPage ep = new EditCustomerPage(driver);

        ep.clickEditCustomer();
        ep.enterCustomerId(customerId);
        ep.submitCustomerId();

        ep.updateAddress("Updated Address Vijayawada");
        ep.submitUpdate();

        Assert.assertTrue(
                ep.getUpdateMessage().contains("Customer details updated Successfully"),
                "Customer update failed"
        );
    }
}
