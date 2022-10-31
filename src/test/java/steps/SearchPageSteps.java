package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.SearchPage;

import static org.junit.jupiter.api.Assertions.*;

public class SearchPageSteps extends SearchPage {
    @Then("The search page should be display")
    public void assertSearchPage() {
        assertSearchPageIsDisplay();
    }

    @And("Check if there is more than ten results and pagination")
    public void checkIfThereIsMoreThanTenResultsAndPagination() {
        if (isMoreThenTenResult())
            assertTrue(isPaginationPresent(), "there is more then ten result pagination should be display");
        else
            assertFalse(isPaginationPresent(), "there is no more then ten result pagination shouldn't be display");
    }

    @And("Should be able to navigate right and left between pages")
    public void shouldBeAbleToNavigateRightAndLeftBetweenPages() {
        var currentPage = getActivePage();
        clickRightArrow();
        staticWait(5);
        var nextPage = getActivePage();
        assertNotEquals(currentPage, nextPage, "when click the right arrow the active page should change");
        clickLeftArrow();
        staticWait(5);
        var currentPage2 = getActivePage();
        assertEquals(currentPage2, currentPage,"when return the previous page the active page should be same");
    }

}
