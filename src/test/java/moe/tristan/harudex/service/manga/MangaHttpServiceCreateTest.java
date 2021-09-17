package moe.tristan.harudex.service.manga;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.util.Locale;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.web.client.MockRestServiceServer;

import moe.tristan.harudex.HttpClientTest;
import moe.tristan.harudex.model.auth.AuthToken;
import moe.tristan.harudex.model.common.statics.ContentRating;
import moe.tristan.harudex.model.common.statics.EntityType;
import moe.tristan.harudex.model.common.statics.PublicationStatus;
import moe.tristan.harudex.model.manga.MangaCreateRequest;
import moe.tristan.harudex.model.manga.MangaEntity;

@HttpClientTest(MangaHttpService.class)
public class MangaHttpServiceCreateTest {

    @Autowired
    private MockRestServiceServer mangadexApi;

    @Autowired
    private MangaHttpService mangaHttpService;

    @Test
    void create() {

        mangadexApi
            .expect(method(POST))
            .andExpect(requestTo("https://api.mangadex.org/manga"))
            .andExpect(header(AUTHORIZATION, "Bearer session-token"))
            .andExpect(content().contentType(APPLICATION_JSON))
            .andExpect(content().json(
                """
                    {
                      "title": {
                        "en": "Test title"
                      },
                      "description": {
                        "en": "Lotsa stuff happens and it's cool etc"
                      },
                      "authors": ["6297d793-581b-4b85-aa8f-e955ec7d427c"],
                      "contentRating": "suggestive",
                      "status": "ongoing",
                      "originalLanguage": "ja",
                      "version": 1
                    }
                    """
            ))
            .andRespond(
                withSuccess()
                    .contentType(APPLICATION_JSON)
                    .body(new ClassPathResource("stubs/manga/manga_view.json"))
            );

        UUID uuid = UUID.fromString("6297d793-581b-4b85-aa8f-e955ec7d427c");
        var request = MangaCreateRequest
            .builder()
            .putTitle("en", "Test title")
            .putDescription("en", "Lotsa stuff happens and it's cool etc")
            .addAuthors(uuid)
            .publicationStatus(PublicationStatus.ONGOING)
            .contentRating(ContentRating.SUGGESTIVE)
            .originalLanguage(Locale.JAPANESE.getLanguage())
            .build();

        MangaEntity mangaEntity = mangaHttpService.create(AuthToken.of("session-token", "refresh-token"), request);

        assertThat(mangaEntity.getData().getType()).isEqualTo(EntityType.MANGA);
        assertThat(mangaEntity.getData().getRelationships()).isNotEmpty();

        mangadexApi.verify();
    }

}
