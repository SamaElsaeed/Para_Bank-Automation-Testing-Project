package Pages;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class OpenNewAccountPage extends BaseTest {
    private WebDriver driver;

    private  By accountTypeDropdown = By.id("type");
    private  By fromAccountDropdown = By.id("fromAccountId");
    private  By createButton = By.xpath("//input[@value='Open New Account']");
    private  By openAccountLink = By.xpath("//a[text()='Open New Account']");
    private  By successMessage = By.xpath("//p[contains(text(),'Congratulations')]");
    private  By newAccountId = By.id("newAccountId");

    public OpenNewAccountPage(WebDriver driver) {
        this.driver = driver;
    }


    private void openNewAccountPage() {
        driver.findElement(openAccountLink).click();
    }

    private void selectAccountType(String accountType) {
        WebElement ddl = driver.findElement(accountTypeDropdown);
        Select dropdown = new Select(ddl);
        dropdown.selectByVisibleText(accountType);
    }

    private void selectFromAccount(int index) {
        WebElement ddl = driver.findElement(fromAccountDropdown);
        Select dropdown = new Select(ddl);
        dropdown.selectByIndex(index);
    }

    private String waitForSuccessMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(9));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        return element.getText();
    }

    public void openAccount(String username, String password, String accountType) {
        registerFirst(username, password);
        openNewAccountPage();
        selectAccountType(accountType);
        selectFromAccount(0);
        driver.findElement(createButton).click();
        Assert.assertEquals(waitForSuccessMessage(), "Congratulations, your account is now open.");
    }

    public void openAccountWithoutDeposit(String username, String password) {
        registerFirst(username, password);
        openNewAccountPage();
        selectAccountType("CHECKING");
        driver.findElement(createButton).click();
        Assert.assertEquals(waitForSuccessMessage(), "Enter Account number");
    }

    public void verifyAccountAppearsInOverview(String username, String password) {
        registerFirst(username, password);
        openNewAccountPage();
        selectAccountType("CHECKING");
        selectFromAccount(0);
        driver.findElement(createButton).click();

        String accountId = driver.findElement(newAccountId).getText();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(9));
        boolean isAccountPresent = wait.until(
                ExpectedConditions.textToBePresentInElementLocated(
                        By.xpath("//a[contains(@href,'activity.htm?id=')]"), accountId)
        );

        Assert.assertTrue(isAccountPresent, "Account not found in overview: " + accountId);
    }
}


