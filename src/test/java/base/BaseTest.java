package base;

import driver.DriverManager;
import helper.FileHelper;
import helper.JsonHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Configuration;

import java.time.Duration;
import java.util.List;

public class BaseTest {
    private final Logger log = LogManager.getLogger(BaseTest.class);
    private static int waitTime;

    private WebDriverWait getWait() {
        var waitTime = Configuration.instance().getIntegerValueOfProp("wait.time");
        return new WebDriverWait(DriverManager.instance().driver(), Duration.ofSeconds(waitTime));
    }

    private WebElement presenceWait(By by) {
        var waitTime = Configuration.instance().getIntegerValueOfProp("wait.time");
        return getWait().until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement clickableWait(WebElement element) {
        return getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    private WebElement clickableWait(By by) {
        return getWait().until(ExpectedConditions.elementToBeClickable(by));
    }

    private WebElement visibleWait(WebElement element) {
        return getWait().until(ExpectedConditions.visibilityOf(element));
    }

    protected void sendKeyToActiveElement(Keys keys) {
        var activeElm = DriverManager
                .instance()
                .driver()
                .switchTo()
                .activeElement();
        sendKeys(activeElm, keys);

        log.info("pressed tab button on the active element");
    }

    protected void sendKeys(WebElement activeElm, Keys keys) {
        clickableWait(activeElm).sendKeys(keys);
    }

    protected void clickActiveElement() {
        var activeElm = DriverManager
                .instance()
                .driver()
                .switchTo()
                .activeElement();
        click(activeElm);
        log.info("clicked on the active element");
    }

    private Select getSelect(WebElement element) {
        return new Select(element);
    }

    protected void selectByValue(WebElement element, String value) {
        getSelect(element).selectByValue(value);
    }

    private Actions getActions() {
        return new Actions(DriverManager.instance().driver());
    }

    protected void click(WebElement element) {
        log.info("click on \"{}\'", getElementName(element));
        clickableWait(element).click();
    }

    protected void click(By by) {
        log.info("click on \"{}\'", by);
        clickableWait(by).click();
    }

    protected void typeText(WebElement element, String text) {
        clickableWait(element).clear();
        clickableWait(element).sendKeys(text);
    }

    protected JavascriptExecutor javaScriptExecute() {
        return (JavascriptExecutor) DriverManager.instance().driver();
    }

    protected void waitUntilPageLoad() {
        getWait().until((ExpectedCondition<Boolean>) driver ->
                String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")).equals("complete"));
    }

    protected void jsClick(WebElement element) {
        log.info("js click on \"{}\"", getElementName(element));
        javaScriptExecute().executeScript("arguments[0].click()", element);
    }

    protected void jsClick(By by) {
        var element = presenceWait(by);
        log.info("js click on \"{}\"", getElementName(element));
        javaScriptExecute().executeScript("arguments[0].click()", element);
    }

    protected boolean isPresent(List<WebElement> element) {
        var result = element.size() > 0;
        log.info("\"{}\" is present :{}", result ? getElementName(element.get(0)) : element, result);
        return result;
    }

    protected String getAttribute(WebElement element, String attName) {
        return element.getAttribute(attName);
    }

    protected String getText(WebElement element) {
        return visibleWait(element).getText();
    }

    private Object getElementName(WebElement element) {
        return element.getText() != null && !element.getText().isEmpty() ? getText(element) : element;
    }

    protected String getErrorMessage(String message) {
        var fileHelper = new FileHelper();
        var json = fileHelper.readFileAsString(getClass().getClassLoader().getResourceAsStream("messages/error_messages.json"));
        var jsonHelper = new JsonHelper();
        return jsonHelper.getJsonValueAsString(json, message);
    }

    protected String getTitlesAndMessages(String message) {
        var fileHelper = new FileHelper();
        var json = fileHelper.readFileAsString(getClass().getClassLoader().getResourceAsStream("messages/messages_and_titles.json"));
        var jsonHelper = new JsonHelper();
        return jsonHelper.getJsonValueAsString(json, message);
    }

    protected String getPageTitle() {
        return DriverManager
                .instance()
                .driver()
                .getTitle();
    }

    protected void staticWait(int second) {
        try {
            Thread.sleep(second * 100);
            setWaitTime(second);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime += waitTime;
    }

    public void clickXZ(WebElement elm1, WebElement elm2) {
        try {
            click(elm1);
        } catch (NoSuchElementException | TimeoutException e) {
            click(elm2);
        }

    }
}

