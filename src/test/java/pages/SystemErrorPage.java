package pages;

import base.BaseTest;
import driver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import utils.Configuration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SystemErrorPage extends BaseTest {
    private final Logger log = LogManager.getLogger(HomePage.class);

    public SystemErrorPage() {
        var waitTime = Configuration.instance().getIntegerValueOfProp("wait.time");
        var ajax = new AjaxElementLocatorFactory(DriverManager.instance().driver(), waitTime);
        PageFactory.initElements(ajax, this);
        waitUntilPageLoad();
        log.info("System Error Page is loaded");
    }

    @FindBy(className = "notification--error")
    private WebElement notificationError;

    public void verifyErrMsgInvalidTrackNumber(String MessageKey) {
        var actualErrMsg = getText(notificationError);
        var expectedErrMsg = getErrorMessage(MessageKey);
        assertEquals( expectedErrMsg, actualErrMsg,"Actual error message is wrong");
    }
}
