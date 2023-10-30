package tech.romashov.steps;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import tech.romashov.http.HttpClient;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Component
public class DevowelizerRequests {
    private Log logger = LogFactory.getLog(getClass());
    @Autowired
    private HttpClient httpClient;

    private UriComponentsBuilder host = UriComponentsBuilder.fromUriString("http://localhost:8080");

    public String get(String path) {
        logger.info(String.format("Create GET request to %s", host.path(path).toUriString()));
        ResponseEntity<String> response = httpClient.get(host.path(path).toUriString(), String.class);
        logger.info("Status code should be 200");
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        return response.getBody();
    }
}
