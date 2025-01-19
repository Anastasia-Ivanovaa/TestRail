package tests.api;

import dto.api.testCase.CreateTestCaseRq;
import dto.api.testCase.CreateTestCaseRs;
import org.testng.annotations.Test;

import static adapters.TestCaseAPI.*;
import static org.testng.Assert.assertEquals;

public class TestCaseTest {

    @Test
    public void checkCreateTestCase()  {
        CreateTestCaseRq rq = CreateTestCaseRq.builder()
                .title("This is a test case 28")
                .typeId(1)
                .priorityId(3)
                .estimate("3m")
                .refs("RF-1, RF-2")
                .customPreconds("rrr")
                .customSteps("step 1")
                .customExpected("Expected Result 1")
                .build();

        CreateTestCaseRs rs = createTestCase(rq);
        assertEquals(rs.getTitle(), "This is a test case 28", "The test case title is NOT matched");
        System.out.println(rs.getId());
//        deleteTestCase(rs.getId());
    }
}
