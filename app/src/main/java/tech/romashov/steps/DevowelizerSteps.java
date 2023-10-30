package tech.romashov.steps;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import tech.romashov.matchers.AssertWithTimeout;
import tech.romashov.requests.DevowelizerRequests;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.stringContainsInOrder;

@Component
public class DevowelizerSteps {
    private Log logger = LogFactory.getLog(getClass());
    @Autowired
    private DevowelizerRequests devowelizerRequests;
    @Autowired
    private TestDataProvider data;


    public void serviceCanReturnOutputWithoutVowels() throws Throwable {
        AssertWithTimeout.assertThat(
                () -> {
                    try {
                        ResponseEntity<String> response = devowelizerRequests.get(String.join("", data.getAlphabet()));
                        logger.info("Status code should be 200");
                        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
                        logger.info("Body is: " + response.getBody());
                        return response.getBody();
                    } catch (RestClientException restClientException) {
                        logger.warn(restClientException);
                        return null;
                    }
                },
                both(stringContainsInOrder(data.getConsonants())).and(not(stringContainsInOrder(data.getVowels())))
        );
        AtomicReference<ResponseEntity<String>> resp = new AtomicReference<>();
        AssertWithTimeout.assertThat(
                () -> {
                    try {
                        ResponseEntity<String> response = devowelizerRequests.get(String.join("", data.getVowels()));
                        logger.info("Status code should be 200");
                        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
                        logger.info("Body is: " + response.getBody());
                        resp.set(response);
                        return resp;
                    } catch (RestClientException restClientException) {
                        logger.warn(restClientException);
                        return null;
                    }
                },
                not(nullValue())
        );
        assertThat(resp.get().getBody(), nullValue());
        AssertWithTimeout.assertThat(
                () -> {
                    try {
                        ResponseEntity<String> response = devowelizerRequests.get(String.join("", data.getConsonants()));
                        logger.info("Status code should be 200");
                        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
                        logger.info("Body is: " + response.getBody());
                        return response.getBody();
                    } catch (RestClientException restClientException) {
                        logger.warn(restClientException);
                        return null;
                    }
                },
                stringContainsInOrder(data.getConsonants())
        );
    }

    public void thereAreNoHttpMethodsExceptGET() throws Exception {
        verifyThatThereIsHttpMethod(() -> devowelizerRequests.get(String.join("", data.getAlphabet())));
        verifyThatThereIsNoHttpMethod(() -> devowelizerRequests.post(String.join("", data.getAlphabet())), HttpMethod.POST);
        verifyThatThereIsNoHttpMethod(() -> devowelizerRequests.delete(String.join("", data.getAlphabet())), HttpMethod.DELETE);
        verifyThatThereIsNoHttpMethod(() -> devowelizerRequests.head(String.join("", data.getAlphabet())), HttpMethod.HEAD);
    }

