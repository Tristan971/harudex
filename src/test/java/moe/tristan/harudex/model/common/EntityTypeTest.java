package moe.tristan.harudex.model.common;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import moe.tristan.harudex.JacksonTest;
import moe.tristan.harudex.model.common.statics.EntityType;

@JacksonTest
class EntityTypeTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void snakeCase() throws JsonProcessingException {
        assertThat(objectMapper.writeValueAsString(EntityType.SCANLATION_GROUP)).isEqualTo("\"scanlation_group\"");
    }

}
