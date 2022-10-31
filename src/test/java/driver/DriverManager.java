package driver;


import browser.*;
import enums.Browsers;
import org.openqa.selenium.WebDriver;
import utils.Configuration;

import java.util.Locale;

public class DriverManager {
    private static DriverManager instance;
    private BrowserSelectable browserSelectable;
    private WebDriver driver;

    private Browsers browser;

    private DriverManager() {

    }

    public static DriverManager instance() {
        if (instance == null)
            instance = new DriverManager();
        return instance;
    }

    public void startService() {
        var browserType = System.getProperty("browser");
        browserType = browserType == null ? Configuration.instance().getStringValueOfProp("browser") : browserType;
        setBrowser(Browsers.valueOf(browserType.toUpperCase(Locale.ENGLISH)));
        switch (browser) {
            case CHROME -> {
                browserSelectable = new Chrome();
                browserSelectable.startService();
            }
            case FIREFOX -> {
                browserSelectable = new Firefox();
                browserSelectable.startService();
            }
            case SAFARI -> {
                browserSelectable = new Safari();
                browserSelectable.startService();
            }
            default -> throw new IllegalArgumentException(String.format("Undefined browser: %s", browser));
        }
    }

    public void lunch() {
        driver = browserSelectable.webDriver();
        driver.manage().window().maximize();
    }

    public WebDriver driver() {
        if (driver == null)
            lunch();
        return driver;
    }

    public void quit() {
        if (driver != null)
            driver().quit();
    }

    public void stopService() {
        browserSelectable.stopService();
    }

    public Browsers getBrowser() {
        return browser;
    }

    private void setBrowser(Browsers browser) {
        this.browser = browser;
    }
}

