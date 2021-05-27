package moe.tristan.harudex.auth;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import moe.tristan.harudex.common.ApiError;
import moe.tristan.harudex.common.Result;

public record LoginResponse(
    @JsonProperty Result result,
    @JsonProperty Token token,
    @JsonProperty List<ApiError> errors
) {}
