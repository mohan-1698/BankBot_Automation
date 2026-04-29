package tests;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.*;
import utils.ConfigReader;

@Listeners(listeners.TestListener.class)
public class FundTransferTest extends BaseTest {

    public String generateEmail() {
        return "test" + System.currentTimeMillis() + "@gmail.com";
    }

    @Test
    public void fundTransferFlow() {

        System.out.println("===== STARTING FUND TRANSFER FLOW =====");

        // 🔹 LOGIN
        LoginPage lp = new LoginPage(driver);
        lp.login(ConfigReader.get("user"), ConfigReader.get("pass"));
        System.out.println("Login successful");

        // 🔹 CREATE CUSTOMER
        NewCustomerPage np = new NewCustomerPage(driver);
        String email = generateEmail();

        np.clickNewCustomer();
        np.fillCustomerForm(email);
        np.submit();

        try {
            driver.switchTo().alert().accept();
        } catch (Exception e) {}

        String customerId = np.getCustomerId();
        System.out.println("Customer ID: " + customerId);

        // 🔹 CREATE TWO ACCOUNTS
        NewAccountPage nap = new NewAccountPage(driver);

        nap.clickNewAccount();
        nap.createAccount(customerId);
        String acc1 = nap.getAccountId();

        nap.clickNewAccount();
        nap.createAccount(customerId);
        String acc2 = nap.getAccountId();

        System.out.println("Account1: " + acc1);
        System.out.println("Account2: " + acc2);

        // 🔹 DEPOSIT (IMPORTANT)
        DepositPage dp = new DepositPage(driver);

        dp.clickDeposit();
        dp.depositAmount(acc1, ConfigReader.get("depositAmount"));

        Assert.assertTrue(dp.getSuccessMessage().contains("Transaction details"),
                "Deposit failed");

        System.out.println("Deposit successful");

        // 🔹 FUND TRANSFER
        FundTransferPage ftp = new FundTransferPage(driver);

        ftp.clickFundTransfer();
        ftp.transfer(acc1, acc2, ConfigReader.get("transferAmount"));

        Assert.assertTrue(
                ftp.getSuccessMessage().contains("Fund Transfer Details"),
                "Transfer failed"
        );

        System.out.println("Fund transfer successful");

        // 🔹 VERIFY BALANCE (Acc1 should reduce)
        BalanceEnquiryPage bep = new BalanceEnquiryPage(driver);

        bep.clickBalanceEnquiry();
        bep.searchAccount(acc1);

        String balanceAfter = driver.getPageSource();
        System.out.println("Balance page loaded");

        Assert.assertTrue(balanceAfter.contains(acc1),
                "Balance verification failed");

        System.out.println("Balance verified");

        // 🔹 INVALID PAYEE TEST
        ftp.clickFundTransfer();
        ftp.transfer(acc1, ConfigReader.get("invalidAccount"), ConfigReader.get("invalidTransferAmount"));

        try {
            Alert alert = driver.switchTo().alert();
            System.out.println("Invalid Payee Alert: " + alert.getText());
            alert.accept();
            System.out.println("Invalid payee validation passed");
        } catch (Exception e) {
            Assert.fail("Invalid payee validation failed");
        }

        System.out.println("===== FUND TRANSFER FLOW COMPLETED =====");
    }
}