package moe.tristan.harudex.lang;

import java.util.List;

import org.springframework.http.HttpHeaders;

import moe.tristan.harudex.model.auth.AuthToken;

public final class AuthorizationHeaders {

    private AuthorizationHeaders() {
    }

    public static HttpHeaders authHeaders(AuthToken authToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.put(HttpHeaders.AUTHORIZATION, List.of("Bearer " + authToken.getSession()));
        return headers;
    }

}
