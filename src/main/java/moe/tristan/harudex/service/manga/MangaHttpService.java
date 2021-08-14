package moe.tristan.harudex.service.manga;

import java.net.URI;
import java.util.UUID;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import moe.tristan.harudex.MangaService;
import moe.tristan.harudex.model.manga.MangaCreateRequest;
import moe.tristan.harudex.model.manga.MangaEntity;
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

    @Override
    public MangaEntity findById(UUID id) {
        return restTemplate.getForObject("/manga/{uuid}", MangaEntity.class, id);
    }

    @Override
    public MangaEntity create(MangaCreateRequest createRequest) {
        return restTemplate.postForObject("/manga", createRequest, MangaEntity.class);
    }

}