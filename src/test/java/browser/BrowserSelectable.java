package browser;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;

public interface BrowserSelectable {
    void startService();

    WebDriver webDriver();

    MutableCapabilities capabilities();

    void stopService();
}
