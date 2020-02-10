package base.auth.rest;

import base.auth.entity.Pagina;
import base.auth.repository.PaginaDao;
import base.rest.BaseRest;
import io.quarkus.security.Authenticated;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@Path("paginas")
@RequestScoped
@Authenticated
public class PaginaRest extends BaseRest<Pagina> {

    @Inject
    PaginaDao paginaDao;

    @GET
    @Path("/existe/url/{url}")
    public boolean jaExisteNome(@PathParam("url") String url) {
        return paginaDao.jaExisteUrl(url);
    }

    @GET
    @Path("/existe/url/{url}/id-diferente/{id}")
    public boolean jaExisteNomeComDiferente(@PathParam("url") String url, @PathParam("id") Long id) {
        return paginaDao.jaExisteUrlAndIdDiferente(url, id);
    }

    @Override
    public Response editar(Long id) {
        if(!this.paginaDao.existeId(id))
            throw new WebApplicationException("[Consulta] Nenhum registro cadastrado com 'id' " + id, Response.Status.NOT_FOUND);
        return ok(paginaDao.consultarComFuncionalidades(id));
    }

    @Override
    protected PaginaDao dao() {
        return paginaDao;
    }

}
