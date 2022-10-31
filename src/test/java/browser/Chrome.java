package browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.service.DriverService;

import java.io.IOException;

public class Chrome implements BrowserSelectable {
    private DriverService service;
    private final Logger log = LogManager.getLogger(Chrome.class);

    @Override
    public void startService() {
        WebDriverManager.chromedriver().setup();
        service = new ChromeDriverService
                .Builder()
                .usingAnyFreePort()
                .build();
        try {
            service.start();
        } catch (IOException e) {
            log.error("DriverService could not start");
        }
    }

    @Override
    public WebDriver webDriver() {
        return new RemoteWebDriver(service.getUrl(), capabilities());
    }

    @Override
    public MutableCapabilities capabilities() {
        var options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--disable-popup-blocking");
        return options;
    }

    @Override
    public void stopService() {
        service.stop();
    }
}
