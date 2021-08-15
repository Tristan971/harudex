package moe.tristan.harudex.service.author;

import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import moe.tristan.harudex.AuthorService;
import moe.tristan.harudex.HaruDexProperties;
import moe.tristan.harudex.lang.AuthorizationHeaders;
import moe.tristan.harudex.model.auth.AuthToken;
import moe.tristan.harudex.model.author.AuthorCreateRequest;
import moe.tristan.harudex.model.author.AuthorEntity;
import moe.tristan.harudex.model.author.AuthorSearchCriteria;
import moe.tristan.harudex.model.author.AuthorSearchResponse;

public class AuthorHttpService implements AuthorService {

    private final RestTemplate restTemplate;
    private final HaruDexProperties haruDexProperties;

    public AuthorHttpService(RestTemplate restTemplate, HaruDexProperties haruDexProperties) {
        this.restTemplate = restTemplate;
        this.haruDexProperties = haruDexProperties;
    }

    @Override
    public AuthorEntity create(AuthToken authToken, AuthorCreateRequest createRequest) {
        HttpHeaders headers = AuthorizationHeaders.authHeaders(authToken);
        return restTemplate.postForObject(haruDexProperties.getBaseUrl() + "/author", new HttpEntity<>(createRequest, headers), AuthorEntity.class);
    }

    @Override
    public AuthorSearchResponse search(AuthorSearchCriteria criteria) {
        URI uri = UriComponentsBuilder
            .fromHttpUrl(haruDexProperties.getBaseUrl())
            .path("/author")
            .queryParams(criteria.asQueryParameters())
            .build()
            .toUri();
        return restTemplate.getForObject(uri, AuthorSearchResponse.class);
    }

}
