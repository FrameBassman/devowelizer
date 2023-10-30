package tech.romashov.steps;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import tech.romashov.matchers.AssertWithTimeout;
import tech.romashov.requests.DevowelizerRequests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.stringContainsInOrder;

@Component
public class NonLatinSymbolsSteps {
    private Log logger = LogFactory.getLog(getClass());
    @Autowired
    private TestDataProvider data;
    @Autowired
    private DevowelizerRequests devowelizerRequests;

    public void serviceShouldNotChangeCyrillicLetters() throws Throwable {
        AssertWithTimeout.assertThat(
                () -> {
                    try {
                        ResponseEntity<String> response = devowelizerRequests.get(String.join("", data.getCyrillicAlphabet()));
                        logger.info("Status code should be 200");
                        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
                        logger.info("Body is: " + response.getBody());
                        return response.getBody();
                    } catch (RestClientException restClientException) {
                        logger.warn(restClientException);
                        return null;
                    }
                },
                stringContainsInOrder(data.getCyrillicAlphabet())
        );
        AssertWithTimeout.assertThat(
                () -> {
                    try {
                        ResponseEntity<String> response = devowelizerRequests.get(String.join("", data.getCyrillicVowels()));
                        logger.info("Status code should be 200");
                        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
                        logger.info("Body is: " + response.getBody());
                        return response.getBody();
                    } catch (RestClientException restClientException) {
                        logger.warn(restClientException);
                        return null;
                    }
                },
                stringContainsInOrder(data.getCyrillicVowels())
        );
        AssertWithTimeout.assertThat(
                () -> {
                    try {
                        ResponseEntity<String> response = devowelizerRequests.get(String.join("", data.getCyrillicConsonants()));
                        logger.info("Status code should be 200");
                        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
                        logger.info("Body is: " + response.getBody());
                        return response.getBody();
                    } catch (RestClientException restClientException) {
                        logger.warn(restClientException);
                        return null;
                    }
                },
                stringContainsInOrder(data.getCyrillicConsonants())
        );
    }

    public void serviceShouldNotReplaceSpecialSymbols() throws Throwable {
                AssertWithTimeout.assertThat(
                () -> {
                    try {
                        ResponseEntity<String> response = devowelizerRequests.get(String.join("", data.getSpecialSymbols()));
                        logger.info("Status code should be 200");
                        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
                        logger.info("Body is: " + response.getBody());
                        return response.getBody();
                    } catch (RestClientException restClientException) {
                        logger.warn(restClientException);
                        return null;
                    }
                },
                stringContainsInOrder(data.getSpecialSymbols())
        );
    }
}
