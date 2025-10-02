package Pages;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Map;

public class BillPayPage extends BaseTest {
    private WebDriver driver;

    public BillPayPage(WebDriver driver){
        this.driver = driver;
    }

    By billPayLink = By.xpath("//a[text()='Bill Pay']");
    By payeeName = By.xpath("//input[@name='payee.name']");
    By payeeAddress = By.xpath("//input[@name='payee.address.street']");
    By payeeCity = By.xpath("//input[@name='payee.address.city']");
    By payeeState = By.xpath("//input[@name='payee.address.state']");
    By payeeZipCode = By.xpath("//input[@name='payee.address.zipCode']");
    By payeePhone = By.xpath("//input[@name='payee.phoneNumber']");
    By payeeAccount = By.xpath("//input[@name='payee.accountNumber']");
    By ConfirmAcc = By.xpath("//input[@name='verifyAccount']");
    By amount = By.xpath("//input[@name='amount']");
    By clickSend = By.xpath("//input[@value='Send Payment']");
    By successMessage = By.xpath("//h1[@class='title' and normalize-space()='Bill Payment Complete']");
    By errorMessage = By.xpath("///p[contains(text(),'Enter payee information')]");

    private void navigateToBillPay() {
        driver.findElement(billPayLink).click();
    }

    private void sendPayment() {
        driver.findElement(clickSend).click();
    }

    private String getSuccessMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        return element.getText();
    }

    private String getErrorMessage() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            return element.getText();
        } catch (Exception e) {
            return "";
        }
    }

    private void logout() {
        driver.findElement(By.xpath("//a[@href='logout.htm']")).click();
    }

    public void validateBillPayment(Map<String, Object> testData) {
        String username = testData.get("username") != null ? testData.get("username").toString() : "";
        String password = testData.get("password") != null ? testData.get("password").toString() : "";
        registerFirstPay(username, password);
        navigateToBillPay();
        fillBillPaymentForm(testData);
        sendPayment();
        verifyPaymentResult();
        logout();
    }

    private void fillBillPaymentForm(Map<String, Object> testData) {
        if (testData.get("payeeName") != null) {
            driver.findElement(payeeName).sendKeys(testData.get("payeeName").toString());
        }
        if (testData.get("payeeAddress") != null) {
            driver.findElement(payeeAddress).sendKeys(testData.get("payeeAddress").toString());
        }
        if (testData.get("payeeCity") != null) {
            driver.findElement(payeeCity).sendKeys(testData.get("payeeCity").toString());
        }
        if (testData.get("payeeState") != null) {
            driver.findElement(payeeState).sendKeys(testData.get("payeeState").toString());
        }
        if (testData.get("payeeZipCode") != null) {
            driver.findElement(payeeZipCode).sendKeys(testData.get("payeeZipCode").toString());
        }
        if (testData.get("payeePhone") != null) {
            driver.findElement(payeePhone).sendKeys(testData.get("payeePhone").toString());
        }
        if (testData.get("payeeAccount") != null) {
            driver.findElement(payeeAccount).sendKeys(testData.get("payeeAccount").toString());
        }
        if (testData.get("confirmAccount") != null) {
            driver.findElement(ConfirmAcc).sendKeys(testData.get("confirmAccount").toString());
        }
        if (testData.get("amount") != null) {
            driver.findElement(amount).sendKeys(testData.get("amount").toString());
        }
    }

    private void verifyPaymentResult() {
        String successMsg = getSuccessMessage();
        String errorMsg = getErrorMessage();

        if (!successMsg.isEmpty()) {
            Assert.assertEquals(successMsg, "Bill Payment Complete");
        } else if (!errorMsg.isEmpty()) {
            Assert.assertEquals(errorMsg, "Enter payee information");
        }
    }

    public void registerFirstPay(String username, String password) {
        try {
            driver.findElement(By.xpath("//a[@href='register.htm']")).click();
            driver.findElement(By.id("customer.firstName")).sendKeys("sama");
            driver.findElement(By.id("customer.lastName")).sendKeys("saeed");
            driver.findElement(By.id("customer.address.street")).sendKeys("cairostreet");
            driver.findElement(By.id("customer.address.city")).sendKeys("cairo");
            driver.findElement(By.id("customer.address.state")).sendKeys("cairo");
            driver.findElement(By.id("customer.address.zipCode")).sendKeys("1234");
            driver.findElement(By.id("customer.phoneNumber")).sendKeys("0123456789");
            driver.findElement(By.id("customer.ssn")).sendKeys("1234");
            driver.findElement(By.id("customer.username")).sendKeys(username);
            driver.findElement(By.id("customer.password")).sendKeys(password);
            driver.findElement(By.id("repeatedPassword")).sendKeys(password);
            driver.findElement(By.xpath("//input[@value='Register']")).click();

            WebElement successMsg = driver.findElement(By.xpath("//h1[@class='title']"));
            Assert.assertTrue(successMsg.getText().contains(username),
                    "Registration failed for user: " + username);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }
}
