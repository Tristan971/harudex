package moe.tristan.harudex.service.manga;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.web.client.MockRestServiceServer;

import moe.tristan.harudex.HttpClientTest;
import moe.tristan.harudex.model.common.statics.OrderType;
import moe.tristan.harudex.model.common.statics.PublicationStatus;
import moe.tristan.harudex.model.manga.MangaSearchCriteria;

@HttpClientTest(MangaHttpService.class)
class MangaHttpServiceSearchTest {

    @Autowired
    private MockRestServiceServer mangadexApi;

    @Autowired
    private MangaHttpService mangaHttpService;

    @Test
    void searchNoParams() {
        mangadexApi
            .expect(method(GET))
            .andExpect(requestTo("/manga"))
            .andRespond(
                withSuccess()
                    .contentType(APPLICATION_JSON)
                    .body(new ClassPathResource("stubs/manga/search_nocriteria.json"))
            );

        mangaHttpService.search();

        mangadexApi.verify();
    }

    @Test
    void searchParams() {
        mangadexApi
            .expect(method(GET))
            .andExpect(requestTo("/manga?title=A%20title&status[]=ongoing&status[]=cancelled&order[createdAt]=asc&order[updatedAt]=desc"))
            .andRespond(
                withSuccess()
                    .contentType(APPLICATION_JSON)
                    .body(new ClassPathResource("stubs/manga/search_noresults.json"))
            );

        // ensure we respect ordering if present
        var statuses = new LinkedHashSet<>(List.of(PublicationStatus.ONGOING, PublicationStatus.CANCELLED));

        var ordering = new LinkedHashMap<String, OrderType>();
        ordering.put("createdAt", OrderType.ASC);
        ordering.put("updatedAt", OrderType.DESC);

        var criteria = MangaSearchCriteria
            .builder()
            .title("A title")
            .statuses(statuses)
            .ordering(ordering)
            .build();

        mangaHttpService.search(criteria);

        mangadexApi.verify();
    }

}
