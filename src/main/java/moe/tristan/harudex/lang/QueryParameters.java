package moe.tristan.harudex.lang;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import moe.tristan.harudex.model.common.CodedEnumeration;

public final class QueryParameters {

    private QueryParameters() {
    }

    public static MultiValueMap<String, String> fromPresentProperties(Map<String, Optional<?>> properties) {
        LinkedMultiValueMap<String, String> queryParameters = new LinkedMultiValueMap<>();

        properties.forEach((name, maybeValue) -> {
            if (maybeValue.isEmpty()) {
                return;
            }

            var value = maybeValue.get();

            if (value instanceof Collection<?> coll) {
                if (coll.isEmpty()) {
                    return;
                }

                queryParameters.put(name + "[]", coll.stream().map(QueryParameters::externalize).toList());
            } else if (value instanceof Map<?,?> m) {
                m.forEach((key, val) -> queryParameters.put(
                    name + "[" + externalize(key) + "]",
                    List.of(externalize(val))
                ));
            } else {
                queryParameters.put(name, List.of(externalize(value)));
            }
        });

        return queryParameters;
    }

    private static String externalize(Object o) {
        if (o instanceof CodedEnumeration ce) {
            return ce.getCode();
        }

        if (o instanceof Enum member) {
            return member.name().toLowerCase(Locale.ROOT);
        }

        return String.valueOf(o);
    }

}
