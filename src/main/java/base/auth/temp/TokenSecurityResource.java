package base.auth.temp;


import com.google.common.collect.Lists;
import io.quarkus.security.Authenticated;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;


/**
 *
 *
 *      Anotação FUNCIONALIDADE:
 *      - È um Hash de "url+funcionalidade" da entidade 'PaginaFuncionalidade'.
 *      Para cada endpoint, que necessite de autorização, terá uma anotação com o valor desse hash. A permissão, é o
 *      grupo do usuário possuir esse hash.
 *
 *
 *      PROBLEMA: Token desatualizado depois de alterar as permissões do Grupo de Usuário
 *        - Para este probleme, criar um evento que irá sinalizar a aplicação para re-criar (gerar novamnete)
 *        os tokens dos usuário. A rotina que gera o Token, irá a cada passagem de Token vericar uma variável (static)
 *        'recriar token', que será positiva a cada alteração de permissão dos grupos de usuários.
 *
 *
 *      Artigo sobre Hash em Java.
 *      - https://www.devmedia.com.br/como-funciona-a-criptografia-hash-em-java/31139
 *
 *
 *
 */

@Path("/secured/")
@RequestScoped // Obrigatorio para o uso com JWT, que é naturalmente RequestScoped
public class TokenSecurityResource {

    @Inject
    JsonWebToken jwt;


    @GET
    @Path("permit")
    @Produces(MediaType.TEXT_PLAIN)
    @PermitAll
    public String hello(@Context SecurityContext ctx) {

        Principal caller = ctx.getUserPrincipal();
        String userJwt = jwt.getName();
        String userTutorial = caller == null ? "anonymous" : caller.getName();
        Set<String> userClaims = jwt.getClaimNames();
        boolean jasJwt = userClaims != null;
        return "UserTutorial: " + userTutorial
                + ", UserJwt: " + userJwt
                + ", isSecure: " + ctx.isSecure() // Informa se a conexão foi feita usando canal seguro (HTTPS)
                + ", authScheme: " + ctx.getAuthenticationScheme() // tells you which authentication mechanism was used to secure the request. BASIC, DIGEST, CLIENT_CERT, and FORM
                + ", hasJwt: " + jasJwt
                + ", userClaims: " + userClaims;

    }

    @GET
    @Path("protected")
    //@RolesAllowed({"Echoer", "Subscriber"})
    @Authenticated
    @Produces(MediaType.TEXT_PLAIN)
    public String protegido(@Context SecurityContext ctx) {
        // Esse caller ('Principal') é igual o jwt ('JsonWebToken'). JsonWebToken extends a interface Principal.
        Principal caller = ctx.getUserPrincipal();
        String nome2 = jwt.getName();
        String name = caller == null ? "anomymous" : caller.getName();
        boolean hasJwt = jwt.getClaimNames() != null;
        return String.format("hello %s (%s), isSecure: %s, authScheme: %s, hasJwt: %s", name, nome2, ctx.isSecure(), ctx.getAuthenticationScheme(), hasJwt);

    }


    @GET
    @Path("sorteio")
    @Produces(MediaType.TEXT_PLAIN)
    @Authenticated
    //@RolesAllowed("Subscriber")
    public String winners() {
        int remaing = 6;
        ArrayList<Integer> numbers = Lists.newArrayList();
        if(jwt.containsClaim(Claims.birthdate.name())) {
            String bdayString = jwt.getClaim(Claims.birthdate.name()); // Obtem o Claim
            LocalDate bday = LocalDate.parse(bdayString);
            numbers.add(bday.getDayOfMonth());
            remaing --;
        }
        while(remaing > 0) {
            int pick = (int) Math.rint(64 * Math.random() + 1);
            numbers.add(pick);
            remaing --;
        }

        return numbers.toString();

    }


}
