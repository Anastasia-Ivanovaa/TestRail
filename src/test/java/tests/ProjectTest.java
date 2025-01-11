package tests;

import io.qameta.allure.Description;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ProjectTest extends BaseTest {

    SoftAssert softAssert = new SoftAssert();

    @Test(testName = "Add a new project", description = "Check that a new project is created")
    @Description("Check that project is created")
    public void createProject() {
        loginPage.open()
                .login(email, password)
                .createProject()
                .fillForm("New project")
                .clickAddProjectButton();
        String message = projectsPage.getSuccessMessage();
        softAssert.assertEquals(message,
                "Successfully added the new project.",
                "Success message is not valid");
        softAssert.assertTrue(projectsPage.isProjectExisting(
                "New project"),
                "Project is NOT created");
        softAssert.assertAll();
    }

    @Test(testName = "Delete a project", description = "Check that the project can be deleted")
    @Description("Check that project is deleted")
    public void deleteProject() {
        loginPage.open()
                .login(email, password)
                .createProject()
                .fillForm("New project2")
                .clickAddProjectButton()
                .deleteProject("New project2")
                .confirmAndDeleteProject();
        String message = projectsPage.getSuccessMessage();
        softAssert.assertEquals(message,
                "Successfully deleted the project.",
                "Success message is not valid");
        softAssert.assertFalse(projectsPage.isProjectExisting(
                "New project2"),
                "Project is NOT deleted");
        softAssert.assertAll();
    }
}
