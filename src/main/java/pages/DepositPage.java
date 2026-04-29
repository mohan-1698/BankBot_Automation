package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class DepositPage extends BasePage {

    public DepositPage(WebDriver driver) {
        super(driver);
    }

    private By depositLink = By.linkText("Deposit");
    private By accountNo = By.name("accountno");
    private By amount = By.name("ammount");
    private By desc = By.name("desc");
    private By submit = By.name("AccSubmit");

    private By successMsg = By.xpath("//p[@class='heading3']");

    public void clickDeposit() {
        click(depositLink);
    }

    public void depositAmount(String accId, String amt) {
        type(accountNo, accId);
        type(amount, amt);
        type(desc, "Deposit");
        click(submit);
    }

    public String getSuccessMessage() {
        return getText(successMsg);
    }
}