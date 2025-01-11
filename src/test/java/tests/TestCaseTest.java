package tests;

import dto.TestCase;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertTrue;

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
                .fillForm(testCase, "Test Case 3");
        String message = testCasePage.getSuccessMessage();
        String testCaseTitle = testCasePage.getTestCaseTitle();
        softAssert.assertEquals(message,
                "Successfully added the new test case. Add another",
                "Success message is not valid");
        softAssert.assertEquals(testCaseTitle,
                "Test Case 3",
                "The title of test case doesn't match");
        softAssert.assertAll();
    }

    @Test(testName = "Delete a test case", description = "Check that the test case can be deleted")
    @Description("Check that test case is deleted")
    public void deleteTestCaseFromList() {
        loginPage.open()
                .login(email, password)
                .openProject("Test")
                .switchTab("Test Cases");
        testCasesListPage.clickOnDeleteButton("Test Case 3")
                .isOpened()
                .clickOnButton("Mark as Deleted")
                .isOpened();
        boolean result = testCasesListPage.isTestCaseExisting("Test Case 3");
        assertTrue(result, "Test case is NOT deleted");
    }
}
