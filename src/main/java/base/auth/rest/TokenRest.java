package base.auth.rest;

import base.auth.bean.Credencial;
import base.auth.exception.CredencialException;
import base.auth.exception.TokenException;
import base.auth.service.AuthService;
import io.quarkus.security.Authenticated;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.validation.constraints.NotEmpty;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Path("token")
@RequestScoped
@Authenticated
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TokenRest {

    @Inject
    Principal principal;

    @Inject
    JsonWebToken token;

    @Inject
    AuthService authService;

    @POST
    @Path("refresh")
    public Credencial refresh(@NotEmpty String login) throws TokenException, CredencialException {
        return authService.refresToken(login);
    }

    @GET
    @Path("info")
    public Response info() {
        return Response.ok(
            Json.createObjectBuilder()
                .add("upn", principal.getName())
                .add("issuer", token.getIssuer() != null ? token.getIssuer() : "null")
                .add("tokenId", token.getTokenID() != null ? token.getTokenID() : "null")
                .add("expirationTime", LocalDateTime.ofEpochSecond(token.getExpirationTime(), 0, ZoneOffset.of("-03:00")).toString())
                .add("issuedAtTime", LocalDateTime.ofEpochSecond(token.getIssuedAtTime(), 0, ZoneOffset.of("-03:00")).toString())
                .add("groups", token.getGroups().toString())
                .add("tokenRaw", token.getRawToken() != null ? token.getRawToken() : "null")
            .build()
        ).build();
    }

}
