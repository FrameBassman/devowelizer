package tech.romashov;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ApiTest extends BaseTest {
    @Autowired
    private HttpClientSteps steps;

    @Test
    public void itWorks() throws Exception {
        logger.info("Hello World!");
        assertThat(steps.version(), equalTo("2"));
    }
}
