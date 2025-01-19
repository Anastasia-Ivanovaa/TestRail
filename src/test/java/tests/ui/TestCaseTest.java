package tests.ui;

import dto.api.testCase.CreateTestCaseRq;
import dto.api.testCase.CreateTestCaseRs;
import dto.ui.EditTestCase;
import dto.ui.TestCase;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import pages.DeletePermanentlyModal;
import pages.TestCasesListPage;
import tests.base.BaseTest;

import static adapters.TestCaseAPI.createTestCase;
import static adapters.TestCaseAPI.deleteTestCase;
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

    CreateTestCaseRq rq = CreateTestCaseRq.builder()
            .title("This is a test case 35")
            .typeId(1)
            .priorityId(3)
            .estimate("3m")
            .refs("RF-1, RF-2")
            .customPreconds("rrr")
            .customSteps("step 1")
            .customExpected("Expected Result 1")
            .build();

    String projectName = "Test";
    String testCaseTitle = "Test Case 1";

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

        deleteTestCase(testCasePage.getTestCaseId());
    }

    @Test(testName = "Delete a test case", description = "Check that the test case can be deleted")
    @Description("Check that test case is deleted")
    public void checkDeleteTestCaseFromList() {
        createTestCase(rq);
        loginPage.open()
                .login(email, password)
                .openProject(projectName)
                .switchTab("Test Cases", TestCasesListPage.class)
                .clickOnDeleteButton(rq.getTitle())
                .isOpened()
                .clickButton("Delete Permanently", DeletePermanentlyModal.class)
                .isOpened()
                .clickDelete()
                .isOpened();
        boolean result = testCasesListPage.isTestCaseExisting(rq.getTitle());
        assertFalse(result, "Test case is NOT deleted");
    }

    @Test(testName = "Display deleted test case", description = "Check that deleted test case is displayed")
    @Description("Check that deleted test case is displayed in the list after making Display Deleted Test Cases on")
    public void checkDisplayDeletedTestCase() {
        createTestCase(rq);
        loginPage.open()
                .login(email, password)
                .openProject(projectName)
                .switchTab("Test Cases", TestCasesListPage.class)
                .clickOnDeleteButton(rq.getTitle())
                .isOpened()
                .clickButton("Mark as Deleted", TestCasesListPage.class)
                .isOpened()
                .clickOnToolbarButton("Display Deleted Test Cases")
                .isOpened();
        boolean result = testCasesListPage.isTestCaseDeleted(rq.getTitle());
        assertTrue(result, "Test case is NOT marked as deleted");
    }

    @Test(testName = "Edit test case", description = "Check that test case can be edited")
    @Description("Check that test case is edited")
    public void checkEditTestCase() {
        createTestCase(rq);
        loginPage.open()
                .login(email, password)
                .openProject(projectName)
                .switchTab("Test Cases", TestCasesListPage.class)
                .putTickIntoCheckbox(rq.getTitle())
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
        deleteTestCase(testCasesListPage.getTestCaseId(rq.getTitle()));
    }

    @Test(testName = "Delete Test Case from Actions bar", description = "Check that Test Case can be deleted using DELETE button in Actions bar")
    @Description("Check that Test Case is deleted using DELETE button in Actions bar")
    public void checkDeleteTestCaseFromActions() {
        createTestCase(rq);
        loginPage.open()
                .login(email, password)
                .openProject(projectName)
                .switchTab("Test Cases", TestCasesListPage.class)
                .isOpened()
                .putTickIntoCheckbox(rq.getTitle())
                .clickOnToolbarButton("Delete");
        confirmationDeleteTestCaseModal.isOpened()
                .clickButton("Delete Permanently", DeletePermanentlyModal.class)
                .isOpened()
                .clickDelete()
                .isOpened();
        boolean result = testCasesListPage.isTestCaseExisting(rq.getTitle());
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
        createTestCase(rq);
        loginPage.open()
                .login(email, password)
                .openProject(projectName)
                .switchTab("Test Cases", TestCasesListPage.class)
                .isOpened()
                .putTickIntoCheckbox(rq.getTitle())
                .clickOnToolbarButton("Assign To")
                .selectOption("Assign selected");
        assignToModal.isOpened()
                .setOptionInDropdown("Anastasia Ivanova")
                .isOpened()
                .openTestCase(rq.getTitle())
                .isOpened();
        String result = testCasePage.getTestCaseValue("Assigned To");
        assertEquals(result, "Me", "Incorrect value in the field");
        deleteTestCase(testCasePage.getTestCaseId());
    }
}
