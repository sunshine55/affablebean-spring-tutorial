package sunshine55.tutorial.afbb.ws.auth.controller;

import java.security.Principal;
import java.util.Optional;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.Authenticator;
import io.micronaut.security.authentication.UsernamePasswordCredentials;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.security.token.generator.AccessRefreshTokenGenerator;
import io.micronaut.security.token.render.AccessRefreshToken;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.validation.Validated;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import sunshine55.tutorial.afbb.ws.auth.dto.LoginRequest;
import sunshine55.tutorial.afbb.ws.auth.dto.RefreshRequest;
import sunshine55.tutorial.afbb.ws.auth.dao.RefreshTokenDao;
import sunshine55.tutorial.afbb.ws.auth.dao.SystemUserDao;
import sunshine55.tutorial.afbb.ws.auth.entity.RefreshTokenEntity;
import sunshine55.tutorial.afbb.ws.auth.entity.SystemUserEntity;

@Controller("/auth")
@Secured(SecurityRule.IS_ANONYMOUS)
@Validated
@ExecuteOn(TaskExecutors.BLOCKING)
@RequiredArgsConstructor
public class AuthController {
    private final SystemUserDao systemUserDao;
    private final RefreshTokenDao refreshTokenDao;
    private final Authenticator<Object> authenticator;
    private final AccessRefreshTokenGenerator accessRefreshTokenGenerator;

    @Post("/login")
    public HttpResponse<?> login(@Body LoginRequest request) {
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(request.getUsername(), request.getPassword());
        AuthenticationResponse response = Flux.from(authenticator.authenticate(null, credentials)).blockFirst();
        if (!response.isAuthenticated()) {
            return HttpResponse.status(HttpStatus.UNAUTHORIZED);
        }
        Optional<Authentication> authOpt = response.getAuthentication();
        if (authOpt.isEmpty()) {
            return HttpResponse.status(HttpStatus.UNAUTHORIZED);
        }
        Optional<AccessRefreshToken> tokenOpt = accessRefreshTokenGenerator.generate(authOpt.get());
        if (tokenOpt.isEmpty()) {
            return HttpResponse.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return HttpResponse.ok(tokenOpt.get());
    }

    @Get("/me")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    public HttpResponse<SystemUserEntity> me(Principal principal) {
        Optional<SystemUserEntity> user = systemUserDao.findByUsername(principal.getName());
        return user.map(u -> {
            u.setPassword(null);
            return HttpResponse.ok(u);
        }).orElse(HttpResponse.notFound());
    }

    @Post("/logout")
    public HttpResponse<Void> logout(@Body RefreshRequest request) {
        Optional<RefreshTokenEntity> tokenOpt = refreshTokenDao.findByRefreshToken(request.getRefreshToken());
        if (tokenOpt.isPresent()) {
            RefreshTokenEntity token = tokenOpt.get();
            token.setRevoked(true);
            refreshTokenDao.update(token);
        }
        return HttpResponse.ok();
    }
}

