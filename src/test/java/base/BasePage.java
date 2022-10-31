package base;

import driver.DriverManager;
import io.cucumber.java.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.Configuration;

import java.time.Duration;

public class BasePage {
    private final static Logger log = LogManager.getLogger(BasePage.class);

    @BeforeAll
    public static void before() {
        DriverManager.instance().startService();
    }

    @Before
    public void before(Scenario scenario) {
        DriverManager.instance().lunch();
        log.info("{} is lunched", DriverManager.instance().getBrowser());
        DriverManager.instance().driver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        DriverManager.instance().driver().manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        var baseUrl = Configuration.instance().getStringValueOfProp("base.url");
        DriverManager.instance().driver().get(baseUrl);
        DriverManager.instance().driver().manage().deleteAllCookies();
        log.info("{} is loaded", baseUrl);
        DriverManager.instance().driver().manage().window().fullscreen();
    }

    @After
    public void after(Scenario scenario) {
        if (scenario.isFailed()) {
            var takeSS = (TakesScreenshot) DriverManager.instance().driver();
            var ss = takeSS.getScreenshotAs(OutputType.BYTES);
            scenario.attach(ss, "image/png", "Error Screenshot");
        }
        DriverManager.instance().quit();
    }

    @AfterAll
    public static void after() {
        DriverManager.instance().stopService();
        if (BaseTest.getWaitTime() > 0)
            log.warn("The test execution put to sleep for \"{}\" static seconds, please reduce the static sleep time", BaseTest.getWaitTime());
    }
}


