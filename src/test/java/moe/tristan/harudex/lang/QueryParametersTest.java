package moe.tristan.harudex.lang;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.util.MultiValueMap;

class QueryParametersTest {

    @Test
    void escapeAmpersand() {
        MultiValueMap<String, String> props = QueryParameters.fromPresentProperties(Map.of("name", Optional.of("DXD A&C")));
        assertThat(props.get("name")).containsExactly("DXD A C");
    }

}
