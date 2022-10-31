package steps;

import enums.Countries;
import io.cucumber.java.en.When;
import pages.LocalHomePage;

public class LocalHomePageSteps extends LocalHomePage {


    @When("Select {string} language for {countries}")
    public void selectLanguageOfCountry(String language, Countries country) {
        var languages = country.languages();
        if (languages.contains(language)) {
            var countryS = String.valueOf(country);
            countryS = countryS.substring(0, 1) + countryS.substring(1).toLowerCase();
            clickDesiredLanguage(countryS, language);
        }
    }
}
