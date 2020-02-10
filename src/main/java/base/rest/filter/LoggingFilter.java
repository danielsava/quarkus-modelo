package base.rest.filter;

import io.vertx.core.http.HttpServerRequest;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class LoggingFilter implements ContainerRequestFilter {

    @Inject
    Logger log;

    @Context
    UriInfo uriInfo;

    @Context
    HttpServerRequest request;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        final String method = requestContext.getMethod();
        final String path = uriInfo.getPath();
        final String address = request.remoteAddress().toString();

        log.infof("Requisicao %s %s from IP %s", method, path, address);

    }

}
