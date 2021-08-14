package moe.tristan.harudex;

import java.io.IOException;
import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

@TestComponent
public class ClientRequests implements ClientHttpRequestInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientRequests.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        long start = System.nanoTime();
        LOGGER.info("{} {} begin", request.getMethod(), request.getURI());

        ClientHttpResponse response = execution.execute(request, body);

        long end = System.nanoTime();
        LOGGER.info("{} {} {} ({} ms)", request.getMethod(), request.getURI(), response.getStatusCode().value(), Duration.ofNanos(end - start).toMillis());
        return response;
    }

}
