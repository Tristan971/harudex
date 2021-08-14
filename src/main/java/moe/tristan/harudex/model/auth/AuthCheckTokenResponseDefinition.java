package moe.tristan.harudex.model.auth;

import java.util.List;

import org.immutables.value.Value.Immutable;

import moe.tristan.harudex.DataClass;
import moe.tristan.harudex.model.common.WithResult;

@Immutable
@DataClass
public interface AuthCheckTokenResponseDefinition extends WithResult {

    boolean isAuthenticated();

    List<String> getRoles();

    List<String> getPermissions();

}
