package moe.tristan.harudex.auth;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import moe.tristan.harudex.common.ApiError;
import moe.tristan.harudex.common.Result;

public record LogoutResponse(
    @JsonProperty Result result,
    @JsonProperty List<ApiError> errors
) {}
