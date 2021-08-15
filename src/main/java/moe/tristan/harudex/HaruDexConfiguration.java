package moe.tristan.harudex;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import moe.tristan.harudex.service.auth.AuthHttpService;
import moe.tristan.harudex.service.author.AuthorHttpService;
import moe.tristan.harudex.service.cover.CoverHttpService;
import moe.tristan.harudex.service.manga.MangaHttpService;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(HaruDexProperties.class)
public class HaruDexConfiguration {

    private final RestTemplate restTemplate;
    private final HaruDexProperties haruDexProperties;

    public HaruDexConfiguration(RestTemplate restTemplate, HaruDexProperties haruDexProperties) {
        this.restTemplate = restTemplate;
        this.haruDexProperties = haruDexProperties;
    }

    @Bean
    public AuthService authService() {
        return new AuthHttpService(restTemplate, haruDexProperties);
    }

    @Bean
    public AuthorService authorService() {
        return new AuthorHttpService(restTemplate, haruDexProperties);
    }

    @Bean
    public MangaService mangaService() {
        return new MangaHttpService(restTemplate, haruDexProperties);
    }

    @Bean
    public CoverService coverService() {
        return new CoverHttpService(restTemplate, haruDexProperties);
    }

}
