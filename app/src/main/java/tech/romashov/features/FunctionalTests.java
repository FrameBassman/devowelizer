package tech.romashov.features;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import tech.romashov.steps.NonLatinSymbolsSteps;
import tech.romashov.steps.DevowelizerSteps;

public class FunctionalTests extends BaseTest {
    @Autowired
    private DevowelizerSteps devowelizerSteps;
    @Autowired
    private NonLatinSymbolsSteps nonLatinSymbolsSteps;

    @Test
    public void serviceShouldHaveGETHttpMethodOnly() throws Exception {
        devowelizerSteps.thereAreNoHttpMethodsExceptGET();
    }

    @Test
    public void cyrillicLetters() throws Throwable {
        nonLatinSymbolsSteps.serviceShouldNotChangeCyrillicLetters();
    }

    @Test
    public void whenRequestContainsSpecialSymbols_serviceShouldReturnResponseWithoutChanges() throws Throwable {
        nonLatinSymbolsSteps.serviceShouldNotReplaceSpecialSymbols();
    }

    @Test
    public void whenRequestContainsOnlyNumbers_serviceShouldReturnOnlyNumbers() throws Throwable {
        devowelizerSteps.serviceShouldReturnOnlyNumbers();
    }

    @Test
    public void whenRequestContainsEmptyString_serviceShouldReturnEmptyResponse() throws Throwable {
        devowelizerSteps.serviceShouldReturnEmptyRequestWhenThereAreNoParameters();
    }

    @Test
    public void whenUserSendsCapitalLetters_serviceShouldReturnWithoutVowels() throws Throwable {
        devowelizerSteps.serviceShouldReturnWitoutCapitalVowels();
    }

    @Test
    public void serviceShouldContainDefaultHeaders() throws Throwable {
        devowelizerSteps.serviceShouldContainDefaultHeaders();
    }
}
