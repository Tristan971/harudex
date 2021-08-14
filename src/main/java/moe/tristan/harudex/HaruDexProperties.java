package moe.tristan.harudex;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("moe.tristan.harudex")
public class HaruDexProperties {

    private String baseUrl = "https://api.mangadex.org";
    private String username = null;
    private String password = null;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
