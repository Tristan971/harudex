package moe.tristan.harudex.model.auth;

import org.immutables.value.Value.Immutable;

import moe.tristan.harudex.DataClass;
import moe.tristan.harudex.model.common.WithResult;

@Immutable
@DataClass
public interface AuthLoginResponseDefinition extends WithResult {

    AuthToken getToken();

}
