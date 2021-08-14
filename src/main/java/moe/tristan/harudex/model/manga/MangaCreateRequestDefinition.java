package moe.tristan.harudex.model.manga;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.immutables.value.Value.Derived;
import org.immutables.value.Value.Immutable;

import moe.tristan.harudex.DataClass;
import moe.tristan.harudex.model.common.statics.ContentRating;
import moe.tristan.harudex.model.common.statics.PublicationDemographic;
import moe.tristan.harudex.model.common.statics.PublicationStatus;

@Immutable
@DataClass
public interface MangaCreateRequestDefinition {

    Map<String, String> getTitle();

    List<Map<String, String>> getAltTitles();

    Map<String, String> getDescription();

    List<UUID> getAuthors();

    List<UUID> getArtists();

    Map<String, String> getLinks();

    Locale getOriginalLanguage();

    Optional<Integer> getLastVolume();

    Optional<Integer> getLastChapter();

    Optional<PublicationDemographic> getPublicationDemographic();

    Optional<PublicationStatus> getPublicationStatus();

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
