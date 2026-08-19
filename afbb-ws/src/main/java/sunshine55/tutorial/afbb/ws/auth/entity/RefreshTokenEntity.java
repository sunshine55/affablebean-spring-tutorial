package sunshine55.tutorial.afbb.ws.auth.entity;

import java.time.LocalDateTime;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@MappedEntity("refresh_token")
@Getter
@Setter
@EqualsAndHashCode
public class RefreshTokenEntity {
    @Id
    @GeneratedValue
    private String id;

    private String refreshToken;
    private String username;
    private Boolean revoked;
    private LocalDateTime createdAt;
}
