package base.auth.config;

import io.quarkus.arc.config.ConfigProperties;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ConfigProperties(prefix = "bpe.root")
public interface UsuarioRootConfig {

    @ConfigProperty(name = "login", defaultValue = "root")
    String login();

    @ConfigProperty(name = "passwd", defaultValue = "@Bp&_2019")
    String senha();


}
