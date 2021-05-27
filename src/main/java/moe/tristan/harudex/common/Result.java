package moe.tristan.harudex.common;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Result {
    OK("ok"),
    ERROR("error");

    private static final Map<String, Result> BY_CODE = Arrays
        .stream(values())
        .collect(Collectors.toMap(Result::getCode, Function.identity()));

    private final String code;

    Result(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @JsonCreator
    public static Result fromString(String value) {
        return requireNonNull(
            BY_CODE.get(value),
            "Unknown code: " + value
        );
    }

}
