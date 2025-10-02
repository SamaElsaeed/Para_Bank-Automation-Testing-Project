package Tests;

import Base.BaseTest;
import Pages.OpenNewAccountPage;
import Pages.TransferFundsPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TransferFundsTest extends BaseTest {
    private TransferFundsPage transferFundsPage;
    @BeforeMethod
    public void setUpPage()  {
        transferFundsPage = new TransferFundsPage(driver);

    }
    @Test(dataProvider = "userData")
    public void verifyTransferMoneySuccessfully(String username,String passwrd){
        transferFundsPage.verifyTransferMoneySuccessfully(username,passwrd);

    }
    @Test(dataProvider = "userData")
    public void VerifyThatAmountFieldRequired(String username,String passwrd){
        transferFundsPage.VerifyThatAmountFieldRequired( username,passwrd);
    }
    @Test(dataProvider = "csvCredentials")
    public void VerifyInValidDataInAmountField(String username,String passwrd,String amount){
        transferFundsPage.VerifyInValidDataInAmountField(username,passwrd,amount);
    }

}
