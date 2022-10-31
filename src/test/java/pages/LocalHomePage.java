package pages;

import base.BaseTest;
import driver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import utils.Configuration;

public class LocalHomePage extends BaseTest {
    private final Logger log = LogManager.getLogger(HomePage.class);

    public LocalHomePage() {
        var waitTime = Configuration.instance().getIntegerValueOfProp("wait.time");
        var ajax = new AjaxElementLocatorFactory(DriverManager.instance().driver(), waitTime);
        PageFactory.initElements(ajax, this);
        waitUntilPageLoad();
        log.info("Local Home Page is loaded");
    }

    protected void clickDesiredLanguage(String country, String language){
        var languageBy = By.xpath("//p[contains(text(),'"+country+"')]/a[contains(text(),'"+language+"')]");
        click(languageBy);
    }
}
