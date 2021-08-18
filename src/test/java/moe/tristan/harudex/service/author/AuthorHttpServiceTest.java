package moe.tristan.harudex.service.author;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import moe.tristan.harudex.HttpClientTest;
import moe.tristan.harudex.model.auth.AuthToken;
import moe.tristan.harudex.model.author.AuthorCreateRequest;
import moe.tristan.harudex.model.author.AuthorSearchCriteria;

@HttpClientTest(AuthorHttpService.class)
class AuthorHttpServiceTest {

    @Autowired
    private AuthorHttpService authorHttpService;

    @Autowired
    private MockRestServiceServer mangadexApi;

    @Test
    void create() {
        mangadexApi
            .expect(method(POST))
            .andExpect(requestTo("https://api.mangadex.org/author"))
            .andExpect(header(AUTHORIZATION, "Bearer session-token"))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(
                """
                    {
                      "name": "Mochi au Lait",
                      "version": 1
                    }
                    """
            ))
            .andRespond(withSuccess(new ClassPathResource("stubs/author/author_entity.json"), MediaType.APPLICATION_JSON));

        AuthorCreateRequest request = AuthorCreateRequest.of("Mochi au Lait");
        authorHttpService.create(AuthToken.of("session-token", "refresh-token"), request);

        mangadexApi.verify();
    }

    @Test
    void search() {
        mangadexApi
            .expect(method(GET))
            .andExpect(requestTo("https://api.mangadex.org/author?name=Mochi+au+Lait&limit=5"))
            .andRespond(withSuccess(new ClassPathResource("stubs/author/author_search.json"), MediaType.APPLICATION_JSON));

        AuthorSearchCriteria criteria = AuthorSearchCriteria
            .builder()
            .name("Mochi au Lait")
            .limit(5)
            .build();

        authorHttpService.search(criteria);

        mangadexApi.verify();
    }

}
