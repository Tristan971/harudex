package moe.tristan.harudex.model.manga;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.immutables.value.Value.Immutable;
import org.springframework.util.MultiValueMap;

import moe.tristan.harudex.DataClass;
import moe.tristan.harudex.lang.QueryParameters;
import moe.tristan.harudex.model.common.PageableRequest;
import moe.tristan.harudex.model.common.statics.BinaryOperationType;
import moe.tristan.harudex.model.common.statics.ContentRating;
import moe.tristan.harudex.model.common.statics.OrderType;
import moe.tristan.harudex.model.common.statics.OriginalLanguage;
import moe.tristan.harudex.model.common.statics.PublicationDemographic;
import moe.tristan.harudex.model.common.statics.PublicationStatus;

@Immutable
@DataClass
interface MangaSearchCriteriaDefinition extends PageableRequest {

    Optional<String> getTitle();

    Optional<Set<UUID>> getIds();

    Optional<Set<UUID>> getAuthors();

    Optional<Set<UUID>> getArtists();

    Optional<Integer> getYear();

    Optional<Set<UUID>> getIncludedTags();

    Optional<BinaryOperationType> getIncludedTagsMode();

    Optional<Set<PublicationStatus>> getStatuses();

    Optional<Set<OriginalLanguage>> getOriginalLanguage();

    Optional<Set<PublicationDemographic>> getPublicationDemographics();

    Optional<Set<ContentRating>> getContentRatings();

    Optional<LocalDateTime> getCreatedAtSince();

    Optional<LocalDateTime> getUpdatedAtSince();

    Optional<Map<String, OrderType>> getOrdering();

    Optional<Set<String>> getIncludes();

    default MultiValueMap<String, String> asQueryParameters() {
        HashMap<String, Optional<?>> properties = new HashMap<>();

        properties.put("title", getTitle());
        properties.put("ids", getIds());
        properties.put("authors", getAuthors());
        properties.put("artists", getArtists());
        properties.put("year", getYear());
        properties.put("includedTags", getIncludedTags());
        properties.put("includedTagsMode", getIncludedTagsMode());
        properties.put("status", getStatuses());
        properties.put("originalLanguage", getOriginalLanguage());
        properties.put("publicationDemographic", getPublicationDemographics());
        properties.put("contentRating", getContentRatings());
        properties.put("createdAtSince", getCreatedAtSince());
        properties.put("updatedAtSince", getUpdatedAtSince());
        properties.put("order", getOrdering());
        properties.put("includes", getIncludes());
        properties.put("limit", getLimit());
        properties.put("offset", getOffset());

        return QueryParameters.fromPresentProperties(properties);
    }

}
