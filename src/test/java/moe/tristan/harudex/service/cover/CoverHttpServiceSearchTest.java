package moe.tristan.harudex.service.cover;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.web.client.MockRestServiceServer;

import moe.tristan.harudex.HttpClientTest;

@HttpClientTest(CoverHttpService.class)
class CoverHttpServiceSearchTest {

    @Autowired
    private MockRestServiceServer mangadexApi;

    @Autowired
    private CoverHttpService coverHttpService;

    @Test
    void findById() {
        mangadexApi
            .expect(method(GET))
            .andExpect(requestTo("/cover/f68e2fb3-73f6-4a97-9b17-b4a34bea0dcd"))
            .andRespond(withSuccess(
                new ClassPathResource("stubs/cover/cover_view.json"),
                APPLICATION_JSON
            ));

        UUID id = UUID.fromString("f68e2fb3-73f6-4a97-9b17-b4a34bea0dcd");
        coverHttpService.findById(id);

        mangadexApi.verify();
    }

    @Test
    void search() {
        mangadexApi
            .expect(method(GET))
            .andExpect(requestTo("/cover"))
            .andRespond(withSuccess(
                new ClassPathResource("stubs/cover/cover_search.json"),
                APPLICATION_JSON
            ));

        coverHttpService.search();

        mangadexApi.verify();
    }

}
