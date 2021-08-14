package moe.tristan.harudex.service.auth;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.client.MockRestServiceServer;

import moe.tristan.harudex.model.auth.AuthLoginRequest;
import moe.tristan.harudex.model.auth.AuthLoginResponse;
import moe.tristan.harudex.model.auth.AuthToken;
import moe.tristan.harudex.model.common.statics.ResultType;
import moe.tristan.harudex.HttpClientTest;

@HttpClientTest(AuthHttpService.class)
class AuthHttpServiceTest {

    @Autowired
    private MockRestServiceServer mangadexApi;

    @Autowired
    private AuthHttpService authHttpService;

    @Test
    void login() {
        mangadexApi
            .expect(method(POST))
            .andExpect(requestTo("/auth/login"))
            .andExpect(content().contentType(APPLICATION_JSON))
            .andExpect(content().json(
                """
                    {
                      "username": "tristan",
                      "password": "very-secret"
                    }
                    """
            ))
            .andRespond(
                withSuccess()
                    .contentType(APPLICATION_JSON)
                    .body(
                        """
                            {
                              "result": "ok",
                              "token": {
                                "session": "some-session-token",
                                "refresh": "some-refresh-token"
                              }
                            }
                            """
                    )
            );

        AuthLoginRequest request = AuthLoginRequest
            .builder()
            .username("tristan")
            .password("very-secret")
            .build();

        AuthLoginResponse response = authHttpService.login(request);
        assertThat(response.getResult()).isEqualTo(ResultType.OK);
        assertThat(response.getToken()).isEqualTo(AuthToken.of("some-session-token", "some-refresh-token"));
    }

    @Test
    void checkToken() {
    }

    @Test
    void logout() {
    }

    @Test
    void refreshToken() {
    }

}
