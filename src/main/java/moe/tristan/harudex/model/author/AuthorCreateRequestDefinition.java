package moe.tristan.harudex.model.author;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import org.immutables.value.Value.Derived;
import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Parameter;

import com.fasterxml.jackson.annotation.JsonInclude;

import moe.tristan.harudex.DataClass;

@Immutable
@DataClass
@JsonInclude(value = NON_EMPTY, content = NON_EMPTY)
public interface AuthorCreateRequestDefinition {

    @Parameter
    String getName();

    @Derived
    default int getVersion() {
        return 1;
    }

}
