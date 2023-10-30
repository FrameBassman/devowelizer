package tech.romashov.features;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import tech.romashov.steps.DevowelizerSteps;

public class SmokeTests extends BaseTest {
    @Autowired
    private DevowelizerSteps devowelizerSteps;

    @Test
    public void whenGetWithVowels_serviceCanReturnOutputWithoutVowels() throws Throwable {
        devowelizerSteps.serviceCanReturnOutputWithoutVowels();
    }
}
