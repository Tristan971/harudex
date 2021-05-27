package moe.tristan.harudex.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import moe.tristan.harudex.HaruDexContext;
import moe.tristan.harudex.common.Result;

@Service
public class AuthService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthService.class);

    private final HaruDexContext haruDexContext;
    private final RestTemplate restTemplate;

    public AuthService(HaruDexContext haruDexContext, RestTemplate restTemplate) {
        this.haruDexContext = haruDexContext;
        this.restTemplate = restTemplate;
    }

    public LoginResponse login(String username, String password) {
        String endpoint = haruDexContext.getMangadexApiUrl() + "/auth/login";

        LoginRequest loginRequest = new LoginRequest(username, password);
        LoginResponse loginResponse = restTemplate.postForObject(endpoint, loginRequest, LoginResponse.class);

        if (loginResponse != null && Result.OK.equals(loginResponse.result())) {
            LOGGER.info("Logged in to MangaDex");
            haruDexContext.setCurrentToken(loginResponse.token());
        }

        return loginResponse;
    }

    public LogoutResponse logout() {
        String endpoint = haruDexContext.getMangadexApiUrl() + "/auth/logout";

        LogoutResponse logoutResponse = restTemplate.postForObject(endpoint, null, LogoutResponse.class);

        if (logoutResponse != null && Result.OK.equals(logoutResponse.result())) {
            LOGGER.info("Logged out of MangaDex");
            haruDexContext.resetToken();
        }

        return logoutResponse;
    }

    public CheckTokenResponse checkToken() {
        String endpoint = haruDexContext.getMangadexApiUrl() + "/auth/check";

        return restTemplate.getForObject(endpoint, CheckTokenResponse.class);
    }

    public RefreshTokenResponse refreshToken() {
        String endpoint = haruDexContext.getMangadexApiUrl() + "/auth/refresh";

        String refreshToken = haruDexContext
            .getCurrentToken()
            .map(Token::refresh)
            .orElseThrow(() -> new UnsupportedOperationException("Cannot refresh MangaDex token because no token in context."));
        RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest(refreshToken);
        RefreshTokenResponse refreshTokenResponse = restTemplate.postForObject(endpoint, refreshTokenRequest, RefreshTokenResponse.class);

        if (refreshTokenResponse != null && Result.OK.equals(refreshTokenResponse.result())) {
            LOGGER.info("Refreshed session token for MangaDex - Message from MangaDex: {}", refreshTokenResponse.message());
            haruDexContext.setCurrentToken(refreshTokenResponse.token());
        }

        return refreshTokenResponse;
    }

}
