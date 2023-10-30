package tech.romashov.requests;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.HtmlUtils;
import org.springframework.web.util.UriComponentsBuilder;
import tech.romashov.http.HttpClient;

@Component
public class DevowelizerRequests {
    private Log logger = LogFactory.getLog(getClass());
    @Autowired
    private HttpClient httpClient;

    private String baseHost = "http://localhost:8080";

    private HttpEntity getDefaultHttpEntity() {
        return new HttpEntity<>("", new HttpHeaders());
    }

    public ResponseEntity<String> get(String path) {
        String uri = UriComponentsBuilder.fromUriString(baseHost)
                .path(path)
                .build()
                .toUriString();
        logger.info(String.format("Create GET request to %s", uri));
        return httpClient.get(uri, getDefaultHttpEntity(), String.class);
    }

    public ResponseEntity<String> get(String path, HttpHeaders headers) {
        String uri = UriComponentsBuilder.fromUriString(baseHost)
                .path(path)
                .build()
                .toUriString();
        logger.info(String.format("Create GET request to %s", uri));
        return httpClient.get(uri, new HttpEntity<>("", headers), String.class);
    }

    public ResponseEntity<String> post(String path) {
        String uri = UriComponentsBuilder.fromUriString(baseHost)
                .path(path)
                .build()
                .toUriString();
        logger.info(String.format("Create POST request to %s", uri));
        return httpClient.post(uri, getDefaultHttpEntity(), String.class);
    }

    public ResponseEntity<String> head(String path) {
        String uri = UriComponentsBuilder.fromUriString(baseHost)
                .path(path)
                .build()
                .toUriString();
        logger.info(String.format("Create HEAD request to %s", uri));
        return httpClient.head(uri, getDefaultHttpEntity(), String.class);
    }

    public ResponseEntity<String> delete(String path) {
        String uri = UriComponentsBuilder.fromUriString(baseHost)
                .path(path)
                .build()
                .toUriString();
        logger.info(String.format("Create DELETE request to %s", uri));
        return httpClient.delete(uri, getDefaultHttpEntity(), String.class);
    }
}
