# Bank_Bot - Banking Application Automation Testing Framework

## Project Overview

Bank_Bot is a comprehensive Selenium WebDriver-based test automation framework designed for testing banking applications. The framework implements the Page Object Model (POM) pattern and covers end-to-end banking operations including login, customer management, account operations, fund transfers, and form validation using TestNG with data-driven testing and Extent Reports.

Framework Type: Selenium WebDriver + TestNG  
Language: Java  
Build Tool: Maven  
Reporting: Extent Reports  
Data-Driven Testing: Excel (Apache POI)  
Banking Application: Guru99 Bank

---

## Project Structure

```
Bank_Bot/
|
├── src/
|   |
|   ├── main/
|   |   └── java/
|   |       ├── base/
|   |       |   ├── BaseTest.java              (Setup/Teardown configuration)
|   |       |   └── BasePage.java              (Common page methods & waits)
|   |       |
|   |       ├── pages/
|   |       |   ├── LoginPage.java             (Login page operations)
|   |       |   ├── HomePage.java              (Home page navigation)
|   |       |   ├── NewCustomerPage.java       (Customer creation form)
|   |       |   ├── EditCustomerPage.java      (Customer editing)
|   |       |   ├── NewAccountPage.java        (Account creation form)
|   |       |   ├── EditAccountPage.java       (Account editing)
|   |       |   ├── FundTransferPage.java      (Fund transfer operations)
|   |       |   ├── DepositPage.java           (Deposit operations)
|   |       |   └── BalanceEnquiryPage.java    (Account balance search)
|   |       |
|   |       ├── utils/
|   |       |   ├── ConfigReader.java          (Read config.properties)
|   |       |   ├── DriverFactory.java         (WebDriver initialization)
|   |       |   ├── ExcelUtil.java             (Read test data from Excel)
|   |       |   ├── ScreenshotUtil.java        (Screenshot capture)
|   |       |   └── ExtentManager.java         (Extent report management)
|   |       |
|   |       └── com/srm/Bank_Bot/
|   |           └── App.java                   (Main application class)
|   |
|   └── test/
|       ├── java/
|       |   ├── com/srm/Bank_Bot/
|       |   |   └── AppTest.java               (Sample test class)
|       |   |
|       |   ├── tests/
|       |   |   ├── LoginTest.java             (Login & logout testing)
|       |   |   ├── CustomerTest.java          (Customer CRUD operations)
|       |   |   ├── AccountTest.java           (Account management testing)
|       |   |   ├── FundTransferTest.java      (Fund transfer testing)
|       |   |   └── FormValidationTest.java    (Form field validation)
|       |   |
|       |   └── listeners/
|       |       └── TestListener.java          (TestNG listener for reporting)
|       |
|       └── resources/
|           ├── config.properties              (Test configuration & test data)
|           ├── Login.xlsx                     (Login test data (Excel)
|           └── testng.xml                     (Test suite configuration)
|
├── reports/
|   └── ExtentReport.html                      (Generated HTML test report)
|
├── screenshots/
|   └── [Failed test screenshots]              (Captured on test failures)
|
├── target/                                    (Build artifacts)
|
├── test-output/                               (TestNG reports)
|
├── pom.xml                                    (Maven dependencies & build config)
├── .project                                   (Eclipse project file)
├── .classpath                                 (Eclipse classpath)
├── .git/                                      (Git repository)
├── .gitignore                                 (Git ignore rules)
└── README.md                                  (This file)
```

---

## Test Modules Details

### 1. LoginTest.java

Comprehensive login and logout testing with data-driven approach.

**Test Methods:**
- verifyLogin(String user, String pass, String expected) - Data-driven login test
- verifyLogout() - Logout functionality testing

**Data Source:**
- Excel file: src/test/resources/Login.xlsx
- Test data includes: valid credentials, invalid credentials, blank fields

**Operations:**
1. Read login data from Excel
2. Enter username and password
3. Validate login based on expected result:
   - Valid: Verify "Manager" in page title
   - Invalid/Blank: Capture alert message and screenshot
