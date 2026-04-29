package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class BalanceEnquiryPage extends BasePage {

    public BalanceEnquiryPage(WebDriver driver) {
        super(driver);
    }

    private By balanceEnquiryLink = By.linkText("Balance Enquiry");
    private By accountNoField = By.name("accountno");
    private By submitBtn = By.name("AccSubmit");

    private By accountNoText = By.xpath("//td[text()='Account No']/following-sibling::td");

    public void clickBalanceEnquiry() {
        click(balanceEnquiryLink);
    }

    public void searchAccount(String accId) {
        type(accountNoField, accId);
        click(submitBtn);
    }

    public String getAccountNumber() {
        return getText(accountNoText);
    }
}