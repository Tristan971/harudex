package moe.tristan.harudex.service.cover;

import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.io.ByteArrayInputStream;
import java.util.Random;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.web.client.MockRestServiceServer;

import moe.tristan.harudex.HttpClientTest;
import moe.tristan.harudex.model.auth.AuthToken;

@HttpClientTest(CoverHttpService.class)
class CoverHttpServiceUploadTest {

    @Autowired
    private MockRestServiceServer mangadexApi;

    @Autowired
    private CoverHttpService coverHttpService;

    @Test
    void upload() {
        byte[] bytes = new byte[1024];
        new Random().nextBytes(bytes);

        mangadexApi
            .expect(method(POST))
            .andExpect(requestTo("/cover/f68e2fb3-73f6-4a97-9b17-b4a34bea0dcd"))
            .andExpect(header("Authorization", "Bearer session-token"))
            .andExpect(content().contentType(APPLICATION_OCTET_STREAM))
            .andExpect(content().bytes(bytes))
            .andRespond(withSuccess(
                new ClassPathResource("stubs/cover/cover_view.json"),
                APPLICATION_JSON
            ));

        UUID id = UUID.fromString("f68e2fb3-73f6-4a97-9b17-b4a34bea0dcd");
        coverHttpService.upload(AuthToken.of("session-token", "refresh-token"), id, new ByteArrayInputStream(bytes));

        mangadexApi.verify();
    }

}
