package moe.tristan.harudex;

import moe.tristan.harudex.model.auth.AuthCheckTokenResponse;
import moe.tristan.harudex.model.auth.AuthLoginRequest;
import moe.tristan.harudex.model.auth.AuthLoginResponse;
import moe.tristan.harudex.model.auth.AuthLogoutResponse;
import moe.tristan.harudex.model.auth.AuthRefreshTokenRequest;
import moe.tristan.harudex.model.auth.AuthRefreshTokenResponse;

public interface AuthService {

    AuthLoginResponse login(AuthLoginRequest authLoginRequest);

    AuthCheckTokenResponse checkToken();

    default AuthCheckTokenResponse status() {
        return checkToken();
    }

    AuthLogoutResponse logout();

    AuthRefreshTokenResponse refreshToken(AuthRefreshTokenRequest refreshTokenRequest);

}
