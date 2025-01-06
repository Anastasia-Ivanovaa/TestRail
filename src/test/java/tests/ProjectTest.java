package tests;

import dto.Project;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ProjectTest extends BaseTest {

    SoftAssert softAssert = new SoftAssert();

    Project project = Project.builder()
            .name("Test 40")
            .projectType("Use a single repository with baseline support")
            .build();

    @Test(testName = "Add a new project", description = "Check that a new project is created")
    @Description("Check that project is created")
    public void createProject() {

        loginPage.open()
                .isPageOpened()
                .login(email, password)
                .isPageOpened()
                .createProject()
                .isPageOpened()
                .fillForm(project)
                .clickAddProjectButton();
        String message = projectsPage.getSuccessMessage();
        softAssert.assertEquals(message, "Successfully added the new project.", "Success message is not valid");
        softAssert.assertTrue(projectsPage.isProjectExisting("Test 38"), "Project is NOT created");
        softAssert.assertAll();
    }

    @Test(testName = "Delete a project", description = "Check that the project can be deleted")
    @Description("Check that project is deleted")
    public void deleteProject() {

        loginPage.open()
                .isPageOpened()
                .login(email, password)
                .isPageOpened()
                .createProject()
                .isPageOpened()
                .fillForm(project)
                .clickAddProjectButton()
                .deleteProject("Test 40")
                .confirmAndDeleteProject();
        String message = projectsPage.getSuccessMessage();
        softAssert.assertEquals(message, "Successfully deleted the project.", "Success message is not valid");
        softAssert.assertFalse(projectsPage.isProjectExisting("Test 40"), "Project is NOT deleted");
        softAssert.assertAll();
    }
}
