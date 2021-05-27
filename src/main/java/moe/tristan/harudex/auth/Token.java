package moe.tristan.harudex.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Token(
    @JsonProperty String session,
    @JsonProperty String refresh
) {}
