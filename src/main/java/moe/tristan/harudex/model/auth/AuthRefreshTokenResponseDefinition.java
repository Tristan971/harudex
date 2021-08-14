package moe.tristan.harudex.model.auth;

import java.util.Optional;

import org.immutables.value.Value.Immutable;

import moe.tristan.harudex.DataClass;
import moe.tristan.harudex.model.common.WithResult;

@Immutable
@DataClass
public interface AuthRefreshTokenResponseDefinition extends WithResult {

    AuthToken getToken();

    Optional<String> getMessage();

}