4. Accept alert
5. Logout with alert confirmation
6. Verify redirection to login page

**Features:**
- Data-driven testing with Excel integration
- Alert handling for error scenarios
- Screenshot capture on login failure
- TestNG listener integration

---

### 2. CustomerTest.java

Customer management operations including creation, validation, and editing.

**Test Methods:**
- customerManagementFlow() - Complete customer lifecycle

**Operations:**
1. Login to banking application
2. Create new customer with unique email (timestamp-based)
3. Handle form submission alerts
4. Verify customer creation success message
5. Extract generated customer ID
6. Attempt duplicate email creation
7. Validate duplicate email error/alert
8. Edit customer record
9. Update customer address
10. Verify update success message

**Features:**
- Unique email generation with timestamp
- Alert handling for form errors
- Success message validation
- Customer ID extraction
- Duplicate email validation

---

### 3. AccountTest.java

Account management operations including creation, validation, and editing.

**Test Methods:**
- accountManagementFlow() - Complete account lifecycle with detailed logging

**Operations:**
1. Login to application
2. Create customer with unique email
3. Create first account for customer
4. Create second account for same customer
5. Extract account IDs
6. Test invalid customer ID for account creation
7. Validate invalid customer alert
8. Search account in balance enquiry
9. Verify account number in listing
10. Edit account details
11. Update account type
12. Verify account update success

**Features:**
- Multiple account creation for one customer
- Invalid customer ID validation
- Account search and verification
- Account modification
- Comprehensive console logging
- Exception handling for site variations

---

### 4. FundTransferTest.java

Complete fund transfer workflow including deposit and balance verification.

**Test Methods:**
- fundTransferFlow() - Complete fund transfer process

**Operations:**
1. Login to application
2. Create customer
3. Create two accounts for customer
4. Deposit amount to first account
5. Verify deposit success message
6. Transfer funds from account 1 to account 2
7. Verify transfer success message
8. Check balance in account 1 after transfer
9. Test invalid payee account
10. Validate invalid account alert

**Features:**
- Two-account fund transfer flow
- Deposit before transfer
- Balance verification after transfer
- Invalid payee validation
- Transaction success message validation
- Comprehensive logging

---

### 5. FormValidationTest.java

Form field validation testing for customer registration.

**Test Methods:**
- formValidationFlow() - Complete form validation scenarios

**Validation Tests:**
1. Empty Form Submission
   - Verify "must not be blank" error for name, city, state
2. Invalid PIN (Non-Numeric)
   - Enter alphabetic PIN
   - Verify "must be numeric" error
3. Future Date of Birth
   - Enter future date
   - Validate rejection with configured validation text

**Features:**
- Multiple field validation in one flow
- Error message extraction and verification
- Dynamic validation text from configuration
- Future date scenario handling

---

## Page Objects

All page interactions are implemented using Page Object Model (POM) pattern:

| Page Class | Purpose |
|-----------|---------|
| LoginPage | Login form and logout operations |
| HomePage | Home page logout and navigation |
| NewCustomerPage | Customer registration form |
| EditCustomerPage | Customer detail editing |
| NewAccountPage | Account creation form |
| EditAccountPage | Account modification |
| FundTransferPage | Fund transfer between accounts |
| DepositPage | Money deposit operations |
| BalanceEnquiryPage | Account balance search |

---

## Base Classes

### BaseTest.java

Provides setup and teardown for all tests.

**Methods:**
- setUp() (Before each test)
  - Initialize ConfigReader
  - Initialize WebDriver using DriverFactory
  - Opens banking application

- tearDown() (After each test)
  - Closes WebDriver
  - Cleans up resources

**Implementation:**
- Uses TestNG annotations
- Public WebDriver instance for test classes

### BasePage.java

Common methods and utilities for all page objects.

**Methods:**
- type(By locator, String text) - Enter text with clear
- click(By locator) - Click element
- getText(By locator) - Extract element text

