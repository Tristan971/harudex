package moe.tristan.harudex;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties("moe.tristan.harudex")
public record HaruDexProperties(
    String baseUrl,
    String username,
    String password
) {

    @Override
    public String baseUrl() {
        return baseUrl == null ? "https://api.mangadex.org" : baseUrl;
    }

}
