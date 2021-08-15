package moe.tristan.harudex.model.author;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.immutables.value.Value.Immutable;
import org.springframework.util.MultiValueMap;

import moe.tristan.harudex.DataClass;
import moe.tristan.harudex.lang.QueryParameters;
import moe.tristan.harudex.model.common.PageableRequest;
import moe.tristan.harudex.model.common.SearchCriteria;
import moe.tristan.harudex.model.common.WithIncludes;
import moe.tristan.harudex.model.common.statics.OrderType;

@Immutable
@DataClass
public interface AuthorSearchCriteriaDefinition extends PageableRequest, SearchCriteria, WithIncludes {

    Optional<List<UUID>> getIds();

    Optional<String> getName();

    Optional<Map<String, OrderType>> getOrder();

    @Override
    default MultiValueMap<String, String> asQueryParameters() {
        Map<String, Optional<?>> properties = new HashMap<>();

        properties.put("ids", getIds());
        properties.put("name", getName());
        properties.put("order", getOrder());

        properties.put("limit", getLimit());
        properties.put("offset", getOffset());

        properties.put("includes", getIncludes());

        return QueryParameters.fromPresentProperties(properties);
    }

}
