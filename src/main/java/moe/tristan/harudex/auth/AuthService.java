package moe.tristan.harudex.auth;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import moe.tristan.harudex.HaruDexContext;

@Service
public class AuthService {

    private final HaruDexContext haruDexContext;
    private final RestTemplate restTemplate;

    public AuthService(HaruDexContext haruDexContext, RestTemplate restTemplate) {
        this.haruDexContext = haruDexContext;
        this.restTemplate = restTemplate;
    }

    public LoginResponse login(String username, String password) {
        String endpoint = haruDexContext.getMangadexApiUrl() + "/auth/login";
        LoginRequest loginRequest = new LoginRequest(username, password);

        return restTemplate.postForObject(endpoint, loginRequest, LoginResponse.class);
    }

}
