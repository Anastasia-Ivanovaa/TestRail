package tests.base;

import jdk.jfr.Description;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.*;
import utils.AllureUtils;
import utils.PropertyReader;

import java.util.concurrent.TimeUnit;

@Listeners(TestListener.class)
public class BaseTest {
    protected WebDriver driver;
    protected LoginPage loginPage;
    protected DashboardPage dashboardPage;
    protected AddProjectPage addProjectPage;
    protected ProjectsPage projectsPage;
    protected ConfirmationDeleteProjectModal confirmationDeleteProjectModal;
    protected ProjectViewPage projectViewPage;
    protected TestCasePage testCasePage;
    protected TestCasesListPage testCasesListPage;
    protected ConfirmationDeleteTestCaseModal confirmationDeleteTestCaseModal;

    protected String email = System.getProperty("email");
    protected String password = System.getProperty("password");
    protected SoftAssert softAssert = new SoftAssert();

    @Parameters({"browser"})
    @BeforeMethod(description = "Open browser")
    @Description("Open browser")
    public void setUp(@Optional("chrome") String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("start-maximized");
            options.addArguments("--disable-notifications");
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("edge")) {
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--headless");
            driver = new EdgeDriver(options);
        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless");
            driver = new FirefoxDriver(options);
        }

        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        addProjectPage = new AddProjectPage(driver);
        projectsPage = new ProjectsPage(driver);
        confirmationDeleteProjectModal = new ConfirmationDeleteProjectModal(driver);
        projectViewPage = new ProjectViewPage(driver);
        testCasePage = new TestCasePage(driver);
        testCasesListPage = new TestCasesListPage(driver);
        confirmationDeleteTestCaseModal = new ConfirmationDeleteTestCaseModal(driver);
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
