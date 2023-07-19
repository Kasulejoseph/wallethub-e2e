package pages;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReviewPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By ratingElementBy = By.xpath("(//div[@class='rating-box-wrapper'])[3]//*[@aria-label='4 star rating']");
    private By writeReviewElementBy = By.tagName("write-review");
    private By submitButtonBy = By.xpath("//div[contains(text(),'Submit')]");
    private By productNameBy = By.className("wrev-prd-name");
    private By reviewTextAreaBy = By.cssSelector("textarea.wrev-user-input");
    private By dropdownBy = By.xpath("//span[text()='Select...']");

    public ReviewPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public void hoverOnRatingElement() {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(ratingElementBy));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public void assertAriaCheckedValueChangeOnHover() {
        WebElement element = driver.findElement(ratingElementBy);
        String initialCheckValue = element.getAttribute("aria-checked");

        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();

        String updatedCheckValue = element.getAttribute("aria-checked");

        assert !initialCheckValue.equals(updatedCheckValue) && updatedCheckValue.equals("true")
                : "Assertion failed: aria-checked value did not change to true on hover.";
    }

    public void clickOnRatingElement() {
        WebElement element = driver.findElement(ratingElementBy);
        element.click();
    }

    public void assertSubmitButtonDisplayed() {
        wait.until(ExpectedConditions.presenceOfElementLocated(writeReviewElementBy));
        WebElement submitButton = driver.findElement(submitButtonBy);
        assert submitButton.isDisplayed() : "Assertion failed: Submit button is not displayed.";
    }

    public void assertProductName(String expectedProductName) {
        WebElement productNameElement = driver.findElement(productNameBy);
        String productName = productNameElement.getText();
        assertEquals(expectedProductName, productName);
    }

    public void enterReviewText(String reviewText) {
        WebElement reviewTextArea = driver.findElement(reviewTextAreaBy);
        reviewTextArea.clear();
        reviewTextArea.sendKeys(reviewText);
    }

    public void selectDropdownOption(String optionText) {
        WebElement dropdown = driver.findElement(dropdownBy);
        dropdown.click();

        WebElement option = wait
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[text()='" + optionText + "']")));
        option.click();
    }

    public void clickSubmitButton() {
        WebElement submitButton = driver.findElement(submitButtonBy);
        submitButton.click();
    }
}
