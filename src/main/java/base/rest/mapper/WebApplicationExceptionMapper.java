package base.rest.mapper;

import javax.json.Json;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {

    @Override
    public Response toResponse(WebApplicationException e) {

        int code = e.getResponse().getStatus();

        return Response
                .status(code)
                .type(MediaType.APPLICATION_JSON)
                .entity(
                    Json.createObjectBuilder()
                        .add("code", code)
                        .add("mensagem", e.getMessage())
                    .build()
                ).build();
    }

}
