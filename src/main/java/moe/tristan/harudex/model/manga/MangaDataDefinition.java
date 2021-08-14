package moe.tristan.harudex.model.manga;

import java.util.UUID;

import org.immutables.value.Value.Derived;
import org.immutables.value.Value.Immutable;

import moe.tristan.harudex.DataClass;
import moe.tristan.harudex.model.common.statics.EntityType;

@Immutable
@DataClass
interface MangaDataDefinition {

    UUID getId();

    @Derived
    default EntityType getType() {
        return EntityType.MANGA;
    }

    MangaAttributes getAttributes();

}
