package moe.tristan.harudex.auth;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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

    @Test
    void loginSuccess() {
        Token token = new Token("session token", "refresh token");
        String successResponse = """
            {
              "result": "ok",
              "token": {
                  "session": "%s",
                  "refresh": "%s"
              }
            }
            """.formatted(token.session(), token.refresh());

        LoginResponse expectedResult = new LoginResponse(Result.OK, token, null);

        expectLoginRequestAndRespondWith(
            withSuccess()
                .contentType(APPLICATION_JSON)
                .body(successResponse)
        );

        LoginResponse response = authService.login(USERNAME, PASSWORD);

        assertThat(response).isEqualTo(expectedResult);
    }

    @Test
    void invalidRequest() {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiError error = new ApiError(
            UUID.randomUUID(),
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
                  "id": "%s",
                  "status": %s,
                  "title": "%s",
                  "detail": "%s",
                  "context": null
                }
              ]
            }
            """.formatted(error.id(), error.status(), error.title(), error.detail());

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
            UUID.randomUUID(),
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
                  "id": "%s",
                  "status": %s,
                  "title": "%s",
                  "detail": "%s",
                  "context": null
                }
              ]
            }
            """.formatted(error.id(), error.status(), error.title(), error.detail());

        expectLoginRequestAndRespondWith(
            withStatus(status)
                .contentType(APPLICATION_JSON)
                .body(failureResponse)
        );

        LoginResponse response = authService.login(USERNAME, PASSWORD);

        assertThat(response).isEqualTo(expectedResponse);
    }

    @AfterEach
    void verify() {
        mockRestServiceServer.verify();
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
