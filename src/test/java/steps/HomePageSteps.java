package steps;

import enums.Countries;
import enums.TrackingNumbers;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;

import static enums.Credentials.USER_TEXT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomePageSteps extends HomePage {

    @Given("The user typed {trackingNumbers} to the tracking number input")
    public void theUserTypedToTheTrackingNumberInput(TrackingNumbers trackingNumbers) {
        closeLanguageModal();
        typeTrackingNumber(trackingNumbers.value());
    }

    @When("Press Tab button then click the active button")
    public void pressTabButtonThenClickTheActiveButton() {
        pressTabAndClickActiveElm();
    }

    @When("Click TRACK button")
    public void clickTrackButton() {
        clickTrackBtn();
    }

    @Then("The {string} message displays on the home page")
    public void theMessageDisplayOnTheErrorPage(String messageKey) {
        verifyNoTrackNumberAlert(messageKey);
    }

    @Given("The {string} as a keyword to type in search-box on the home page")
    public void theAsAKeywordToTypeInSearchBoxOnTheHomePage(String keyword) {
        closeLanguageModal();
        typeKeywordToSrcBx(keyword);
    }

    @When("Click the search button magnifying glass icon")
    public void clickTheSearchButtonMagnifyingGlassIcon() {
        clickSearchIcon();
    }

    @Given("Choose Location Page")
    public void locationHomePage() {
        clickGlobalLink();
    }

    @Then("Current country should be {countries}")
    public void checkCurrentCountry(Countries country) {
        var expected = String.valueOf(country).substring(0, 1)
                + String.valueOf(country).substring(1).toLowerCase();
        var actual = getCurrentCountry();
        assertEquals(expected, actual, "Current country should be sam with chosen one");
    }

    @And("There should be just language of {countries} on language dropdown")
    public void checkLanguageDropdown(Countries country) {
        var expected = country.languages();
        var actual = getLanguageOfCCountry();
        assertTrue(expected.equals(actual), "language list should be same expected list: " + expected +
                " \nactual list:" + actual);
    }

    @Given("Go to login page")
    public void onLoginPage() {
        closeLanguageModal();
        clickSignInMenu();
        clickLoginMenu();
    }

    @Then("The user should be log in successfully")
    public void theUserShouldBeLogInSuccessfully() {
        var expectedUserText = USER_TEXT.value();
        var actualUserText = getSignInText();
        assertEquals(expectedUserText, actualUserText);
    }
}
