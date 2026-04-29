package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class EditCustomerPage extends BasePage {

    public EditCustomerPage(WebDriver driver) {
        super(driver);
    }

    private By editCustomerLink = By.linkText("Edit Customer");
    private By customerIdField = By.name("cusid");
    private By submitBtn = By.name("AccSubmit");

    private By address = By.name("addr");
    private By updateBtn = By.name("sub");

    private By successMsg = By.xpath("//p[@class='heading3']");

    public void clickEditCustomer() {
        click(editCustomerLink);
    }

    public void enterCustomerId(String id) {
        type(customerIdField, id);
    }

    public void submitCustomerId() {
        click(submitBtn);
    }

    public void updateAddress(String newAddress) {
        type(address, newAddress);
    }

    public void submitUpdate() {
        click(updateBtn);
    }

    public String getUpdateMessage() {
        return getText(successMsg);
    }
}