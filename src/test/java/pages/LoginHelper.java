package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginHelper {
    private WebDriver driver;

    private By emailField = By.id("email");
    private By passwordFieldBy = By.id("password");
    private By loginButtonBy = By.xpath("//button[span[text()='Login']]");

    public LoginHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void enterEmail(String email) {
        WebElement emailFieldElement = driver.findElement(emailField);
        emailFieldElement.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passwordFieldElement = driver.findElement(passwordFieldBy);
        passwordFieldElement.sendKeys(password);
    }

    public void enterPassword(String password, By passwordField) {
        WebElement passwordFieldElement = driver.findElement(passwordField);
        passwordFieldElement.sendKeys(password);
    }

    public void clickLoginButton() {
        clickLoginButton(loginButtonBy);
    }

    public void clickLoginButton(By loginButton) {
        WebElement loginButtonElement = driver.findElement(loginButton);
        loginButtonElement.click();
    }
}