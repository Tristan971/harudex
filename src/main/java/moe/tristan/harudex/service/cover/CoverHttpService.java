package moe.tristan.harudex.service.cover;

import java.util.UUID;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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
    public CoverEntity upload(AuthToken authToken, UUID mangaId, byte[] content) {
        HttpHeaders headers = AuthorizationHeaders.authHeaders(authToken);
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new ByteArrayResource(content) {
            @Override
            public String getFilename() {
                return "cover";
            }
        });

        return restTemplate.postForObject(
            haruDexProperties.getBaseUrl() + "/cover/{mangaId}",
            new HttpEntity<>(body, headers),
            CoverEntity.class,
            mangaId
        );
    }

}
