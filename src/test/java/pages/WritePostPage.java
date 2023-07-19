package pages;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WritePostPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By homeButtonBy = By.cssSelector("a[aria-label='Home']");
    private By homeBy = By.cssSelector("body");
    private By createPostElementBy = By.xpath("//span[contains(text(), \"What's on your mind\")]");
    private By createPostInputElementBy = By.xpath("//div[contains(@aria-label, \"What's on your mind\")]");
    private By postButtonElementBy = By.xpath("//div[contains(@aria-label, \"Post\")]");

    public WritePostPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 20);
    }

    public void clickHomeButton() {
        WebElement homeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(homeButtonBy));
        WebElement home = wait.until(ExpectedConditions.visibilityOfElementLocated(homeBy));
        home.click();
        homeButton.click();
    }

    public void clickCreatePostElement() {
        WebElement createPostElement = wait.until(ExpectedConditions.visibilityOfElementLocated(createPostElementBy));
        createPostElement.click();
    }

    public void enterPostText(String postText) {
        WebElement createPostInputElement = wait
                .until(ExpectedConditions.visibilityOfElementLocated(createPostInputElementBy));
        createPostInputElement.clear();
        createPostInputElement.sendKeys(postText);
    }

    public void clickPostButton() {
        WebElement postButton = driver.findElement(postButtonElementBy);
        postButton.click();
    }

    public void assertPostedMessageExists(String message) {
        WebElement spanElement = driver.findElement(By.xpath("//span[text()='" + message + "']"));
        assertTrue(spanElement.isDisplayed(), "The message '" + message + "' does not exist.");
    }
}