**Features:**
- WebDriverWait integration
- Configuration-driven timeout
- Explicit waits for stability
- Timeout loaded from properties

---

## Configuration Management

### config.properties

Contains externalized test configuration:
- Browser settings (chrome)
- Application URL (Guru99 Bank)
- Admin credentials (username, password)
- Test data (invalid customer ID, amounts)
- Deposit and transfer amounts
- Validation text for form errors
- Future and invalid date formats

---

## Data-Driven Testing

### Excel Data (Apache POI)

**File:** src/test/resources/Login.xlsx

**Structure:**
- Row 1: Headers (username, password, expected)
- Rows 2+: Test scenarios

**Test Cases:**
- Valid login credentials
- Invalid credentials
- Blank fields

---

## Dependencies

Maven Dependencies (from pom.xml):

| Dependency | Version | Purpose |
|-----------|---------|---------|
| Selenium Java | 4.21.0 | WebDriver and element interaction |
| TestNG | 7.11.0 | Test framework and annotations |
| WebDriverManager | 5.8.0 | Automatic Chrome driver management |
| Apache POI OOXML | 5.2.5 | Excel file reading (.xlsx) |
| Extent Reports | 5.1.1 | HTML test reporting |
| Commons-IO | 2.15.1 | File I/O operations |
| JUnit | 3.8.1 | Additional assertions |

---

## TestNG Suite Configuration (testng.xml)

**Suite Name:** Suite

**Listeners:**
- TestListener class for screenshot capture

**Test Modules:**
1. Login Tests
2. Customer Tests
3. Account Tests
4. Fund Transfer Tests
5. Form Validation Tests

**Execution:**
- Sequential (parallel="false")
- All modules run in order

---

## How to Run Tests

### Prerequisites:
- Java 8 or higher
- Maven 3.6+
- Chrome browser installed
- Banking application access (Guru99 Bank)

### Run All Tests:
```bash
mvn clean test
```

### Run with TestNG.xml:
```bash
mvn clean test -DsuiteXmlFile=src/test/resources/testng.xml
```

### Run Specific Test Class:
```bash
mvn clean test -Dtest=LoginTest
```

### Run Specific Test Method:
```bash
mvn clean test -Dtest=CustomerTest#customerManagementFlow
```

### Using Maven Surefire Plugin:
```bash
mvn clean verify
```

---

## Test Execution & Reporting

### Extent Report

**Location:** reports/ExtentReport.html

**Contains:**
- Test execution summary (Pass/Fail/Skip)
- Test timeline
- Screenshots on failure
- Detailed execution logs
- Error stack traces

### Screenshots

**Location:** screenshots/

**Captured On:**
- Test failure
- Login failure scenarios
- Alert dialogs
- File naming: {TestMethodName}.png

### Console Output

Comprehensive logging includes:
- Login success/failure messages
- Customer ID generation
- Account creation details
- Fund transfer amounts
- Balance verification
- Validation results

---

## Alert Handling

The framework implements comprehensive alert handling for:

**Login Alerts:**
- "User or Password is not valid" - Invalid credentials
- Captured with screenshot before acceptance

**Form Alerts:**
- Field validation errors
- Duplicate email alerts
- Invalid account alerts

**Logout Alert:**
- Confirmation message before logout

**Code Pattern:**
```java
try {
    Alert alert = driver.switchTo().alert();
    String alertText = alert.getText();
    System.out.println("Alert: " + alertText);
    alert.accept();
} catch (Exception e) {
    // Handle if alert not present
}
```

---

## Key Features

[+] Comprehensive banking application testing  
[+] Page Object Model (POM) implementation  
[+] Data-driven testing with Excel integration  
[+] Configuration-driven test execution  
[+] Complete customer and account lifecycle testing  
[+] Fund transfer and deposit operations  
[+] Form field validation testing  
[+] Alert handling for error scenarios  
[+] Screenshot capture on failure  
[+] TestNG listener integration  
[+] Extent Reports for detailed HTML reporting  
[+] Dynamic email generation with timestamps  
[+] Unique email validation  
[+] Multiple test scenarios (valid, invalid, blank)  
[+] Comprehensive console logging  

