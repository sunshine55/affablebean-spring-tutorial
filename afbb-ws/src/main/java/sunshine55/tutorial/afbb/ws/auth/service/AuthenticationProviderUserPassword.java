package sunshine55.tutorial.afbb.ws.auth.service;

import java.time.LocalDateTime;
import java.util.Optional;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.AuthenticationFailureReason;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.provider.HttpRequestAuthenticationProvider;
import jakarta.inject.Singleton;
import sunshine55.tutorial.afbb.ws.auth.dao.SystemUserDao;
import sunshine55.tutorial.afbb.ws.auth.entity.SystemUserEntity;

@Singleton
class AuthenticationProviderUserPassword<B> implements HttpRequestAuthenticationProvider<B> {
    private final SystemUserDao systemUserDao;
    private final UserPasswordEncoder userPasswordEncoder;

    AuthenticationProviderUserPassword(SystemUserDao systemUserDao, UserPasswordEncoder userPasswordEncoder) {
        this.systemUserDao = systemUserDao;
        this.userPasswordEncoder = userPasswordEncoder;
    }

    @Override
    public AuthenticationResponse authenticate(
            @Nullable HttpRequest<B> httpRequest,
            @NonNull AuthenticationRequest<String, String> authenticationRequest) {

        String username = authenticationRequest.getIdentity();
        Optional<SystemUserEntity> userOpt = systemUserDao.findByUsername(username);

        if (userOpt.isEmpty()) {
            return AuthenticationResponse.failure(AuthenticationFailureReason.USER_NOT_FOUND);
        }

        SystemUserEntity user = userOpt.get();

        if (!Boolean.TRUE.equals(user.getActive())) {
            return AuthenticationResponse.failure(AuthenticationFailureReason.USER_DISABLED);
        }

        if (!userPasswordEncoder.matches(authenticationRequest.getSecret(), user.getPassword())) {
            return AuthenticationResponse.failure(AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH);
        }

        user.setLastLoginAt(LocalDateTime.now());
        systemUserDao.update(user);

        return AuthenticationResponse.success(username);
    }
}
