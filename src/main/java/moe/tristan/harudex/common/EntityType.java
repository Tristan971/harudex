package moe.tristan.harudex.common;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum EntityType {
    MANGA("manga"),
    CHAPTER("chapter"),
    COVER_ART("cover_art"),
    AUTHOR("author"),
    ARTIST("artist"),
    SCANLATION_GROUP("scanlation_group"),
    TAG("tag"),
    USER("user"),
    CUSTOM_LIST("custom_list");

    private static final Map<String, EntityType> BY_CODE = Arrays.stream(values()).collect(Collectors.toMap(
        EntityType::getCode,
        Function.identity()
    ));

    private final String code;

    EntityType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @JsonCreator
    public static EntityType fromString(String value) {
        return requireNonNull(
            BY_CODE.get(value),
            "Unknown code: " + value
        );
    }

}
