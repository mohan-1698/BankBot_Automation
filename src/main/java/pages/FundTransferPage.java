package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class FundTransferPage extends BasePage {

    public FundTransferPage(WebDriver driver) {
        super(driver);
    }

    private By fundTransferLink = By.linkText("Fund Transfer");
    private By payerAcc = By.name("payersaccount");
    private By payeeAcc = By.name("payeeaccount");
    private By amount = By.name("ammount");
    private By desc = By.name("desc");
    private By submit = By.name("AccSubmit");

    private By successMsg = By.xpath("//p[@class='heading3']");

    public void clickFundTransfer() {
        click(fundTransferLink);
    }

    public void transfer(String fromAcc, String toAcc, String amt) {
        type(payerAcc, fromAcc);
        type(payeeAcc, toAcc);
        type(amount, amt);
        type(desc, "Transfer");
        click(submit);
    }

    public String getSuccessMessage() {
        return getText(successMsg);
    }
}