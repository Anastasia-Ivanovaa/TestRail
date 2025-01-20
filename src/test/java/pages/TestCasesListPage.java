package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.ArrayList;

@Log4j2
public class TestCasesListPage extends BasePage {

    private final String TEST_CASE_ROW = "//td//span[text() = '%s']//ancestor::tr[contains(@class, 'caseRow')]";
    private final String TEST_CASE_TITLE = "//tr[contains(@class, 'caseRow')]//td//span[text() = '%s']";
    private final String TOOLBAR_BUTTON = "//div[@id= 'contentToolbar']//a[contains(@class,'toolbar-button')]/span[text()='%s']";
    private final By TITLE_COLUMN = By.xpath("//a[@title='Title']/span[text()='Title']");
    private final By EXISTING_TEST_CASE_TITLE =
            By.xpath("//tr[not (contains(@class,'deleted-case'))]//span[@data-testid='sectionCaseTitle']");
    private final By DELETED_TEST_CASE =
            By.xpath("//tr[contains(@class,'deleted-case')]//span[@data-testid='sectionCaseTitle']");
    private final By ADD_CASE_BUTTON = By.xpath("//a[@data-testid='suiteAddCaseLink']");
    private final String DELETE_BUTTON = "/td/a[@class='deleteLink']";
    private final String DROPDOWN_OPTION = "//div[@id='content-inner']//a[contains(text(),'%s')][contains(@class,'link-tooltip')]";
    private final By SUCCESS_EDIT_MESSAGE = By.xpath("//div[@data-testid='messageSuccessDivBox']");
    private final String COLUMN_NAME = "//tr[contains(@class,'sectionRow')]//span[text()='%s']";

    public TestCasesListPage(WebDriver driver) {
        super(driver);
    }

    @Step("Check that Test Case tab is opened")
    public TestCasesListPage isOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE_COLUMN));
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("Test cases page isn't opened ");
        }
        return this;
    }

    @Step("Click on DELETE button next to {testCaseTitle}")
    public ConfirmationDeleteTestCaseModal clickOnDeleteButton(String testCaseTitle) {
        log.info("Click on DELETE button next to '{}' test case", testCaseTitle);
        By testCase = By.xpath(String.format(TEST_CASE_ROW, testCaseTitle));
        Actions actions = new Actions(driver);
        wait.until(ExpectedConditions.elementToBeClickable(ADD_CASE_BUTTON));

        WebElement testCaseRow = driver.findElement(testCase);
        actions.moveToElement(testCaseRow).perform();
        WebElement deleteIcon = driver.findElement(By.xpath(String.format(TEST_CASE_ROW + DELETE_BUTTON, testCaseTitle)));
        actions.moveToElement(deleteIcon).click().build().perform();
        return new ConfirmationDeleteTestCaseModal(driver);
    }

    @Step("Check whether test case with title '{testCaseTitle}' was deleted")
    public boolean isTestCaseExisting(String testCaseTitle) {
        log.info("Checking whether test case '{}' shown in the list", testCaseTitle);
        By testCase = By.xpath(String.format(TEST_CASE_TITLE, testCaseTitle));
        WebElement deletedTestCase = driver.findElement(testCase);
        wait.until(ExpectedConditions.invisibilityOf(deletedTestCase));

        ArrayList<WebElement> testCasesList = new ArrayList<>(driver.findElements(EXISTING_TEST_CASE_TITLE));

        boolean result = false;
        for (WebElement testCaseName : testCasesList) {
            if (testCaseName.getText().equals(testCaseTitle)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Step("Check whether test case with title '{testCaseTitle}' is displayed in the list")
    public boolean isTestCaseDeleted(String testCaseTitle) {
        log.info("Checking whether test case '{}' shown in the list of deleted test cases", testCaseTitle);
        ArrayList<WebElement> testCasesList = new ArrayList<>(driver.findElements(DELETED_TEST_CASE));
        boolean result = false;
        for (WebElement testCaseName : testCasesList) {
            if (testCaseName.getText().equals(testCaseTitle)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Step("Click on {buttonName} button")
    public TestCasesListPage clickOnToolbarButton(String buttonName) {
        log.info("Click on '{}' button", buttonName);
        By button = By.xpath(String.format(TOOLBAR_BUTTON, buttonName));
        driver.findElement(button).click();
        return this;
    }

    @Step("Set tick into checkbox next to {testCaseName} test case")
    public TestCasesListPage putTickIntoCheckbox(String testCaseName) {
        log.info("Set tick into checkbox next to '{}' test case", testCaseName);
        By testCase = By.xpath(String.format(TEST_CASE_ROW + "//input", testCaseName));
        wait.until(ExpectedConditions.visibilityOfElementLocated(testCase));
        driver.findElement(testCase).click();
        return this;
    }

    @Step("Select option {optionName} in the dropdown")
    public void selectOption(String optionName) {
        log.info("Click on '{}' option", optionName);
        By option = By.xpath(String.format(DROPDOWN_OPTION, optionName));
        wait.until(ExpectedConditions.visibilityOfElementLocated(option));
        driver.findElement(option).click();
    }

    @Step("Get success message after editing of Test case")
    public String getSuccessMessage() {
        log.info("Get success message after editing of Test case");
        return driver.findElement(SUCCESS_EDIT_MESSAGE).getText();
    }

    @Step("Check whether column '{columnName}' is displayed in the table header")
    public boolean isColumnAdded(String columnName) {
        log.info("Checking whether column '{}' is displayed in the table header", columnName);
        By columnTitle = By.xpath(String.format(COLUMN_NAME, columnName));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(columnTitle));
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("Column is NOT appeared");
        }
        ArrayList<WebElement> testCasesList = new ArrayList<>(driver.findElements(columnTitle));
        boolean result = false;
        for (WebElement testCaseName : testCasesList) {
            if (testCaseName.getText().equals(columnName)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Step("'Click on '{testcaseName}' test case ")
    public TestCasePage openTestCase(String testcaseName) {
        retryClick(String.format(TEST_CASE_TITLE, testcaseName));
        return new TestCasePage(driver);
    }
}
