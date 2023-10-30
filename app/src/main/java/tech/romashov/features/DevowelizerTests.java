package tech.romashov.features;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import tech.romashov.steps.DevowelizerRequests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DevowelizerTests extends BaseTest {
    @Autowired
    private DevowelizerRequests devowelizerRequests;

    @Test
    public void itWorks() throws Exception {
        logger.info("Hello World!");
        assertThat(devowelizerRequests.get("input"), equalTo("npt"));
    }
}
