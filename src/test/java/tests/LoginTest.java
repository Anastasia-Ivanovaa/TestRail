package tests;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    @Description("Check positive login")
    public void validLogin() {
        loginPage.open()
                .isPageOpened()
                .login(email, password);
        dashboardPage.isPageOpened();
    }

    @DataProvider(name = "LoginData")
    public Object[][] loginData() {
        return new Object[][]{
                {"", password, "Email/Login is required."},
                {email, "", "Password is required."},
                {email, "123456", "Sorry, there was a problem.\n" +
                        "Email/Login or Password is incorrect. Please try again."}
        };
    }

    @Test(dataProvider = "LoginData")
    @Description("Negative login check")
    public void invalidLogin(String email, String password, String expectedMessage) {
        loginPage.open()
                .isPageOpened()
                .login(email, password);
        String errorMessage = loginPage.getErrorMessage();
        Assert.assertEquals(errorMessage, expectedMessage, "The message is not correct");
    }
}
