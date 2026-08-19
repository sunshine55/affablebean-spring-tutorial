package sunshine55.tutorial.afbb.ws.auth.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import jakarta.inject.Singleton;

@Singleton
public class UserPasswordEncoder {
    private final PasswordEncoder delegate = new BCryptPasswordEncoder();

    public String encode(String rawPassword) {
        return delegate.encode(rawPassword);
    }

    public boolean matches(String rawPassword, String encodedPassword) {
        return delegate.matches(rawPassword, encodedPassword);
    }
}
