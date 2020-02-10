package base.auth.autorizacao;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.AUTHORIZATION)
public class AutorizacaoFilter implements ContainerRequestFilter {


    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) {

        // Funcionalidade vai ser Hash da 'url' + 'funcionalidae', que representa a funcionalidade da pagina
        //String permissaoUsuario = requestContext.getHeaderString("PERMISSAO");

        //String funcionalidadeHash = resourceInfo.getResourceMethod().getAnnotation(Funcionalidade.class).hash();

        // Retona um NãoAutorizado
        //if (!permissaoUsuario.equals(funcionalidadeHash))
           //abortWithUnauthorized(requestContext);


    }



    private void abortWithUnauthorized(ContainerRequestContext requestContext) {
        // Abort the filter chain with a 401 status code response
        // The WWW-Authenticate header is sent along with the response
        requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                        .header(HttpHeaders.WWW_AUTHENTICATE, "Bearer realm=\"example\"")
                        .build());
    }


}
