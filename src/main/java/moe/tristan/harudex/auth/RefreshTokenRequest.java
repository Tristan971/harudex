package moe.tristan.harudex.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RefreshTokenRequest(
    @JsonProperty("token") String refreshToken
) {}
