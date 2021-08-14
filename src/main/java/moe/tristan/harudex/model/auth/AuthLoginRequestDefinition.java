package moe.tristan.harudex.model.auth;

import java.util.Optional;

import org.immutables.value.Value.Check;
import org.immutables.value.Value.Immutable;

import moe.tristan.harudex.DataClass;

@Immutable
@DataClass
public interface AuthLoginRequestDefinition {

    Optional<String> getUsername();

    Optional<String> getEmail();

    String getPassword();

    @Check
    default void check() {
        if (getUsername().isEmpty() && getEmail().isEmpty()) {
            throw new IllegalArgumentException("One of username or email must be defined, but none were set!");
        }
    }

}
