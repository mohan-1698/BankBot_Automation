package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;
import utils.ConfigReader;

public class NewCustomerPage extends BasePage {

    public NewCustomerPage(WebDriver driver) {
        super(driver);
    }

    // 🔹 Navigation
    private By newCustomerLink = By.linkText("New Customer");

    // 🔹 Form Fields
    private By name = By.name("name");
    private By gender = By.xpath("//input[@value='f']");
    private By dob = By.name("dob");
    private By address = By.name("addr");
    private By city = By.name("city");
    private By state = By.name("state");
    private By pin = By.name("pinno");
    private By mobile = By.name("telephoneno");
    private By email = By.name("emailid");
    private By password = By.name("password");
    private By submit = By.name("sub");

    // 🔹 Success Elements
    private By successMessage = By.xpath("//p[@class='heading3']");
    private By customerId = By.xpath("//td[text()='Customer ID']/following-sibling::td");

    // 🔹 Validation Messages
    private By nameError = By.id("message");
    private By cityError = By.id("message4");
    private By stateError = By.id("message5");
    private By pinError = By.id("message6");

    // =========================
    // 🔹 ACTION METHODS
    // =========================

    public void clickNewCustomer() {
        click(newCustomerLink);
    }

    // ✅ Default DOB (from config)
    public void enterDOB() {
        driver.findElement(dob).clear();
        driver.findElement(dob).sendKeys(ConfigReader.get("custDob"));
    }

    // ✅ Dynamic DOB (for validation)
    public void enterDOB(String date) {
        driver.findElement(dob).clear();
        driver.findElement(dob).sendKeys(date);
    }

    // ✅ Enter PIN (for validation)
    public void enterPin(String pinValue) {
        type(pin, pinValue);
    }

    // 🔹 Fill Form
    public void fillCustomerForm(String emailValue) {

        type(name, ConfigReader.get("custName"));
        click(gender);

        enterDOB(); // default DOB

        type(address, ConfigReader.get("custAddress"));
        type(city, ConfigReader.get("custCity"));
        type(state, ConfigReader.get("custState"));
        type(pin, ConfigReader.get("custPin"));
        type(mobile, ConfigReader.get("custMobile"));
        type(email, emailValue);
        type(password, ConfigReader.get("custPassword"));
    }

    public void submit() {
        click(submit);
    }

    // =========================
    // 🔹 VALIDATION METHODS
    // =========================

    public void submitEmptyForm() {
        click(submit);
    }

    public String getNameError() {
        return getText(nameError);
    }

    public String getCityError() {
        return getText(cityError);
    }

    public String getStateError() {
        return getText(stateError);
    }

    public String getPinError() {
        return getText(pinError);
    }

    // =========================
    // 🔹 RESULT METHODS
    // =========================

    public String getCustomerId() {
        return getText(customerId);
    }

    public String getSuccessMessage() {
        return getText(successMessage);
    }
}