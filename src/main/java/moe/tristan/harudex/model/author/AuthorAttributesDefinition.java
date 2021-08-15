package moe.tristan.harudex.model.author;

import java.net.URL;
import java.time.ZonedDateTime;
import java.util.Optional;

import org.immutables.value.Value.Immutable;

import moe.tristan.harudex.DataClass;

@Immutable
@DataClass
public interface AuthorAttributesDefinition {

    String getName();

    Optional<URL> getImageUrl();

    //array/object memery isn't manageable in a typed language, alas
    //Map<String, String> getBiography();

    ZonedDateTime getCreatedAt();

    ZonedDateTime getUpdatedAt();

    int getVersion();

}
