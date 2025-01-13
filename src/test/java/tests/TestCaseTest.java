package tests;

import dto.TestCase;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import static org.testng.Assert.assertFalse;

public class TestCaseTest extends BaseTest {

    TestCase testCase = TestCase.builder()
            .sectionOption("Test Cases")
            .templateOption("Test Case (Text)")
            .typeOption("Functional")
            .priorityOption("High")
            .preconditions("Test")
            .build();

    String projectName = "Test";
    String testCaseTitle = "Test Case 3";

    @Test(testName = "Add a new test case", description = "Check that a new test case can be created from sidebar")
    @Description("Check that test case is created")
    public void checkCreateTestCaseFromActionsBar() {
        loginPage.open()
                .login(email, password)
                .openProject(projectName)
                .clickAddTestCaseInSidebar()
                .fillForm(testCase, testCaseTitle);
        String message = testCasePage.getSuccessMessage();
        String testCaseTitle = testCasePage.getTestCaseTitle();
        softAssert.assertEquals(message,
                "Successfully added the new test case. Add another",
                "Success message is not valid");
        softAssert.assertEquals(testCaseTitle,
                testCaseTitle,
                "The title of test case doesn't match");
        softAssert.assertAll();
    }

    @Test(testName = "Delete a test case", description = "Check that the test case can be deleted")
    @Description("Check that test case is deleted")
    public void checkDeleteTestCaseFromList() {
        loginPage.open()
                .login(email, password)
                .openProject(projectName)
                .switchTab("Test Cases");
        testCasesListPage.clickOnDeleteButton(testCaseTitle)
                .isOpened()
                .clickOnButton("Mark as Deleted")
                .isOpened();
        boolean result = testCasesListPage.isTestCaseExisting(testCaseTitle);
        assertFalse(result, "Test case is NOT deleted");
    }

    @Test(testName = "Display deleted test case", description = "Check that deleted test case is displayed")
    @Description("Check that deleted test case is displayed in the list after making Display Deleted Test Cases on")
    public void checkDisplayDeletedTestCase() {
        loginPage.open()
                .login(email, password)
                .openProject("Test")
                .switchTab("Test Cases");
        testCasesListPage.clickOnDeleteButton("Test Case D")
                .isOpened()
                .clickOnButton("Mark as Deleted")
                .isOpened()
                .clickOnToolbarButton("Display Deleted Test Cases");
        //нужно проверить есть ли тест кейс в списке


    }

}
