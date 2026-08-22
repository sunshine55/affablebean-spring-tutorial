package sunshine55.tutorial.afbb.ws.auth.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Serdeable
public class RefreshRequest {
    private String refreshToken;
}
