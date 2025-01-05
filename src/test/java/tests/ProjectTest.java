package tests;

import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class ProjectTest extends BaseTest {

    @Test
    @Description("Check that project is created")
    public void createProject() {
        loginPage.open()
                .isPageOpened()
                .login(email, password)
                .open()
                .isPageOpened()
                .createProject()
                .isPageOpened()
                .fillForm("Test2", "test2", "Use a single repository with baseline support");
    }
}
