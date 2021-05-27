package moe.tristan.harudex.common;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum CustomListVisibility {
    PUBLIC("public"),
    PRIVATE("private");

    private static final Map<String, CustomListVisibility> BY_CODE = Arrays.stream(values()).collect(Collectors.toMap(
        CustomListVisibility::getCode,
        Function.identity()
    ));

    private final String code;

    CustomListVisibility(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @JsonCreator
    public static CustomListVisibility fromString(String value) {
        return requireNonNull(
            BY_CODE.get(value),
            "Unknown code: " + value
        );
    }

}
