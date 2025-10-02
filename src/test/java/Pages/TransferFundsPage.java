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

public class TransferFundsPage extends BaseTest {
   private WebDriver driver;
   private By transferMoneyLink =By.xpath("//a[text()='Transfer Funds']");
   private By amountField =By.id("amount");
   private By fromAccountDropdown=By.id("fromAccountId");
   private By toAccountDropdown=By.id("toAccountId");
   private By successMessage = By.xpath("//h1[text()='Transfer Complete!']");
   //private By errorMessage =By.xpath("//h1[text()='Error!']");
   private By transferButton=By.xpath("//input[@type='submit']");
    public TransferFundsPage(WebDriver driver) {
        this.driver = driver;
    }
    private void transferMoney() {
        driver.findElement(transferMoneyLink).click();
    }
    private void clickTransferButton(){
        driver.findElement(transferButton).click();
    }
    private void enterAmount(String amount) {
        driver.findElement(amountField).clear();
        driver.findElement(amountField).sendKeys(amount);
    }
    private void selectFromAccount(int index) {
        WebElement ddl = driver.findElement(fromAccountDropdown);
        Select dropdown = new Select(ddl);
        dropdown.selectByIndex(index);
    }
    private void selectToAccount(int index) {
        WebElement ddl = driver.findElement(toAccountDropdown);
        Select dropdown = new Select(ddl);
        dropdown.selectByIndex(index);
    }
    private String waitForErrorMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//p[@class='error' and normalize-space()='An internal error has occurred and has been logged.']")));

        return errorMsg.getText();
    }
    private String waitForSuccessMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(9));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        return element.getText();
    }
    public void verifyTransferMoneySuccessfully(String username,String passwrd){
        registerFirst(username,passwrd);
        transferMoney();
        driver.findElement(amountField).sendKeys("10000");
        selectFromAccount(0);
        selectToAccount(0);
        clickTransferButton();
        Assert.assertEquals(waitForSuccessMessage(), "Transfer Complete!");
    }
    public void VerifyThatAmountFieldRequired(String username , String passwrd){
        registerFirst(username,passwrd);
        transferMoney();
        selectFromAccount(0);
        selectToAccount(0);
        clickTransferButton();
        Assert.assertEquals(waitForErrorMessage(),"An internal error has occurred and has been logged.");
    }
    public void VerifyInValidDataInAmountField(String username,String passwrd,String amount){
        registerFirst(username, passwrd);
        transferMoney();
        enterAmount(amount);
        selectFromAccount(0);
        selectToAccount(0);
        clickTransferButton();
        Assert.assertEquals(waitForErrorMessage(),"An internal error has occurred and has been logged.");

    }





}
