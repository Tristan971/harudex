package moe.tristan.harudex.model.common;

import org.immutables.value.Value.Derived;
import org.springframework.util.MultiValueMap;

public interface SearchCriteria {

    @Derived
    default MultiValueMap<String, String> asQueryParameters() {
        throw new UnsupportedOperationException("Query parameter use not implemented!");
    }

}
