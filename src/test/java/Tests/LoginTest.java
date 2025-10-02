package Tests;

import Base.BaseTest;
import Pages.LoginPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    private LoginPage loginPage;

    @BeforeMethod
    public void setUpPage() {
        loginPage = new LoginPage(driver);
    }

    @Test(dataProvider = "userData")
    public void verifythatusercanloginsuccessfullywithvalidcredential(String username,String passwrd){

        loginPage.verifythatusercanloginsuccessfullywithvalidcredential(username,passwrd);
    }

    @Test(dataProvider = "credentials", description = "Test login with invalid data")
    public void verifyThatUserCannotloginWithInvalidCredentials(String username, String pass) {

          loginPage.loginWithInValidData(username, pass);
    }
    @Test(dataProvider = "userData")
    public void verifyForwardNavigationDoesNotBypassLogin(String username,String passwrd){
        loginPage.verifyForwardNavigationDoesNotBypassLogin(username,passwrd);
    }

}
