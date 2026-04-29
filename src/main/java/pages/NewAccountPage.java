package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class NewAccountPage extends BasePage {

    public NewAccountPage(WebDriver driver) {
        super(driver);
    }

    private By newAccountLink = By.linkText("New Account");
    private By customerId = By.name("cusid");
    private By accountType = By.name("selaccount");
    private By deposit = By.name("inideposit");
    private By submit = By.name("button2");

    private By accountNo = By.xpath("//td[text()='Account ID']/following-sibling::td");
    private By successMsg = By.xpath("//p[@class='heading3']");

    public void clickNewAccount() {
        click(newAccountLink);
    }

    public void createAccount(String custId) {
        type(customerId, custId);

        // Select Savings (default option usually index 0)
        driver.findElement(accountType).sendKeys("Savings");

        type(deposit, "5000");

        click(submit);
    }

    public String getAccountId() {
        return getText(accountNo);
    }

    public String getSuccessMessage() {
        return getText(successMsg);
    }
}