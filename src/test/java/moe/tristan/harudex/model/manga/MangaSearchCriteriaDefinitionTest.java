package moe.tristan.harudex.model.manga;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import moe.tristan.harudex.JacksonTest;

@JacksonTest
class MangaSearchCriteriaDefinitionTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void empty() {
        MangaSearchCriteria emptyCriteria = MangaSearchCriteria.builder().build();

        Map<String, ?> json = objectMapper.convertValue(emptyCriteria, new TypeReference<>() { });
        assertThat(json).isEmpty();
    }

}
