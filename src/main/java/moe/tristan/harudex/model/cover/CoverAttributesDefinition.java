package moe.tristan.harudex.model.cover;

import java.time.ZonedDateTime;
import java.util.Optional;

import org.immutables.value.Value.Immutable;

import moe.tristan.harudex.DataClass;

@Immutable
@DataClass
public interface CoverAttributesDefinition {

    Optional<String> getVolume();

    String getFileName();

    int getVersion();

    ZonedDateTime getCreatedAt();

    ZonedDateTime getUpdatedAt();

}
