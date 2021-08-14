package moe.tristan.harudex.model.common;

import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Parameter;

import moe.tristan.harudex.DataClass;

@Immutable
@DataClass
interface PageDefinition {

    @Parameter
    int getLimit();

    @Parameter
    int getOffset();

}
