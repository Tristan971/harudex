package moe.tristan.harudex;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import moe.tristan.harudex.auth.Token;

public class HaruDexContext {

    private final HaruDexProperties haruDexProperties;

    private final AtomicReference<Token> currentToken = new AtomicReference<>();

    public HaruDexContext(HaruDexProperties haruDexProperties) {
        this.haruDexProperties = haruDexProperties;
    }

    public String getMangadexApiUrl() {
        return haruDexProperties.baseUrl();
    }

    public Optional<Token> getCurrentToken() {
        return Optional.ofNullable(currentToken.get());
    }

    public void setCurrentToken(Token token) {
        currentToken.set(token);
    }

}
