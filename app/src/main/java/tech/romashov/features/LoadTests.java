package tech.romashov.features;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import tech.romashov.steps.DevowelizerSteps;

public class LoadTests extends BaseTest {
    @Autowired
    private DevowelizerSteps devowelizerSteps;

    @Test
    public void whenDoRequests_serviceShouldNotReturnAnErrorDuringThePeriod() throws Throwable {
        devowelizerSteps.shouldNotReturnAnErrorDuringThePeriod();
    }
}
