package browser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.service.DriverService;
import org.openqa.selenium.safari.SafariDriverService;
import org.openqa.selenium.safari.SafariOptions;

import java.io.IOException;

public class Safari implements BrowserSelectable {

    private DriverService service;
    private final Logger log = LogManager.getLogger(Chrome.class);

    @Override
    public void startService() {
        service = new SafariDriverService
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
        var options = new SafariOptions();
        options.setCapability("local.browser", true);
        return options;
    }

    @Override
    public void stopService() {
        service.stop();
    }
}
