package moe.tristan.harudex.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LoginRequest(

    @JsonProperty
    String username,

    @JsonProperty
    String password

) {}
