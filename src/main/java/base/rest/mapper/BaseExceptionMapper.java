package base.rest.mapper;

import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.json.Json;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BaseExceptionMapper implements ExceptionMapper<Exception> {

    @Inject
    Logger log;


    @Override
    public Response toResponse(Exception e) {

        log.error("Exception", e);

        int code = 500;
        return Response
                .status(code)
                .type(MediaType.APPLICATION_JSON)
                .entity(
                        Json.createObjectBuilder()
                            .add("exception", e.toString())
                            .add("mensagem", e.getMessage())
                            .add("code", code)
                        .build()
                ).build();
    }

}
