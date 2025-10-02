package Utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class JsonUtils {
    private Map<String, Object> data;

    public JsonUtils(String filePath) {
        try {
            Gson gson = new Gson();
            FileReader reader = new FileReader(filePath);
            Type type = new TypeToken<Map<String, Object>>(){}.getType();
            data = gson.fromJson(reader, type);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("JSON file not found: " + filePath, e);
        }
    }

    public Object getValue(String key) {
        return data != null ? data.get(key) : null;
    }

    public List<Map<String, Object>> getBillPayments() {
        if (data == null) {
            throw new IllegalStateException("JSON data not loaded properly");
        }
        return (List<Map<String, Object>>) data.get("billPayments");
    }

    // Helper method to get specific test case
    public Map<String, Object> getTestCase(int index) {
        List<Map<String, Object>> testCases = getBillPayments();
        if (index >= 0 && index < testCases.size()) {
            return testCases.get(index);
        }
        throw new IndexOutOfBoundsException("Test case index out of bounds: " + index);
    }

    // Get total number of test cases
    public int getTestCaseCount() {
        return getBillPayments().size();
    }
    public String getExpectedResult(Map<String, Object> testData) {
        return testData.get("expectedResult") != null ?
                testData.get("expectedResult").toString() : "success";
    }

    public String getTestCaseName(Map<String, Object> testData) {
        return testData.get("testCase") != null ?
                testData.get("testCase").toString() : "Unnamed Test Case";
    }
}