    private void verifyThatThereIsNoHttpMethod(Runnable invokeHttpRequest, HttpMethod method) {
        String expectedHtml = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
               "<head>\n" +
                  "<meta charset=\"utf-8\">\n" +
                  "<title>Error</title>\n" +
               "</head>\n" +
               "<body>\n" +
                  "<pre>Not Found</pre>\n" +
               "</body>\n" +
            "</html>\n";
        try {
            invokeHttpRequest.run();
            throw new AssertionError(String.format("There is an endpoint for %s http method, but should not be exist", method));
        } catch (HttpServerErrorException | HttpClientErrorException exception) {
            logger.info("Response should have NOT_FOUND http status");
            assertThat(exception.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
            logger.info("Response should have Not Found http body");
            assertThat(exception.getResponseBodyAsString(), equalTo(expectedHtml));
        }
    }

    private void verifyThatThereIsHttpMethod(Callable<ResponseEntity<String>> invokeHttpRequest) throws Exception {
        try {
            ResponseEntity<String> response = invokeHttpRequest.call();
            logger.info("Response should not have NOT_FOUND http status");
            assertThat(response.getStatusCode(), not(HttpStatus.NOT_FOUND));
        } catch (HttpStatusCodeException exception) {
            logger.info("Response should not have NOT_FOUND http status");
            assertThat(exception.getStatusCode(), not(HttpStatus.NOT_FOUND));
        }
    }

    public void shouldNotReturnAnErrorDuringThePeriod() throws Throwable {
        Instant begin = Instant.now();
        Duration testTimeout = Duration.ofMinutes(1);
        while (Duration.between(begin, Instant.now()).toMillis() < testTimeout.toMillis()) {
            try {
                ResponseEntity<String> response = devowelizerRequests.get(String.join("", data.getAlphabet()));
                logger.info("Returns a response with 200 OK status code");
                assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
            } catch (HttpStatusCodeException exception) {
                logger.info("Returns a response with error in HttpStatusCode");
                assertThat(exception.getStatusCode(), equalTo(HttpStatus.OK));
            }
        }
    }

    public void serviceShouldReturnWitoutCapitalVowels() throws Throwable {
        AssertWithTimeout.assertThat(
                () -> {
                    try {
                        ResponseEntity<String> response = devowelizerRequests.get(String.join("", data.getAlphabetCapitalLetters()));
                        logger.info("Status code should be 200");
                        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
                        logger.info("Body is: " + response.getBody());
                        return response.getBody();
                    } catch (RestClientException restClientException) {
                        logger.warn(restClientException);
                        return null;
                    }
                },
                both(stringContainsInOrder(data.getConsonantsCapitalLetters())).and(not(stringContainsInOrder(data.getVowels())))
        );
        AtomicReference<ResponseEntity<String>> resp = new AtomicReference<>();
        AssertWithTimeout.assertThat(
                () -> {
                    try {
                        ResponseEntity<String> response = devowelizerRequests.get(String.join("", data.getVowelsCapitalLetters()));
                        logger.info("Status code should be 200");
                        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
                        logger.info("Body is: " + response.getBody());
                        resp.set(response);
                        return resp;
                    } catch (RestClientException restClientException) {
                        logger.warn(restClientException);
                        return null;
                    }
                },
                not(nullValue())
        );
        assertThat(resp.get().getBody(), stringContainsInOrder(data.getVowelsCapitalLetters()));
        AssertWithTimeout.assertThat(
                () -> {
                    try {
                        ResponseEntity<String> response = devowelizerRequests.get(String.join("", data.getConsonantsCapitalLetters()));
                        logger.info("Status code should be 200");
                        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
                        logger.info("Body is: " + response.getBody());
                        return response.getBody();
                    } catch (RestClientException restClientException) {
                        logger.warn(restClientException);
                        return null;
                    }
                },
                stringContainsInOrder(data.getConsonantsCapitalLetters())
        );
    }

    public void serviceShouldContainDefaultHeaders() throws Throwable {
        HttpHeaders defaultHeaders = new HttpHeaders();
        defaultHeaders.add("agent", "devowelizer-test-tool");
        AtomicReference<ResponseEntity<String>> response = new AtomicReference<>();
        AssertWithTimeout.assertThat(
            () -> {
                try {
                    response.set(devowelizerRequests.get("input"));
                    return response;
                } catch (RestClientException exception) {
                    return null;
                }
            },
            not(nullValue())
        );
        assertThat(
                response.get().getHeaders(),
                hasEntry(is("Connection"), contains("keep-alive"))
        );
        assertThat(
                response.get().getHeaders(),
                hasEntry(is("Content-Length"), contains("3"))
        );
        assertThat(
                response.get().getHeaders(),
                hasEntry(is("Content-Type"), contains("text/html; charset=utf-8"))
        );
        assertThat(
                response.get().getHeaders(),
                hasEntry(is("X-Powered-By"), contains("Express"))
        );
    }

    public void serviceShouldReturnEmptyRequestWhenThereAreNoParameters() throws Throwable {
        AtomicReference<ResponseEntity<String>> resp = new AtomicReference<>();
        AssertWithTimeout.assertThat(
                () -> {
                    try {
                        ResponseEntity<String> response = devowelizerRequests.get("");
                        logger.info("Status code should be 200");
                        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
                        logger.info("Body is: " + response.getBody());
                        resp.set(response);
                        return resp;
                    } catch (RestClientException restClientException) {
                        logger.warn(restClientException);
                        return null;
                    }
                },
                not(nullValue())
        );
        assertThat(resp.get().getBody(), equalTo("Send GET to /:input"));
    }

    public void serviceShouldReturnOnlyNumbers() throws Throwable {
        AssertWithTimeout.assertThat(
                () -> {
                    try {
                        ResponseEntity<String> response = devowelizerRequests.get(String.join("", data.getNumbers()));
                        logger.info("Status code should be 200");
                        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
                        logger.info("Body is: " + response.getBody());
                        return response.getBody();
                    } catch (RestClientException restClientException) {
                        logger.warn(restClientException);
                        return null;
                    }
                },
                stringContainsInOrder(data.getNumbers())
        );
    }
}
