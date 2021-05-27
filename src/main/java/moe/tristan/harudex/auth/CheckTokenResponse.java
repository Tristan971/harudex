package moe.tristan.harudex.auth;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import moe.tristan.harudex.common.Result;

public record CheckTokenResponse(
    @JsonProperty Result ok,
    @JsonProperty boolean isAuthenticated,
    @JsonProperty List<String> roles,
    @JsonProperty List<String> permissions
) {}
