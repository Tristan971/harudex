package moe.tristan.harudex.common;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import moe.tristan.harudex.HaruDexContext;

public final class AuthClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    private final HaruDexContext haruDexContext;

    public AuthClientHttpRequestInterceptor(HaruDexContext haruDexContext) {
        this.haruDexContext = haruDexContext;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        haruDexContext.getCurrentToken().ifPresent(token -> request.getHeaders().setBearerAuth(token.session()));
        return execution.execute(request, body);
    }

}
