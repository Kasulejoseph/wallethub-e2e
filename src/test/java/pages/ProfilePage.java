package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ProfilePage {
    private WebDriver driver;
    private Actions actions;

    private By userProfile = By.className("brgm-user");
    private By profileLink = By.xpath("//a[text()='Profile']");
    private By profileName = By.cssSelector("h1.profile-name");
    private By recommendationTitle = By.cssSelector("h2.pr-rec-title");
    private By companyName = By.cssSelector("a[href*='test-insurance-company']");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    public void hoverOverUserProfile() {
        WebElement userProfileElement = driver.findElement(userProfile);
        actions.moveToElement(userProfileElement).perform();
    }

    public void clickProfileLink() {
        WebElement profileLinkElement = driver.findElement(profileLink);
        profileLinkElement.click();
    }

    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    public boolean isProfileNameDisplayed() {
        WebElement profileNameElement = driver.findElement(profileName);
        return profileNameElement.isDisplayed();
    }

    public boolean isRecommendationTitleCorrect(String expectedRecommendationTitleRegex) {
        WebElement recommendationTitleElement = driver.findElement(recommendationTitle);
        return recommendationTitleElement.getText().matches(expectedRecommendationTitleRegex);
    }

    public boolean isCompanyNameCorrect(String insuranceCompany) {
        WebElement companyNameElement = driver.findElement(companyName);
        return companyNameElement.getText().equals(insuranceCompany);
    }

    public void verifyProfilePage(String expectedURLPattern) {
        String currentURL = getCurrentURL();
        assert currentURL.matches(expectedURLPattern) : "Assertion failed: Direction to URL is incorrect.";

        assert isProfileNameDisplayed() : "Assertion failed: Profile name does not exist.";
        assert isRecommendationTitleCorrect("\\w+'s Recommendations")
                : "Assertion failed: Recommendation title is incorrect.";
        assert isCompanyNameCorrect("Test Insurance Company") : "Assertion failed: Company name is incorrect.";
    }
}
