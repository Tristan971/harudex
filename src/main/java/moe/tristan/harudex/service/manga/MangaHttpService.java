package moe.tristan.harudex.service.manga;

import java.net.URI;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import moe.tristan.harudex.MangaService;
import moe.tristan.harudex.model.manga.MangaSearchCriteria;
import moe.tristan.harudex.model.manga.MangaSearchResponse;

@Service
public class MangaHttpService implements MangaService {

    private final RestTemplate restTemplate;

    public MangaHttpService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public MangaSearchResponse search() {
        return restTemplate.getForObject("/manga", MangaSearchResponse.class);
    }

    @Override
    public MangaSearchResponse search(MangaSearchCriteria criteria) {
        URI uri = UriComponentsBuilder
            .fromUriString("/manga")
            .queryParams(criteria.asQueryParameters())
            .build(false)
            .toUri();
        return restTemplate.getForObject(uri, MangaSearchResponse.class);
    }

}
