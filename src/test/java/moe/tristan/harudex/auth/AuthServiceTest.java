package moe.tristan.harudex.auth;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.ResponseCreator;

import moe.tristan.harudex.HaruDexApiClientTest;
import moe.tristan.harudex.HaruDexContext;
import moe.tristan.harudex.common.ApiError;
import moe.tristan.harudex.common.Result;

@SpringBootTest(classes = AuthService.class)
@HaruDexApiClientTest
class AuthServiceTest {

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String LOGIN_REQUEST = """
        {
          "username": "%s",
          "password": "%s"
        }
        """.formatted(USERNAME, PASSWORD);

    @MockBean
    private HaruDexContext haruDexContext;

    @Autowired
    private MockRestServiceServer mockRestServiceServer;

    @Autowired
    private AuthService authService;

    @BeforeEach
    void setUp() {
        when(haruDexContext.getMangadexApiUrl()).thenReturn("https://api.mangadex.test");
    }

    @Nested
    public class Login {

        @Test
        void loginSuccess() {
            String httpResponse = """
                {
                  "result": "ok",
                  "token": {
                      "session": "session token",
                      "refresh": "refresh token"
                  }
                }
                """;

            Token expectedToken = new Token("session token", "refresh token");
            LoginResponse expected = new LoginResponse(Result.OK, expectedToken, null);

            expectLoginRequestAndRespondWith(
                withSuccess()
                    .contentType(APPLICATION_JSON)
                    .body(httpResponse)
            );

            LoginResponse result = authService.login(USERNAME, PASSWORD);
            assertThat(result).isEqualTo(expected);
        }

        @Test
        void invalidRequest() {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            ApiError error = new ApiError(
                UUID.fromString("004644eb-e9b2-4665-8caa-fcb9c3f2f6f6"),
                status.value(),
                "exception_title",
                "Exception detail",
                null
            );
            LoginResponse expectedResponse = new LoginResponse(Result.ERROR, null, List.of(error));

            String failureResponse = """
                {
                  "result": "error",
                  "errors": [
                    {
                      "id": "004644eb-e9b2-4665-8caa-fcb9c3f2f6f6",
                      "status": 400,
                      "title": "exception_title",
                      "detail": "Exception detail",
                      "context": null
                    }
                  ]
                }
                """;

            expectLoginRequestAndRespondWith(
                withStatus(status)
                    .contentType(APPLICATION_JSON)
                    .body(failureResponse)
            );

            LoginResponse response = authService.login(USERNAME, PASSWORD);
            assertThat(response).isEqualTo(expectedResponse);
        }

        @Test
        void invalidCredentials() {
            HttpStatus status = HttpStatus.UNAUTHORIZED;
            ApiError error = new ApiError(
                UUID.fromString("004644eb-e9b2-4665-8caa-fcb9c3f2f6f6"),
                status.value(),
                "unauthorized_http_exception",
                "User / Password does not match",
                null
            );
            LoginResponse expectedResponse = new LoginResponse(Result.ERROR, null, List.of(error));

            String failureResponse = """
                {
                  "result": "error",
                  "errors": [
                    {
                      "id": "004644eb-e9b2-4665-8caa-fcb9c3f2f6f6",
                      "status": 401,
                      "title": "unauthorized_http_exception",
                      "detail": "User / Password does not match",
                      "context": null
                    }
                  ]
                }
                """;

            expectLoginRequestAndRespondWith(
                withStatus(status)
                    .contentType(APPLICATION_JSON)
                    .body(failureResponse)
            );

            LoginResponse response = authService.login(USERNAME, PASSWORD);
            assertThat(response).isEqualTo(expectedResponse);
        }

        private void expectLoginRequestAndRespondWith(ResponseCreator responseCreator) {
            mockRestServiceServer
                .expect(method(HttpMethod.POST))
                .andExpect(requestTo(haruDexContext.getMangadexApiUrl() + "/auth/login"))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(content().json(LOGIN_REQUEST, false))
                .andRespond(responseCreator);
        }

    }

    @Nested
    public class Logout {

        @Test
        void successfully() {
            String sessionToken = "bearer-token";
            Token token = new Token(sessionToken, "refresh token");
            when(haruDexContext.getCurrentToken()).thenReturn(Optional.of(token));

            String response = """
                {
                  "result": "ok"
                }
                """;

            LogoutResponse expected = new LogoutResponse(Result.OK, null);

            mockRestServiceServer
                .expect(method(HttpMethod.POST))
                .andExpect(requestTo(haruDexContext.getMangadexApiUrl() + "/auth/logout"))
                .andExpect(header(HttpHeaders.AUTHORIZATION, "Bearer " + sessionToken))
                .andRespond(
                    withSuccess()
                        .contentType(APPLICATION_JSON)
                        .body(response)
                );

            LogoutResponse logoutResponse = authService.logout();
            assertThat(logoutResponse).isEqualTo(expected);

            verify(haruDexContext).resetToken();
        }

    }

    @Nested
    public class CheckToken {

        @Test
        void checkToken() {
            String sessionToken = "bearer-token";
            Token token = new Token(sessionToken, "refresh token");
            when(haruDexContext.getCurrentToken()).thenReturn(Optional.of(token));

            String response = """
                {
                  "ok": "ok",
                  "isAuthenticated": true,
                  "roles": [
                    "role a",
                    "role b"
                  ],
                  "permissions": [
                    "perm a",
                    "perm b"
                  ]
                }
                """;

            CheckTokenResponse expected = new CheckTokenResponse(
                Result.OK,
                true,
                List.of("role a", "role b"),
                List.of("perm a", "perm b")
            );

            mockRestServiceServer
                .expect(method(HttpMethod.GET))
                .andExpect(requestTo(haruDexContext.getMangadexApiUrl() + "/auth/check"))
                .andExpect(header(HttpHeaders.AUTHORIZATION, "Bearer " + sessionToken))
                .andRespond(
                    withSuccess()
                        .contentType(APPLICATION_JSON)
                        .body(response)
                );

            CheckTokenResponse checkTokenResponse = authService.checkToken();
            assertThat(checkTokenResponse).isEqualTo(expected);
        }

    }

    @Nested
    public class RefreshToken {

        @Test
        void successfully() {
            String sessionToken = "bearer-token";
            String refreshToken = "refresh-token";
            Token token = new Token(sessionToken, refreshToken);
            when(haruDexContext.getCurrentToken()).thenReturn(Optional.of(token));

            String request = """
                {
                  "token": "refresh-token"
                }
                """;

            String response = """
                {
                  "result": "ok",
                  "token": {
                    "session": "new-session-token",
                    "refresh": "new-refresh-token"
                  },
                  "message": "Message from MangaDex"
                }
                """;

            RefreshTokenResponse expected = new RefreshTokenResponse(
                Result.OK,
                new Token("new-session-token", "new-refresh-token"),
                "Message from MangaDex"
            );

            mockRestServiceServer
                .expect(method(HttpMethod.POST))
                .andExpect(requestTo(haruDexContext.getMangadexApiUrl() + "/auth/refresh"))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(content().json(request))
                .andRespond(
                    withSuccess()
                        .contentType(APPLICATION_JSON)
                        .body(response)
                );

            RefreshTokenResponse refreshTokenResponse = authService.refreshToken();
            assertThat(refreshTokenResponse).isEqualTo(expected);

            verify(haruDexContext).setCurrentToken(eq(refreshTokenResponse.token()));
        }

    }

    @AfterEach
    void verifyInteraction() {
        mockRestServiceServer.verify();
    }

}
