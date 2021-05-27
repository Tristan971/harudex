package moe.tristan.harudex.common;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum MangaReadingStatus {
    PLAN_TO_READ("plan_to_read"),
    READING("reading"),
    ON_HOLD("on_hold"),
    DROPPED("dropped"),
    COMPLETED("completed"),
    RE_READING("re_reading");

    private static final Map<String, MangaReadingStatus> BY_CODE = Arrays.stream(values()).collect(Collectors.toMap(
        MangaReadingStatus::getCode,
        Function.identity()
    ));

    private final String code;

    MangaReadingStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @JsonCreator
    public static MangaReadingStatus fromString(String value) {
        return requireNonNull(
            BY_CODE.get(value),
            "Unknown code: " + value
        );
    }

}
