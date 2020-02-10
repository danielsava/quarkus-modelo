package base.auth.rest;

import base.auth.bean.Credencial;
import base.auth.bean.Login;
import base.auth.exception.CredencialException;
import base.auth.exception.TokenException;
import base.auth.service.AuthService;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthRest {

    @Inject
    AuthService authService;

    @Inject
    Logger log;

    @POST
    @Path("/login")
    public Credencial logar(@Valid Login login) throws TokenException, CredencialException {
        log.info("Logando " + login.login + ", " + login.senha);
        Credencial credencial = authService.autenticar(login);
        return credencial;

    }

    /*
    private Response responseNaoAutorizado(String msg) {
        return Response
                .status(Response.Status.UNAUTHORIZED)
                .entity(
                    Json.createObjectBuilder()
                        .add("mensagem", msg)
                        .build())
                .build();
    }
    */

}
