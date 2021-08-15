package moe.tristan.harudex.model.author;

import java.util.UUID;

import org.immutables.value.Value.Immutable;

import moe.tristan.harudex.DataClass;
import moe.tristan.harudex.model.common.statics.EntityType;

@Immutable
@DataClass
public interface AuthorDataDefinition {

    UUID getId();

    EntityType getType();

    AuthorAttributes getAttributes();

}
