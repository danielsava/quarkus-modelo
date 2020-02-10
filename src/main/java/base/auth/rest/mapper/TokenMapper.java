package base.auth.rest.mapper;

import base.auth.exception.TokenException;

import javax.json.Json;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class TokenMapper implements ExceptionMapper<TokenException> {

    @Override
    public Response toResponse(TokenException e) {
        return Response
            .status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode())
            .type(MediaType.APPLICATION_JSON)
            .entity(
                Json.createObjectBuilder()
                    .add("exception", e.toString())
                    .add("erro","Erro ao Gerar o Token")
                    .add("mensagem:", e.getMessage())
                .build()
            ).build();
    }

}
