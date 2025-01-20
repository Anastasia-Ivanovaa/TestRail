package tests.api;

import dto.api.project.CreateProjectRq;
import dto.api.project.CreateProjectRs;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static adapters.ProjectAPI.createProject;
import static adapters.ProjectAPI.deleteProject;
import static org.testng.Assert.assertEquals;

public class ProjectTest {

    CreateProjectRq rq = CreateProjectRq.builder()
            .name("Project API")
            .announcement("Welcome to project")
            .showAnnouncement(true)
            .build();

    @Test(testName = "Add a new project", description = "Check that a new project is created")
    @Description("Check that project is created")
    public void checkCreateProject() {
        CreateProjectRs rs = createProject(rq);
        assertEquals(rs.getName(),
                "Project API",
                "The title is NOT matched");
        deleteProject(rs.getId());
    }

    @Test(testName = "Delete a project", description = "Check that the project can be deleted using API")
    @Description("Check that project is deleted")
    public void checkDeleteTestCase() {
        CreateProjectRs rs = createProject(rq);
        Response response = deleteProject(rs.getId());
        assertEquals(response.getStatusCode(),
                200,
                "Invalid status code");
    }
}
