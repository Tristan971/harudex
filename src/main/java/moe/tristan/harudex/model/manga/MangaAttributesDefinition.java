package moe.tristan.harudex.model.manga;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.immutables.value.Value.Immutable;

import com.fasterxml.jackson.annotation.JsonProperty;

import moe.tristan.harudex.DataClass;
import moe.tristan.harudex.model.common.statics.ContentRating;
import moe.tristan.harudex.model.common.statics.PublicationStatus;

@Immutable
@DataClass
interface MangaAttributesDefinition {

    Map<String, String> getTitle();

    List<Map<String, String>> getAltTitles();

    Map<String, String> getDescription();

    Map<String, String> getLinks();

    Locale getOriginalLanguage();

    Optional<String> getLastVolume();

    Optional<String> getLastChapter();

    @JsonProperty("status")
    PublicationStatus getPublicationStatus();

    @JsonProperty("year")
    Optional<Integer> getReleaseYear();

    ContentRating getContentRating();

    // todo: tags

    ZonedDateTime getCreatedAt();

    ZonedDateTime getUpdatedAt();

    int getVersion();

}
