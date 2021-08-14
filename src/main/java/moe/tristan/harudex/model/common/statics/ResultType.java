package moe.tristan.harudex.model.common.statics;

import java.util.Locale;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ResultType {
    OK,
    KO;

    @JsonValue
    String toJson() {
        return name().toUpperCase(Locale.ROOT);
    }

    @JsonCreator
    static ResultType fromJson(String value) {
        return valueOf(value.toUpperCase(Locale.ROOT));
    }

}
