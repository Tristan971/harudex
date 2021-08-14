package moe.tristan.harudex.model.common;

import java.util.UUID;

import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Parameter;

import moe.tristan.harudex.DataClass;
import moe.tristan.harudex.model.common.statics.EntityType;

@Immutable
@DataClass
interface RelationshipDefinition {

    @Parameter
    EntityType getType();

    @Parameter
    UUID getId();

}
