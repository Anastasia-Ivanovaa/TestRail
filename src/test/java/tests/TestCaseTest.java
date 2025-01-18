package tests;

import dto.EditTestCase;
import dto.TestCase;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import pages.TestCasesListPage;
import tests.base.BaseTest;

import static org.testng.Assert.*;

public class TestCaseTest extends BaseTest {

    TestCase testCase = TestCase.builder()
            .sectionOption("Test Cases")
            .templateOption("Test Case (Text)")
            .typeOption("Functional")
            .priorityOption("High")
            .preconditions("Test")
            .build();

    EditTestCase editTestCase = EditTestCase.builder()
            .priority("High")
            .build();

    String projectName = "Test";
    String testCaseTitle = "103";

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
                .switchTab("Test Cases", TestCasesListPage.class)
                .clickOnDeleteButton(testCaseTitle)
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
                .openProject(projectName)
                .switchTab("Test Cases", TestCasesListPage.class)
                .clickOnDeleteButton(testCaseTitle)
                .isOpened()
                .clickOnButton("Mark as Deleted")
                .isOpened()
                .clickOnToolbarButton("Display Deleted Test Cases")
                .isOpened();
        boolean result = testCasesListPage.isTestCaseDeleted(testCaseTitle);
        assertTrue(result, "Test case is NOT marked as deleted");
    }

    @Test(testName = "Edit test case", description = "Check that test case can be edited")
    @Description("Check that test case is edited")
    public void checkEditTestCase() {
        loginPage.open()
                .login(email, password)
                .openProject(projectName)
                .switchTab("Test Cases", TestCasesListPage.class)
                .putTickIntoCheckbox(testCaseTitle)
                .clickOnToolbarButton("Edit")
                .selectOption("Edit selected");
        editTestCasesSelectedPage
                .isOpened()
                .fillEditForm(editTestCase)
                .isOpened()
                .confirmChanges();
        String successEditMessage = testCasesListPage.getSuccessMessage();
        assertEquals(successEditMessage,
                "Successfully updated the test cases.",
                "The test case changes have NOT saved");
    }

    @Test(testName = "Delete Test Case from Actions bar", description = "Check that Test Case can be deleted using DELETE button in Actions bar")
    @Description("Check that Test Case is deleted using DELETE button in Actions bar")
    public void checkDeleteTestCaseFromActions() {
        loginPage.open()
                .login(email, password)
                .openProject(projectName)
                .switchTab("Test Cases", TestCasesListPage.class)
                .isOpened()
                .putTickIntoCheckbox(testCaseTitle)
                .clickOnToolbarButton("Delete");
        confirmationDeleteTestCaseModal.isOpened()
                .clickOnButton("Mark as Deleted")
                .isOpened();
        boolean result = testCasesListPage.isTestCaseExisting(testCaseTitle);
        assertFalse(result, "Test case is NOT marked as deleted");
    }

    @Test(testName = "Add a new column to the test cases table", description = "Check that new column can be added to the test cases table")
    @Description("Check that a new column can be added to the test cases table")
    public void checkAddNewColumnToTable() {
        loginPage.open()
                .login(email, password)
                .openProject(projectName)
                .switchTab("Test Cases", TestCasesListPage.class)
                .clickOnToolbarButton("Columns");
        selectColumnsModal.isOpened()
                .clickAddColumnButton()
                .isOpened()
                .selectDropdownOption("Estimate")
                .clickAddColumnButton()
                .isOpened()
                .clickButton("Update Columns");
        testCasesListPage.isOpened();
        boolean result = testCasesListPage.isColumnAdded("Estimate");
        assertTrue(result, "The column is NOT added to the table");
    }

    @Test(testName = "Assign To Test Case", description = "Check that Test Case can be assigned to someone")
    @Description("Check that Test Case can be assigned to someone")
    public void checkAssignToValue() {
        loginPage.open()
                .login(email, password)
                .openProject(projectName)
                .switchTab("Test Cases", TestCasesListPage.class)
                .isOpened()
                .putTickIntoCheckbox(testCaseTitle)
                .clickOnToolbarButton("Assign To")
                .selectOption("Assign selected");
        assignToModal.isOpened()
                .setOptionInDropdown("Anastasia Ivanova")
                .isOpened()
                .openTestCase(testCaseTitle)
                .isOpened();
        String result = testCasePage.getTestCaseValue("Assigned To");
        assertEquals(result, "Me", "Incorrect value in the field");
    }
}
