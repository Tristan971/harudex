package moe.tristan.harudex.model.common.statics;

import java.util.Locale;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EntityType {
    MANGA,
    CHAPTER,
    COVER_ART,
    AUTHOR,
    ARTIST,
    SCANLATION_GROUP,
    TAG,
    USER,
    CUSTOM_LIST;

    @JsonValue
    String toJson() {
        return name().toLowerCase(Locale.ROOT);
    }

    @JsonCreator
    static EntityType fromJson(String value) {
        return valueOf(value.toUpperCase(Locale.ROOT));
    }

}
