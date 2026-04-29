package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    // Logout link
    By logoutLink = By.linkText("Log out");

    public void clickLogout() {
        click(logoutLink);
    }
}