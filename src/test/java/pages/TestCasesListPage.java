package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.ArrayList;

@Log4j2
public class TestCasesListPage extends BasePage {

    private final String TEST_CASE_ROW = "//td//span[text() = '%s']//ancestor::tr[contains(@class, 'caseRow')]";
    private final String TEST_CASE_TITLEt = "//tr[contains(@class, 'caseRow')]//td//span[text() = '%s']";
    private final By TEST_CASE_TITLE =
            By.xpath("//tr[not (contains(@class,'deleted-case'))]//span[@data-testid='sectionCaseTitle']");
    private final By ADD_CASE_BUTTON = By.xpath("//a[@data-testid='suiteAddCaseLink']");
    private final String DELETE_BUTTON = "/td/a[@class='deleteLink']";

    public TestCasesListPage(WebDriver driver) {
        super(driver);
    }

    @Step("Check that Test Case tab is opened")
    public TestCasesListPage isOpened() {
        try{
        wait.until(ExpectedConditions.visibilityOfElementLocated(TEST_CASE_TITLE));}
        catch(TimeoutException e){
            log.error(e.getMessage());
            Assert.fail("Test cases page isn't appeared ");
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
        By testCase = By.xpath(String.format(TEST_CASE_TITLEt, testCaseTitle));
        WebElement deletedTestCase = driver.findElement(testCase);
        return wait.until(ExpectedConditions.invisibilityOf(deletedTestCase));

//        ArrayList<WebElement> testCasesList = new ArrayList<>(driver.findElements(TEST_CASE_TITLE));
//
//        boolean result = false;
//        for (WebElement testCaseName : testCasesList) {
//            if (testCaseName.getText().equals(testCaseTitle)) {
//                result = true;
//                break;
//            }
//        }
//        return result;
//    }
    }
}
