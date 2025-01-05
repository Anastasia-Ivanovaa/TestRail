package tests;

import jdk.jfr.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pages.AddProjectPage;
import pages.DashboardPage;
import pages.LoginPage;
import utils.AllureUtils;
import utils.PropertyReader;

@Listeners(TestListener.class)
public class BaseTest {
    protected WebDriver driver;
    protected LoginPage loginPage;
    protected DashboardPage dashboardPage;
    protected AddProjectPage addProjectPage;

    String email = System.getProperty("email", PropertyReader.getProperty("email"));
    String password = System.getProperty("password", PropertyReader.getProperty("password"));

    @BeforeMethod(description = "Open browser")
    @Description("Open browser")
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        addProjectPage = new AddProjectPage(driver);
    }

    @AfterMethod(alwaysRun = true, description = "Close browser")
    @Description("Close browser")
    public void tearDown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            AllureUtils.takeScreenshot(driver);
        }
        if (driver != null) {
            driver.quit();
        }
    }
}
