package Pages;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LoginPage extends BaseTest {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    By userName=By.xpath("//input[@name='username']");
    By password=By.xpath("//input[@name='password']");
    By loginButton=By.xpath("//input[@class='button']");


    public void verifythatusercanloginsuccessfullywithvalidcredential(String username,String passwrd){
        registerFirst(username,passwrd);
        driver.findElement(By.xpath("//a[@href='logout.htm']")).click();
        driver.findElement(userName).sendKeys(username);
        driver.findElement(password).sendKeys(passwrd);
        driver.findElement(loginButton).click();
        WebElement successMsg = driver.findElement(By.xpath("//h1[contains(text(),'Accounts Overview')]"));
        Assert.assertTrue(successMsg.getText().contains("Accounts Overview"));

    }

    public void loginWithInValidData(String username, String pass) {
        driver.findElement(userName).sendKeys(username);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(loginButton).click();
        By errorMsg = By.xpath("//p[contains(text(),'could not be verified')]");
        WebElement errorElement = driver.findElement(errorMsg);
        Assert.assertEquals(
                errorElement.getText(),
                "The username and password could not be verified."
        );

    }
    public void verifyForwardNavigationDoesNotBypassLogin(String username,String passwrd){
        registerFirst(username,passwrd);
        driver.findElement(By.xpath("//a[@href='logout.htm']")).click();
        driver.navigate().back();
        WebElement errorMessage = driver.findElement(By.xpath("//span[text()='Confirm Form Resubmission']"));
        Assert.assertEquals(errorMessage.getText(), "Confirm Form Resubmission");


    }

}
