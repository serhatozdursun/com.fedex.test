package pages;

import base.BaseTest;
import driver.DriverManager;
import enums.Credentials;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import utils.AES;
import utils.Configuration;

public class LoginPage extends BaseTest {

    private final Logger log = LogManager.getLogger(LoginPage.class);

    public LoginPage() {
        var waitTime = Configuration.instance().getIntegerValueOfProp("wait.time");
        var ajax = new AjaxElementLocatorFactory(DriverManager.instance().driver(), waitTime);
        PageFactory.initElements(ajax, this);
        waitUntilPageLoad();
        log.info("Login Page is loaded");
    }

    @FindBy(id = "userId")
    private WebElement userId;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-btn")
    private WebElement loginBtn;

    @FindBy(className = "fdx-c-form-message--error")
    private WebElement invalidCredentials;

    protected void typeUserId(Credentials credentials) {
        var userName = credentials.value();
        typeText(userId, AES.decrypt(userName)== null ? userName : AES.decrypt(userName));
    }

    protected void typePassword(Credentials credentials) {
        var password = credentials.value();
        typeText(passwordInput, AES.decrypt(password) == null ? password : AES.decrypt(password));
    }

    protected void clickLoginBtn() {
        click(loginBtn);
    }

    protected String getErrorMessageOnPage() {
        return getText(invalidCredentials);
    }
}
