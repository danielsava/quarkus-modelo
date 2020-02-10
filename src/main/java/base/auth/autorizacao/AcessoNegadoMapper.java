package base.auth.autorizacao;

import base.auth.exception.AcessoNegadoException;

import javax.json.Json;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AcessoNegadoMapper implements ExceptionMapper<AcessoNegadoException> {

    @Override
    public Response toResponse(AcessoNegadoException e) {
        return Response
                .status(Response.Status.UNAUTHORIZED)
                .type(MediaType.APPLICATION_JSON)
                //.header(HttpHeaders.WWW_AUTHENTICATE, "Bearer realm=\"example\"")
                .entity(
                        Json.createObjectBuilder()
                            .add("Autorizacao","")
                            .add("message:", "Acesso Negado para a Funcionalidade")
                            .add("code", Response.Status.UNAUTHORIZED.name())
                        .build()
                ).build();
    }

}
