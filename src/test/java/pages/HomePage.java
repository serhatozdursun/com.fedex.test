package pages;

import base.BaseTest;
import driver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import utils.Configuration;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomePage extends BaseTest {
    private final Logger log = LogManager.getLogger(HomePage.class);

    public HomePage() {
        var waitTime = Configuration.instance().getIntegerValueOfProp("wait.time");
        var ajax = new AjaxElementLocatorFactory(DriverManager.instance().driver(), waitTime);
        PageFactory.initElements(ajax, this);
        waitUntilPageLoad();
        log.info("Account page is loaded");
    }

    @FindBy(id = "trackingnumber")
    private WebElement trackNumberInput;

    @FindBy(className = "js-modal-close")
    private List<WebElement> languageModalCloseBtn;

    @FindBy(id = "btnSingleTrack")
    private WebElement singleTrackBtn;

    @FindBy(css = "[role=alert]")
    private WebElement alertMessage;

    @FindBy(className = "fxg-user-options__search-btn")
    private WebElement searchBtn;

    @FindBy(id = "fxg-search-icon")
    private WebElement searchIcon;

    @FindBy(id = "fxg-search-text")
    private WebElement searchBoxInput;

    @FindBy(className = "fxg-geo-locator__global-link")
    private WebElement globalLink;

    @FindBy(css = "[href='http://www.fedex.com/?location=home']")
    private WebElement locationHome;

    @FindBy(className = "icon_link")
    private WebElement currentCountry;

    @FindBy(css = "ul.dropdown-menu li a")
    private List<WebElement> languageOfCountry;

    @FindBy(id = "fxg-dropdown-signIn")
    private WebElement signInDropdown;

    @FindBy(css = "[title='Log In']")
    private WebElement loginMenu;

    @FindBy(className = "fxg-user-options__sign-in-text")
    private WebElement signInText;

    protected void typeTrackingNumber(String text) {
        typeText(trackNumberInput, text);
        log.info("\"{}\" tack number type on track number input", text);
    }

    protected void pressTabAndClickActiveElm() {
        sendKeyToActiveElement(Keys.TAB);
        clickActiveElement();
    }

    protected void closeLanguageModal() {
        if (isPresent(languageModalCloseBtn))
            click(languageModalCloseBtn.get(0));
    }

    protected void clickTrackBtn() {
        click(singleTrackBtn);
    }

    protected void verifyNoTrackNumberAlert(String messageKey) {
        var actualErrMsg = getText(alertMessage);
        var expectedErrMsg = getErrorMessage(messageKey);
        assertEquals(expectedErrMsg, actualErrMsg, "Actual error message is wrong");
    }

    protected void typeKeywordToSrcBx(String keyword) {
        click(searchBtn);
        typeText(searchBoxInput, keyword);
    }

    protected void clickSearchIcon() {
        click(searchIcon);
    }

    protected void clickGlobalLink() {
        closeLanguageModal();
        click(locationHome);
    }

    protected String getCurrentCountry() {
        closeLanguageModal();
        return getText(currentCountry).trim().strip();
    }

    protected List<String> getLanguageOfCCountry() {
        var languageList = languageOfCountry
                .stream()
                .map(i -> i.getAttribute("innerText"))
                .collect(Collectors.toList());
        return languageList;
    }

    protected void clickSignInMenu() {
        click(signInDropdown);
    }

    protected void clickLoginMenu() {
        click(loginMenu);
    }

    protected String getSignInText() {
        return getText(signInText);
    }
}
