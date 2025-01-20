package tests.ui;

import dto.api.project.GetProjectsRs;
import dto.api.project.Project;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import static adapters.ProjectAPI.deleteProject;
import static adapters.ProjectAPI.getProjects;

public class ProjectTest extends BaseTest {

    String projectName = "New project";

    @Test(testName = "Add a new project", description = "Check that a new project is created")
    @Description("Check that project is created")
    public void checkCreateProject() {
        loginPage.open()
                .login(email, password)
                .createProject()
                .fillForm(projectName)
                .clickAddProjectButton();
        String message = projectsPage.getSuccessMessage();
        softAssert.assertEquals(message,
                "Successfully added the new project.",
                "Success message is not valid");
        softAssert.assertTrue(projectsPage.isProjectExisting(
                        projectName),
                "Project is NOT created");
        softAssert.assertAll();

        GetProjectsRs rs = getProjects();
        Project createdProject = rs.getProjects().get(2);
        deleteProject(createdProject.getId());
    }

    @Test(testName = "Delete a project", description = "Check that the project can be deleted")
    @Description("Check that project is deleted")
    public void checkDeleteProject() {
        loginPage.open()
                .login(email, password)
                .createProject()
                .fillForm(projectName)
                .clickAddProjectButton()
                .deleteProject(projectName)
                .confirmAndDeleteProject();
        String message = projectsPage.getSuccessMessage();
        softAssert.assertEquals(message,
                "Successfully deleted the project.",
                "Success message is not valid");
        softAssert.assertFalse(projectsPage.isProjectExisting(
                        projectName),
                "Project is NOT deleted");
        softAssert.assertAll();
    }
}
