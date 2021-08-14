package moe.tristan.harudex.model.common.statics;

import java.util.Locale;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum OriginalLanguage {
    JA,
    KO,
    CN;

    @JsonValue
    String toJson() {
        return name().toLowerCase(Locale.ROOT);
    }

    @JsonCreator
    static OriginalLanguage fromJson(String value) {
        return valueOf(value.toUpperCase(Locale.ROOT));
    }

}
