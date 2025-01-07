package tests;

import dto.TestCase;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestCaseTest extends BaseTest {

    SoftAssert softAssert = new SoftAssert();

    TestCase testCase = TestCase.builder()
            .sectionOption("Test Cases")
            .templateOption("Test Case (Text)")
            .typeOption("Functional")
            .priorityOption("High")
            .preconditions("Test")
            .build();

    @Test(testName = "Add a new test case", description = "Check that a new test case can be created from sidebar")
    @Description("Check that test case is created")
    public void createTestCaseFromActionsBar() {
        loginPage.open()
                .login(email, password)
                .openProject("Test")
                .clickAddTestCaseInSidebar()
                .fillForm(testCase, "Test Case 82");
        String message = testCasePage.getSuccessMessage();
        softAssert.assertEquals(message,
                "Successfully added the new test case. Add another",
                "Success message is not valid");
        softAssert.assertAll();
    }
}
