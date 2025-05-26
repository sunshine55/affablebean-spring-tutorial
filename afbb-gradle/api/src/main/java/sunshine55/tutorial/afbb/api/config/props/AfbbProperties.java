package sunshine55.tutorial.afbb.api.config.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "afbb")
@Getter @Setter
public class AfbbProperties {
    private Cors cors;

    @Getter @Setter
    public static class Cors {
        private String allowedOrigins;
    }
}
