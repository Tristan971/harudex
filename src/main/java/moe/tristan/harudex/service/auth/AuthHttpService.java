package moe.tristan.harudex.service.auth;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import moe.tristan.harudex.AuthService;
import moe.tristan.harudex.HaruDexProperties;
import moe.tristan.harudex.model.auth.AuthCheckTokenResponse;
import moe.tristan.harudex.model.auth.AuthLoginRequest;
import moe.tristan.harudex.model.auth.AuthLoginResponse;
import moe.tristan.harudex.model.auth.AuthLogoutResponse;
import moe.tristan.harudex.model.auth.AuthRefreshTokenRequest;
import moe.tristan.harudex.model.auth.AuthRefreshTokenResponse;

@Service
public class AuthHttpService implements AuthService {

    private final RestTemplate restTemplate;
    private final HaruDexProperties haruDexProperties;

    public AuthHttpService(RestTemplateBuilder restTemplateBuilder, HaruDexProperties haruDexProperties) {
        this.restTemplate = restTemplateBuilder.build();
        this.haruDexProperties = haruDexProperties;
    }

    @Override
    public AuthLoginResponse login(AuthLoginRequest authLoginRequest) {
        return restTemplate.postForObject(haruDexProperties.getBaseUrl() + "/auth/login", authLoginRequest, AuthLoginResponse.class);
    }

    @Override
    public AuthCheckTokenResponse checkToken() {
        return restTemplate.getForObject(haruDexProperties.getBaseUrl() + "/auth/check", AuthCheckTokenResponse.class);
    }

    @Override
    public AuthLogoutResponse logout() {
        return restTemplate.postForObject(haruDexProperties.getBaseUrl() + "/auth/logout", null, AuthLogoutResponse.class);
    }

    @Override
    public AuthRefreshTokenResponse refreshToken(AuthRefreshTokenRequest refreshTokenRequest) {
        return restTemplate.postForObject(haruDexProperties.getBaseUrl() + "/auth/refresh", refreshTokenRequest, AuthRefreshTokenResponse.class);
    }

}
