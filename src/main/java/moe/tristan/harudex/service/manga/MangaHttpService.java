package moe.tristan.harudex.service.manga;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import moe.tristan.harudex.HaruDexProperties;
import moe.tristan.harudex.MangaService;
import moe.tristan.harudex.lang.AuthorizationHeaders;
import moe.tristan.harudex.model.auth.AuthToken;
import moe.tristan.harudex.model.manga.MangaCreateRequest;
import moe.tristan.harudex.model.manga.MangaEntity;
import moe.tristan.harudex.model.manga.MangaSearchCriteria;
import moe.tristan.harudex.model.manga.MangaSearchResponse;

public class MangaHttpService implements MangaService {

    private final RestTemplate restTemplate;
    private final HaruDexProperties haruDexProperties;

    public MangaHttpService(RestTemplate restTemplate, HaruDexProperties haruDexProperties) {
        this.restTemplate = restTemplate;
        this.haruDexProperties = haruDexProperties;
    }

    @Override
    public MangaSearchResponse search() {
        return restTemplate.getForObject(haruDexProperties.getBaseUrl() + "/manga", MangaSearchResponse.class);
    }

    @Override
    public MangaSearchResponse search(MangaSearchCriteria criteria) {
        URI uri = UriComponentsBuilder
            .fromHttpUrl(haruDexProperties.getBaseUrl())
            .path("/manga")
            .queryParams(criteria.asQueryParameters())
            .build()
            .toUri();
        return restTemplate.getForObject(uri, MangaSearchResponse.class);
    }

    @Override
    public MangaEntity findById(UUID id) {
        return restTemplate.getForObject(haruDexProperties.getBaseUrl() + "/manga/{uuid}", MangaEntity.class, id);
    }

    @Override
    public MangaEntity create(AuthToken authToken, MangaCreateRequest createRequest) {
        HttpHeaders headers = AuthorizationHeaders.authHeaders(authToken);
        return restTemplate.postForObject(haruDexProperties.getBaseUrl() + "/manga", new HttpEntity<>(createRequest, headers), MangaEntity.class);
    }

}
