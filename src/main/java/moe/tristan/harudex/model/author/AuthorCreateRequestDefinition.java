package moe.tristan.harudex.model.author;

import org.immutables.value.Value.Derived;
import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Parameter;

import moe.tristan.harudex.DataClass;

@Immutable
@DataClass
public interface AuthorCreateRequestDefinition {

    @Parameter
    String getName();

    @Derived
    default int getVersion() {
        return 1;
    }

}
