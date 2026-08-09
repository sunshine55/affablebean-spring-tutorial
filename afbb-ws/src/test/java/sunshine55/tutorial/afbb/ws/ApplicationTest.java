package sunshine55.tutorial.afbb.ws;

import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import jakarta.inject.Inject;

@MicronautTest
public class ApplicationTest {

    @Inject
    private EmbeddedApplication<?> application;

    @Test
    public void appMain() {
        Assertions.assertTrue(application.isRunning());
    }
}

