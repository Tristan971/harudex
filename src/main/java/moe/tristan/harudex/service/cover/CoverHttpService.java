package moe.tristan.harudex.service.cover;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import moe.tristan.harudex.CoverService;
import moe.tristan.harudex.HaruDexProperties;
import moe.tristan.harudex.lang.AuthorizationHeaders;
import moe.tristan.harudex.model.auth.AuthToken;
import moe.tristan.harudex.model.cover.CoverEntity;
import moe.tristan.harudex.model.cover.CoverSearchResponse;

public class CoverHttpService implements CoverService {

    private final RestTemplate restTemplate;
    private final HaruDexProperties haruDexProperties;

    public CoverHttpService(RestTemplate restTemplate, HaruDexProperties haruDexProperties) {
        this.restTemplate = restTemplate;
        this.haruDexProperties = haruDexProperties;
    }

    @Override
    public CoverEntity findById(UUID id) {
        return restTemplate.getForObject(haruDexProperties.getBaseUrl() + "/cover/{coverId}", CoverEntity.class, id);
    }

    @Override
    public CoverSearchResponse search() {
        return restTemplate.getForObject(haruDexProperties.getBaseUrl() + "/cover", CoverSearchResponse.class);
    }

    @Override
    public CoverEntity upload(AuthToken authToken, UUID mangaId, InputStream content) {
        try {
            HttpHeaders authorization = AuthorizationHeaders.authHeaders(authToken);
            return restTemplate.postForObject(
                haruDexProperties.getBaseUrl() + "/cover/{mangaId}",
                new HttpEntity<>(content.readAllBytes(), authorization),
                CoverEntity.class,
                mangaId
            );
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot read bytes from cover image input stream", e);
        }
    }

}
