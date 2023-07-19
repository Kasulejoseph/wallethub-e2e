package tests;

import java.util.Properties;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.LoginPage;
import pages.WritePostPage;
import utils.PropertiesLoader;

public class FacebookStatusTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private WritePostPage writePostPage;
    private Properties properties;

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        writePostPage = new WritePostPage(driver);
        properties = new Properties();

        // Load properties from the file
        properties = PropertiesLoader.loadProperties("messages.properties");
    }

    @Test
    public void testPostStatusMessage() {
        By loginButtonBy = By.xpath("//button[text()='Log in']");
        By passwordFieldBy = By.id("pass");
        String emailAddress = properties.getProperty("login.emailAddress");
        String password = properties.getProperty("login.password");
        String postMessage = properties.getProperty("post.message");
        String loginURL = properties.getProperty("login.url");

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
