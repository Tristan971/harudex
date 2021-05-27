package moe.tristan.harudex.common;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class HaruDexClientRequestsLogger implements ClientHttpRequestInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(HaruDexClientRequestsLogger.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        long startTimeMillis = System.currentTimeMillis();

        String requestMethodAndPath = "%s %s".formatted(request.getMethod(), request.getURI());

        ClientHttpResponse response = null;
        try {
            LOGGER.info("{} begin", requestMethodAndPath);
            response = execution.execute(request, body);
            return response;
        } finally {
            if (response != null) {
                long endTimeMillis = System.currentTimeMillis();
                LOGGER.info("{} {} ({}ms)", requestMethodAndPath, response.getRawStatusCode(), endTimeMillis - startTimeMillis);
            }
        }
    }

}
