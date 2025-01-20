package tests.api;

import dto.api.testCase.CreateTestCaseRq;
import dto.api.testCase.CreateTestCaseRs;
import dto.api.testCase.GetTestCaseRs;
import dto.api.testCase.UpdateTestCaseRq;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static adapters.TestCaseAPI.*;
import static org.testng.Assert.assertEquals;

public class TestCaseTest {

    CreateTestCaseRq newTestCase = CreateTestCaseRq.builder()
            .title("This is a test case 29")
            .typeId(1)
            .priorityId(3)
            .estimate("3m")
            .refs("RF-1, RF-2")
            .customPreconds("rrr")
            .customSteps("step 1")
            .customExpected("Expected Result 1")
            .build();

    UpdateTestCaseRq editedTestCase = UpdateTestCaseRq.builder()
            .title("This is updated test case 30")
            .estimate("4m")
            .build();

    SoftAssert softAssert = new SoftAssert();

    @Test(testName = "Add test case", description = "Check that a new test case can be created using API")
    @Description("Check that test case is created")
    public void checkCreateTestCase() {
        CreateTestCaseRs rs = createTestCase(newTestCase);
        assertEquals(rs.getTitle(),
                "This is a test case 29",
                "The test case title is NOT matched");
        System.out.println(rs.getId());
        deleteTestCase(rs.getId());
    }

    @Test(testName = "Delete a test case", description = "Check that the test case can be deleted using API")
    @Description("Check that test case is deleted")
    public void checkDeleteTestCase() {
        CreateTestCaseRs rs = createTestCase(newTestCase);
        Response response = deleteTestCase(rs.getId());
        assertEquals(response.getStatusCode(),
                200,
                "Invalid status code");
    }

    @Test(testName = "Edit test case", description = "Check that test case can be edited using API")
    @Description("Check that test case is edited")
    public void checkUpdateTestCase() {
        CreateTestCaseRs testCase = createTestCase(newTestCase);
        CreateTestCaseRs updatedTestCase = updateTestCase(editedTestCase, testCase.getId());
        softAssert.assertEquals(updatedTestCase.getTitle(),
                "This is updated test case 30",
                "The title has NOT changed");
        System.out.println(updatedTestCase.getPriorityId());
        softAssert.assertEquals(updatedTestCase.getEstimate(),
                "4m",
                "The estimate has NOT updated");
        softAssert.assertAll();
        deleteTestCase(updatedTestCase.getId());
    }

    @Test(testName = "Get the existing test case", description = "Check that test case dat can be taken from API")
    @Description("Check that test case data can be taken form API")
    public void checkGetTestCase() {
        CreateTestCaseRs testCase = createTestCase(newTestCase);
        GetTestCaseRs createdTestCase = getTestCase(testCase.getId());
        assertEquals(createdTestCase.getTitle(),
                "This is a test case 29",
                "The title of test case is invalid");
        deleteTestCase(createdTestCase.getId());
    }
}
