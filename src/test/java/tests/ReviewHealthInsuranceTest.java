package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.LoginPage;
import pages.ProfilePage;
import pages.ReviewPage;

public class ReviewHealthInsuranceTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private ProfilePage profilePage;
    private ReviewPage reviewPage;

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        // Initialize the page objects
        loginPage = new LoginPage(driver);
        reviewPage = new ReviewPage(driver);
        profilePage = new ProfilePage(driver);

    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }

    @Test
    public void testReviewHealthInsurance() {
        String productName = "Test Insurance Company";
        String healthInsuranceText = "Health Insurance";
        String emailAddress = "kasule08joseph@gmail.com";
        String password = "Admin@123";
        String loginUrl = "https://wallethub.com/join/login";
        String profileUrl = "https://wallethub.com/profile/13732055i";

        // Generate a random review text
        Faker faker = new Faker();
        String reviewText = faker.lorem().characters(200);

        // Login using the LoginHelper class
        driver.get(loginUrl);
        loginPage.getLoginHelper().enterEmail(emailAddress);
        loginPage.getLoginHelper().enterPassword(password);
        loginPage.getLoginHelper().clickLoginButton();
        // Assert the login page assertions
        loginPage.assertLoginPageAssertions();

        // Navigate to the review page
        driver.get(profileUrl);

        // Perform actions on the review page using the ReviewPage methods
        reviewPage.hoverOnRatingElement();
        reviewPage.assertAriaCheckedValueChangeOnHover();
        reviewPage.clickOnRatingElement();

        // Assert that the submit button is displayed
        reviewPage.assertSubmitButtonDisplayed();

        // Assert the product name
        reviewPage.assertProductName(productName);

        // Enter the review text
        reviewPage.enterReviewText(reviewText);

        // Select the "Health Insurance" option from the dropdown
        reviewPage.selectDropdownOption(healthInsuranceText);

        // Click the submit button
        reviewPage.clickSubmitButton();

        
        // verify review
        profilePage.hoverOverUserProfile();
        profilePage.clickProfileLink();
        profilePage.verifyProfilePage();
    }
}