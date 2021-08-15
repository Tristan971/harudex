package moe.tristan.harudex.model.auth;

import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Parameter;

import moe.tristan.harudex.DataClass;

@Immutable
@DataClass
public interface AuthLoginRequestDefinition {

    @Parameter
    String getUsername();

    @Parameter
    String getPassword();

}
