package moe.tristan.harudex.model.manga;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.immutables.value.Value.Derived;
import org.immutables.value.Value.Immutable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import moe.tristan.harudex.DataClass;
import moe.tristan.harudex.model.common.statics.ContentRating;
import moe.tristan.harudex.model.common.statics.PublicationDemographic;
import moe.tristan.harudex.model.common.statics.PublicationStatus;

@Immutable
@DataClass
@JsonInclude(value = NON_EMPTY, content = NON_EMPTY)
public interface MangaCreateRequestDefinition {

    Map<String, String> getTitle();

    List<Map<String, String>> getAltTitles();

    Map<String, String> getDescription();

    List<UUID> getAuthors();

    List<UUID> getArtists();

    Map<String, String> getLinks();

    String getOriginalLanguage();

    Optional<Integer> getLastVolume();

    Optional<Integer> getLastChapter();

    Optional<PublicationDemographic> getPublicationDemographic();

    @JsonProperty("status")
    PublicationStatus getPublicationStatus();

    Optional<Integer> getReleaseYear();

    ContentRating getContentRating();

    List<UUID> getTags();

    @Derived
    default Optional<String> getModNodes() {
        return Optional.empty();
    }

    @Derived
    default int getVersion() {
        return 1;
    }

}
