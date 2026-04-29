package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private By username = By.name("uid");
    private By password = By.name("password");
    private By loginBtn = By.name("btnLogin");

    // Actions
    public void enterUsername(String user) {
        type(username, user);
    }

    public void enterPassword(String pass) {
        type(password, pass);
    }

    public void clickLogin() {
        click(loginBtn);
    }

    // Combined method
    public void login(String user, String pass) {
        enterUsername(user);
        enterPassword(pass);
        clickLogin();
    }

    // Validation method
    public boolean isLoginPageDisplayed() {
        return driver.getTitle().contains("Guru99 Bank Home Page");
    }
}