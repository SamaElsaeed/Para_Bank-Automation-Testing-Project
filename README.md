ParaBank Test Automation Project
📌 Project Overview

This project automates functional testing of the ParaBank Demo Application: https://parabank.parasoft.com.
It is built using Java, Selenium WebDriver, TestNG, Maven, and Allure Reports with complete Data-Driven Testing (DDT) support.
The framework ensures scalability, reusability, and maintainability, covering both positive and negative test cases across critical ParaBank functionalities.

🚀 How to Run the Project

1️⃣ Prerequisites
Install Java JDK 17+
Install IntelliJ IDEA (Community/Ultimate)
Install Apache Maven
Configure Environment Variables (JAVA_HOME, MAVEN_HOME)
Install Allure Commandline for test reporting

2️⃣ Clone the Repository
git clone https://github.com/<your-username>/<your-repo>.git
cd <your-repo>

3️⃣ Run the Tests
Run all tests using Maven:
mvn clean test

4️⃣ Generate Allure Report
allure serve allure-results


This will open a live interactive report in your browser.

🏗️ Framework Overview

Programming Language: Java
Test Framework: TestNG
Build Tool: Maven
Reporting: Allure Reports
Data-Driven Testing (DDT):
Excel
CSV
JSON
Properties Files
TestNG DataProvider
Design Pattern: Page Object Model (POM)
Assertions: TestNG Assert

✅ Test Scenarios

🔹 Precondition: Account Registration
Ensures that a valid user account exists before running scenarios.
Register a new user with valid details.
Verify success message:
“Your account was created successfully. You are now logged in.”
Logout to prepare for test execution.
🔹 Scenario 1: Login Verification
Objective: Validate login functionality with valid & invalid credentials.
Steps:
Enter valid credentials → Validate successful login.
Enter invalid credentials → Validate error message.
DDT Applied: Excel with DataProvider

🔹 Scenario 2: Open New Account
Ojective: Verify account creation process (Checking/Savings).
Steps:
Navigate to Open Account → Select type + funding account.
Confirm new account number displayed.
Verify account appears in Accounts Overview.
DDT Applied:DataProvider

🔹 Scenario 3: Transfer Funds
Objective: Validate fund transfer between accounts.
Steps:
Select From & To Accounts + Amount.
Confirm transfer details.
Verify updated balances in Accounts Overview.
DDT Applied: CSV, DataProvider

🔹 Scenario 4: Bill Pay
Objective: Ensure successful bill payment.
Steps:
Enter Payee Details (Name, Address, Account, Amount).
Validate confirmation page.
Verify transaction in Accounts Overview.
DDT Applied:JSON

🔍 Test Coverage

Positive Test Cases
Register new account
Login with valid credentials
Open new account with valid data
Transfer funds with valid amount
Bill payment with valid payee details

Negative Test Cases
Login with invalid credentials
Register with missing mandatory fields
Transfer with invalid/missing accounts
Bill pay with empty/invalid details
Enter negative/invalid amounts in transfers or payments

📊 Reporting

All test executions generate Allure Reports, which provide a clear and interactive view of test results:


Step-by-step execution logs for each test case
Automatic screenshots on failure for debugging
Detailed test history & trends (pass/fail/skip rate over time)
Scenario-wise breakdown (Login, Account Creation, Transfer, Bill Pay)
Execution time tracking for performance insights
Data-driven run details (inputs from Excel, CSV, JSON, Properties, DataProvider)

Screenshots for failures

Test history and trends
