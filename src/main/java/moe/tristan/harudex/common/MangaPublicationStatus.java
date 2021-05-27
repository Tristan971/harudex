package moe.tristan.harudex.common;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum MangaPublicationStatus {
    ONGOING("ongoing"),
    HIATUS("hiatus"),
    COMPLETE("completed"),
    CANCELLED("cancelled");

    private static final Map<String, MangaPublicationStatus> BY_CODE = Arrays.stream(values()).collect(Collectors.toMap(
        MangaPublicationStatus::getCode,
        Function.identity()
    ));

    private final String code;

    MangaPublicationStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @JsonCreator
    public static MangaPublicationStatus fromString(String value) {
        return requireNonNull(
            BY_CODE.get(value),
            "Unknown code: " + value
        );
    }

}
