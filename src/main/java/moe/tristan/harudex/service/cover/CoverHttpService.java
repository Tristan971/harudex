package moe.tristan.harudex.service.cover;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import moe.tristan.harudex.CoverService;
import moe.tristan.harudex.model.cover.CoverEntity;
import moe.tristan.harudex.model.cover.CoverSearchResponse;

@Service
public class CoverHttpService implements CoverService {

    private final RestTemplate restTemplate;

    public CoverHttpService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public CoverEntity findById(UUID id) {
        return restTemplate.getForObject("/cover/{coverId}", CoverEntity.class, id);
    }

    @Override
    public CoverSearchResponse search() {
        return restTemplate.getForObject("/cover", CoverSearchResponse.class);
    }

    @Override
    public CoverEntity upload(UUID mangaId, InputStream content) {
        try {
            return restTemplate.postForObject("/cover/{mangaId}", content.readAllBytes(), CoverEntity.class, mangaId);
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot read bytes from cover image input stream", e);
        }
    }

}
