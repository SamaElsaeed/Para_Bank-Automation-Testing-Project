package Tests;

import Base.BaseTest;
import Pages.BillPayPage;
import Utils.JsonUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BillPayTest extends BaseTest {
    private BillPayPage billPayPage;

    @BeforeMethod
    public void setUpPage() {
        billPayPage = new BillPayPage(driver);
    }

    @DataProvider(name = "billPaymentData")
    public Object[][] getBillPaymentData() {
        JsonUtils jsonUtils = new JsonUtils("src/test/resources/BillPay.json");
        List<Map<String, Object>> testCases = jsonUtils.getBillPayments();

        // Convert list of maps to Object[][]
        List<Object[]> testData = new ArrayList<>();
        for (Map<String, Object> testCase : testCases) {
            // Create a new map for each test case to avoid reference issues
            Map<String, Object> caseData = new HashMap<>(testCase);
            testData.add(new Object[]{caseData});
        }

        return testData.toArray(new Object[0][]);
    }

    @Test(dataProvider = "billPaymentData")
    public void testBillPayment(Map<String, Object> testData) {
        System.out.println("Executing test case with data: " + testData);
        billPayPage.validateBillPayment(testData);
    }
}


