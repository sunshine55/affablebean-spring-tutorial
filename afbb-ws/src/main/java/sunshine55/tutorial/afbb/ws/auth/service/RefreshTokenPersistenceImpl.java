package sunshine55.tutorial.afbb.ws.auth.service;

import java.time.LocalDateTime;
import java.util.Optional;

import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.errors.OauthErrorResponseException;
import io.micronaut.security.token.event.RefreshTokenGeneratedEvent;
import io.micronaut.security.token.refresh.RefreshTokenPersistence;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import sunshine55.tutorial.afbb.ws.auth.dao.RefreshTokenDao;
import sunshine55.tutorial.afbb.ws.auth.entity.RefreshTokenEntity;

import static io.micronaut.security.errors.IssuingAnAccessTokenErrorCode.INVALID_GRANT;

@Singleton
@RequiredArgsConstructor
public class RefreshTokenPersistenceImpl implements RefreshTokenPersistence {
    private final RefreshTokenDao refreshTokenDao;

    @Override
    public void persistToken(RefreshTokenGeneratedEvent event) {
        if (event != null && event.getRefreshToken() != null && event.getAuthentication() != null) {
            RefreshTokenEntity entity = new RefreshTokenEntity();
            entity.setRefreshToken(event.getRefreshToken());
            entity.setUsername(event.getAuthentication().getName());
            entity.setRevoked(false);
            entity.setCreatedAt(LocalDateTime.now());
            refreshTokenDao.save(entity);
        }
    }

    @Override
    public Publisher<Authentication> getAuthentication(String refreshToken) {
        return Flux.create(emitter -> {
            Optional<RefreshTokenEntity> tokenOpt = refreshTokenDao.findByRefreshToken(refreshToken);
            if (tokenOpt.isPresent()) {
                RefreshTokenEntity token = tokenOpt.get();
                if (Boolean.TRUE.equals(token.getRevoked())) {
                    emitter.error(new OauthErrorResponseException(INVALID_GRANT, "refresh token revoked", null));
                } else {
                    emitter.next(Authentication.build(token.getUsername()));
                    emitter.complete();
                }
            } else {
                emitter.error(new OauthErrorResponseException(INVALID_GRANT, "refresh token not found", null));
            }
        }, FluxSink.OverflowStrategy.ERROR);
    }
}
