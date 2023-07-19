package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.LoginPage;
import pages.WritePostPage;


public class FacebookStatusTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private WritePostPage writePostPage;

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        writePostPage = new WritePostPage(driver);
    }

    @Test
    public void testPostStatusMessage() {
        By loginButtonBy = By.xpath("//button[text()='Log in']");
        By passwordFieldBy = By.id("pass");
        String emailAddress = "genid87344@paldept.com";
        String password = "Admin@123";
        String postMessage = "Hello world!";
        String loginURL = "https://www.facebook.com/";

        // Perform the login actions
        driver.get(loginURL);
        loginPage.getLoginHelper().enterEmail(emailAddress);
        loginPage.getLoginHelper().enterPassword(password, passwordFieldBy);
        loginPage.getLoginHelper().clickLoginButton(loginButtonBy);

        // Navigate to home and create a post
        writePostPage.clickHomeButton();
        writePostPage.clickCreatePostElement();
        writePostPage.enterPostText(postMessage);
        writePostPage.clickPostButton();
        writePostPage.assertPostedMessageExists(postMessage);
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }
    
}
