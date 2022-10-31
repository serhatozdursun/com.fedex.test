package steps;

import enums.Credentials;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginPageSteps extends LoginPage {

    @When("typing {credentials} in the user ID input")
    public void typingVALID_USER_IDInTheUserIDInput(Credentials credentials) {
        typeUserId(credentials);
    }

    @And("typing {credentials} in the password input")
    public void typingVALID_USER_IDInThePasswordInput(Credentials credentials) {
        typePassword(credentials);
    }

    @And("Click login button")
    public void clickLoginButton() {
        clickLoginBtn();
    }

    @Then("Verify that the error message is {string}")
    public void verifyThatErrorMessageIsString(String errormessage) {
        var expectedMsg = getErrorMessage(errormessage);
        var actualMsg = getErrorMessageOnPage();
        assertEquals(expectedMsg,actualMsg);
    }
}
