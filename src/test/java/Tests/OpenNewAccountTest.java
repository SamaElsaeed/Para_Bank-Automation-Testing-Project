package Tests;

import Base.BaseTest;
import Pages.OpenNewAccountPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class OpenNewAccountTest extends BaseTest {
    private OpenNewAccountPage openNewAccountPage;
    @BeforeMethod
    public void setUpPage()  {
        openNewAccountPage = new OpenNewAccountPage(driver);

    }

    @Test(dataProvider = "userData")
    public void openNewSavingAccount(String username,String passwrd) {
        openNewAccountPage.openAccount(username,passwrd,"SAVINGS");

    }
    @Test(dataProvider = "userData")
    public void openNewCheckingAccount(String username,String passwrd)  {
        openNewAccountPage.openAccount(username,passwrd,"CHECKING");
    }
    @Test(dataProvider = "userData")
    public void openAccountWithoutDeposit(String username,String passwrd)  {
        openNewAccountPage.openAccountWithoutDeposit(username,passwrd);
    }
    @Test(dataProvider = "userData")
    public void verifyAccountAppearsInOverview(String username,String passwrd){
        openNewAccountPage.verifyAccountAppearsInOverview(username,passwrd);
    }
}
