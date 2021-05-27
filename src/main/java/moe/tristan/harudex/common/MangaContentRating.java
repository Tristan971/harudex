package moe.tristan.harudex.common;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum MangaContentRating {
    SAFE("safe"),
    SUGGESTIVE("suggestive"),
    EROTICA("erotica"),
    PORNOGRAPHIC("pornographic");

    private static final Map<String, MangaContentRating> BY_CODE = Arrays.stream(values()).collect(Collectors.toMap(
        MangaContentRating::getCode,
        Function.identity()
    ));

    private final String code;

    MangaContentRating(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @JsonCreator
    public static MangaContentRating fromString(String value) {
        return requireNonNull(
            BY_CODE.get(value),
            "Unknown code: " + value
        );
    }

}
