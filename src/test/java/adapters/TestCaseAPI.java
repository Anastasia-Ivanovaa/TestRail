package adapters;

import dto.api.testCase.CreateTestCaseRq;
import dto.api.testCase.CreateTestCaseRs;
import dto.api.testCase.GetTestCaseRs;
import dto.api.testCase.UpdateTestCaseRq;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TestCaseAPI extends BaseAPI {

    public static String section_id = "186";

    public static CreateTestCaseRs createTestCase(CreateTestCaseRq createTestCaseRq) {
        return given()
                    .spec(spec)
                    .body(gson.toJson(createTestCaseRq))
               .when()
                     .post(base_url_api +"add_case/" + section_id)
                     .then()
                     .log().all()
                     .statusCode(200)
                     .extract()
                     .as(CreateTestCaseRs.class);
    }

    public static Response deleteTestCase(int caseId) {
        return given()
                    .spec(spec)
               .when()
                     .post(base_url_api + "delete_case/" + caseId)
                     .then()
                     .log().all()
                     .statusCode(200)
                     .extract().response();
    }

    public static GetTestCaseRs getTestCase(int caseId) {
        return given()
                     .spec(spec)
               .when()
                     .get(base_url_api + "get_case/" + caseId)
                     .then()
                     .log().all()
                     .statusCode(200)
                     .extract()
                     .as(GetTestCaseRs.class);
    }

    public static CreateTestCaseRs updateTestCase(UpdateTestCaseRq updateTestCaseRq, int caseId) {
        return given()
                     .spec(spec)
                     .body(gson.toJson(updateTestCaseRq))
               .when()
                     .post(base_url_api + "update_case/" + caseId)
                     .then()
                     .log().all()
                     .statusCode(200)
                     .extract().response()
                     .as(CreateTestCaseRs.class);
    }
}

