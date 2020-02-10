package base.auth.rest.mapper;

import base.auth.exception.CredencialException;

import javax.json.Json;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CredencialExceptionMapper implements ExceptionMapper<CredencialException> {

    @Override
    public Response toResponse(CredencialException e) {
        return Response
            .status(Response.Status.UNAUTHORIZED)
            .entity(
                Json.createObjectBuilder()
                    .add("mensagem", e.getMessage())
                .build())
            .build();
    }

}
