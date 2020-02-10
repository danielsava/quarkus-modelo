package base;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.TimeZone;

@ApplicationScoped
public class StartStopEvent {

    @Inject
    Logger log;

    void onStart(@Observes StartupEvent startupEvent) {
        log.info(" # Inicializada: " + LocalDateTime.now());
        log.info(" # TimeZone: " + System.getProperty("user.timezone")); // Default é 'America/Sao_Paulo '

        /** Correção do TimeZone (Estava dando erro de 1 hora. Acredito que seja por causa da bagunça do fim do horário de verão 2019) */
        TimeZone.setDefault(TimeZone.getTimeZone("GMT-3"));
        log.info(" # Inicialidada (corrigido): " + LocalDateTime.now());
        log.info(" # TimeZone     (corrigido): " + System.getProperty("user.timezone")); // Default é 'America/Sao_Paulo '
    }

    void onStop(@Observes ShutdownEvent shutdownEvent) {
        log.info(" Finalizando Aplicacao: " + LocalDateTime.now());
    }


}
