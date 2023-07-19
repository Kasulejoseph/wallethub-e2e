package pages;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private LoginHelper loginHelper;

    private By userButton = By.cssSelector(".brgm-user span");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        loginHelper = new LoginHelper(driver);
    }

    public LoginHelper getLoginHelper() {
        return loginHelper;
    }

    public boolean isRedirectedToValidURL(String expectedURL) {
        wait.until(ExpectedConditions.urlToBe(expectedURL));
        String currentURL = driver.getCurrentUrl();
        assertEquals(expectedURL, currentURL);
        return expectedURL.equals(currentURL);

    }

    public boolean isUserButtonDisplayed() {
        WebElement userButtonElement = wait.until(ExpectedConditions.visibilityOfElementLocated(userButton));
        return userButtonElement.isDisplayed();
    }

    public void assertLoginPageAssertions(String myWalletURL) {
        assertTrue(isRedirectedToValidURL(myWalletURL), "Assertion failed: redirection to invalid URL.");
        assertTrue(isUserButtonDisplayed(), "Assertion failed: user button does not exist.");
    }
}