package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class EditAccountPage extends BasePage {

    public EditAccountPage(WebDriver driver) {
        super(driver);
    }

    private By editAccountLink = By.linkText("Edit Account");
    private By accountIdField = By.name("accountno");
    private By submitBtn = By.name("AccSubmit");

    private By successMsg = By.xpath("//p[@class='heading3']");

    public void clickEditAccount() {
        click(editAccountLink);
    }

    public void enterAccountId(String accId) {
        type(accountIdField, accId);
    }

    public void submitAccountId() {
        click(submitBtn);
    }

    // ✅ SAFE METHOD (NO CRASH)
    public void updateAccountType() {
        try {
            driver.findElement(By.name("selaccount")).sendKeys("Current");
            System.out.println("Account type changed");
        } catch (Exception e) {
            System.out.println("⚠️ Account type not editable on this page");
            throw e; // send back to test
        }
    }

    public void submitUpdate() {
        try {
            click(By.name("AccSubmit"));
        } catch (Exception e) {
            System.out.println("⚠️ Submit update not available");
        }
    }

    public String getUpdateMessage() {
        return getText(successMsg);
    }
}