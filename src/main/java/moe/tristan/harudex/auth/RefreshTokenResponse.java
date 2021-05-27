package moe.tristan.harudex.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

import moe.tristan.harudex.common.Result;

public record RefreshTokenResponse(
    @JsonProperty Result result,
    @JsonProperty Token token,
    @JsonProperty String message
) {}
