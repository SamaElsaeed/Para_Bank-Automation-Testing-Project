package Base;

import Utils.CSVFileManager;
import Utils.ConfigLoader;
import Utils.ExcelUtils;
import Utils.JsonUtils;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Random;

public class BaseTest {
    protected static WebDriver driver;
    //JsonUtils jsonUtils=new JsonUtils("");

    @BeforeMethod
    public void openWebsite() throws IOException {
        driver=new ChromeDriver();
        ConfigLoader configLoader=new ConfigLoader("src/test/resources/config.Properties");
        driver.get(configLoader.getValue("url"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }
    @DataProvider(name = "credentials")
    public Object[][] credentialsDataProvider() {
        ExcelUtils excel = new ExcelUtils("src/test/resources/TestData -2.xlsx", "Sheet1");
        return excel.getSheetDataAsArray();
    }

    public void takeScreenshot(String fileName) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            File directory = new File("screenshots");
            if (!directory.exists()) {
                directory.mkdir();
            }
            FileUtils.copyFile(screenshot, new File(directory + "/" + fileName + ".png"));
            Allure.addAttachment("ScreenShots",directory + "/" + fileName + ".png");
            System.out.println("Screenshot saved: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @DataProvider(name = "userData")
    public Object[][] userData() {
        Random rand = new Random();
        int randomNum = rand.nextInt(100000); // random number up to 5 digits
        String uniqueUser = "user" + randomNum;
        return new Object[][]{
                {uniqueUser, "sam@123"}
        };
    }
    @DataProvider(name = "csvCredentials")
    public Object[][] credentialsCsvData() {
        CSVFileManager csv = new CSVFileManager("src/test/resources/AmountOfMoney.csv");
        return csv.getDataAsObjectArray();
    }

    public void registerFirst(String username, String password) {
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
            Assert.assertTrue(successMsg.getText().contains(username));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }

    @AfterMethod
    public void failureTestCases(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            takeScreenshot(result.getName() + "_failed");
        }
        driver.quit();
    }
}
