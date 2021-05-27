package moe.tristan.harudex.common;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ApiError(
    @JsonProperty UUID id,
    @JsonProperty int status,
    @JsonProperty String title,
    @JsonProperty String detail,
    @JsonProperty String context
) {}
