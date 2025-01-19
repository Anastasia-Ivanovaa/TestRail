package adapters;

import dto.api.testCase.CreateTestCaseRq;
import dto.api.testCase.CreateTestCaseRs;
import dto.api.testCase.GetTestCaseRs;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TestCaseAPI extends BaseAPI {

    public static String section_id = "186";

    public static CreateTestCaseRs createTestCase(CreateTestCaseRq createTestCaseRq) {
        String url = base_url_api + "add_case/" + section_id;
        String response = testpost(gson.toJson(createTestCaseRq), url);
        return gson.fromJson(response, CreateTestCaseRs.class);
    }

    public static Response deleteTestCase(int caseId) {
        return spec
                .when()
                .post("https://ivaonova.testrail.io/index.php?/api/v2/delete_case/" + caseId)
                .then()
                .log().all().statusCode(200)
                .extract().response();
    }

    public static GetTestCaseRs getTestCase(int caseId) {
        System.out.println(base_url_api);
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
}