---

## Design Patterns Used

1. Page Object Model (POM) - Separation of test logic and page locators
2. Singleton Pattern - ExtentManager for single report instance
3. Factory Pattern - DriverFactory for WebDriver initialization
4. Base Class Pattern - BasePage & BaseTest for code reuse
5. Data-Driven Testing - TestNG DataProvider with Excel
6. Listener Pattern - TestListener for cross-cutting concerns

---

## Best Practices Implemented

1. Explicit waits used for element interaction
2. Configuration externalized for flexibility
3. Data-driven tests for scalability
4. Screenshot capture on failure for debugging
5. Comprehensive reporting with Extent Reports
6. Meaningful test method names
7. Alert handling with proper exception catching
8. Reusable page methods
9. Dynamic test data generation
10. Comprehensive logging for debugging

---

## Common Test Scenarios

### 1. Login Flow
- Valid credentials login
- Invalid credentials with alert
- Blank fields validation
- Logout with confirmation

### 2. Customer Management
- Create new customer
- Duplicate email handling
- Edit customer details
- Address update verification

### 3. Account Operations
- Create account for customer
- Invalid customer ID validation
- Search account in listing
- Edit account type

### 4. Fund Transfer
- Two-account setup
- Deposit to account
- Transfer between accounts
- Balance verification after transfer
- Invalid payee validation

### 5. Form Validation
- Empty form submission
- Required field errors
- Non-numeric PIN validation
- Future date of birth rejection

---

## Configuration Properties Reference

Key configuration entries in config.properties:

```
browser=chrome
baseUrl=https://www.demo.guru99.com/v4/index.php
user=mngr659083
pass=UjAvEsE
timeout=10
invalidCustomerId=999999
depositAmount=5000
transferAmount=1000
invalidAccount=999
invalidTransferAmount=5000
invalidPin=ABC123
futureDob=01/01/2050
validationText=DOB must be in valid format and less than todays date
```

---

## Troubleshooting

### Common Issues:

| Issue | Solution |
|-------|----------|
| WebDriver not initialized | Check WebDriverManager setup, ensure Chrome installed |
| Element not found | Verify locators in page objects, check for dynamic elements |
| Alert not handled | Ensure alert presence before accepting, add try-catch |
| Excel data not read | Verify Excel file path and format (.xlsx) |
| Configuration not loaded | Check config.properties file exists in resources folder |
| Screenshot not captured | Ensure screenshots/ folder has write permissions |
| Timeout exceptions | Increase timeout value in config.properties |

---

## Notes and Considerations

1. All tests use explicit waits for better reliability
2. Email generation includes timestamp to ensure uniqueness
3. Alert handling includes try-catch for variation in application behavior
4. Configuration values can be changed without code modification
5. Screenshots only captured on test failure
6. Console output provides detailed execution flow for debugging
7. Framework supports dynamic customer and account creation
8. Suitable for testing Guru99 Bank demo application

---

## Extending the Framework

### Add New Test Class:
1. Create class extending BaseTest in tests/ package
2. Use page objects for interactions
3. Add TestNG annotations (@Test, @Listeners)
4. Add to testng.xml suite

### Add New Page Object:
1. Create class extending BasePage
2. Define page locators using By
3. Implement page methods using type(), click(), getText()
4. Use in test classes

### Add New Test Data:
1. Update config.properties or Login.xlsx
2. Read using ConfigReader or ExcelUtil
3. Use in test parameters

---

## Contact & Support

For framework enhancements:
- Refer to QualityHR and AutoQA projects for additional patterns
- Selenium documentation: https://www.selenium.dev/
- TestNG documentation: https://testng.org/
- Apache POI: https://poi.apache.org/

Banking Application: https://www.demo.guru99.com/v4/

---

Last Updated: April 2026  
Framework Version: 0.0.1-SNAPSHOT  
Status: Active Development  
Banking Application: Guru99 Bank Demo
