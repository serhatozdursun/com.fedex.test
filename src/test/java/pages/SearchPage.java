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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchPage extends BaseTest {
    private final Logger log = LogManager.getLogger(HomePage.class);

    public SearchPage() {
        var waitTime = Configuration.instance().getIntegerValueOfProp("wait.time");
        var ajax = new AjaxElementLocatorFactory(DriverManager.instance().driver(), waitTime);
        PageFactory.initElements(ajax, this);
        waitUntilPageLoad();
        log.info("Search Page is loaded");
    }

    @FindBy(css = ".coveo-result-list-container>div")
    private List<WebElement> resultList;

    @FindBy(css = ".coveo-pager-list li")
    private List<WebElement> coverPagerList;

    @FindBy(className = "coveo-pager-next-icon")
    private WebElement rightArrow;

    @FindBy(className = "coveo-pager-previous-icon")
    private WebElement leftArrow;

    @FindBy(className = "coveo-active")
    private WebElement activePage;

    protected void assertSearchPageIsDisplay() {
        var actualTitle = getPageTitle();
        var expectedTitle = getTitlesAndMessages("search_page_title");
        assertTrue(actualTitle.contains(expectedTitle),
                String.format("expected title should contains %s but it was %s", actualTitle, expectedTitle));
    }

    protected boolean isMoreThenTenResult() {
        return resultList.size() >= 10;
    }

    protected boolean isPaginationPresent(){
        return isPresent(coverPagerList);
    }

    protected void clickRightArrow(){
        click(rightArrow);
    }

    protected void clickLeftArrow(){
        click(leftArrow);
    }

    protected String getActivePage(){
      return   getText(activePage);
    }

}
