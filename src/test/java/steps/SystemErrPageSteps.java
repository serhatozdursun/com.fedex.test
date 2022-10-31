package steps;

import io.cucumber.java.en.Then;
import pages.SystemErrorPage;

public class SystemErrPageSteps extends SystemErrorPage {

    @Then("The {string} message displays on the error page")
    public void theMessageDisplayOnTheErrorPage(String messageKey) {
        verifyErrMsgInvalidTrackNumber(messageKey);
    }
}
