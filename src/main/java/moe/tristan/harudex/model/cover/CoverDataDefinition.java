package moe.tristan.harudex.model.cover;

import java.util.UUID;

import org.immutables.value.Value.Immutable;

import moe.tristan.harudex.DataClass;
import moe.tristan.harudex.model.common.statics.EntityType;

@Immutable
@DataClass
public interface CoverDataDefinition {

    UUID getId();

    EntityType getType();

    CoverAttributes getAttributes();

}
