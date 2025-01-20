package adapters;

import dto.api.project.CreateProjectRq;
import dto.api.project.CreateProjectRs;
import dto.api.project.GetProjectsRs;
import dto.api.testCase.GetTestCaseRs;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ProjectAPI extends BaseAPI {

    public static CreateProjectRs createProject(CreateProjectRq createProjectRq) {
        return given()
                     .spec(spec)
                     .body(gson.toJson(createProjectRq))
               .when()
                     .post(base_url_api + "add_project")
                     .then()
                     .log().all()
                     .statusCode(200)
                     .extract()
                     .as(CreateProjectRs.class);
    }

    public static Response deleteProject(int projectId) {
        return given()
                     .spec(spec)
               .when()
                      .post(base_url_api + "delete_project/" + projectId)
                      .then()
                      .log().all()
                      .statusCode(200)
                      .extract().response();
    }

    public static GetProjectsRs getProjects() {
        return given()
                       .spec(spec)
               .when()
                       .get(base_url_api + "get_projects")
                       .then()
                       .log().all()
                       .statusCode(200)
                       .extract()
                       .as(GetProjectsRs.class);
    }
}
