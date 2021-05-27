package moe.tristan.harudex.common;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum MangaPublicationDemographic {
    SHOUNEN("shounen"),
    SHOUJO("shoujo"),
    JOSEI("josei"),
    SEINEN("seinen");

    private static final Map<String, MangaPublicationDemographic> BY_CODE = Arrays.stream(values()).collect(Collectors.toMap(
        MangaPublicationDemographic::getCode,
        Function.identity()
    ));

    private final String code;

    MangaPublicationDemographic(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @JsonCreator
    public static MangaPublicationDemographic fromString(String value) {
        return requireNonNull(
            BY_CODE.get(value),
            "Unknown code: " + value
        );
    }

}
