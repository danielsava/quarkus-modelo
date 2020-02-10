package base.auth.rest;

import base.auth.entity.Funcionalidade;
import base.auth.repository.FuncionalidadeDao;
import base.rest.BaseRest;
import io.quarkus.security.Authenticated;
import base.util.StringUtil;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@Path("funcionalidades")
@RequestScoped
@Authenticated
public class FuncionalidadeRest extends BaseRest<Funcionalidade> {

    @Inject
    FuncionalidadeDao funcionalidadeDao;

    @GET
    @Path("/existe/nome/{nome}")
    public boolean jaExisteNome(@PathParam("nome") String nome) {

        if (StringUtil.isEmpty(nome))
            throw new WebApplicationException("Parâmetro 'nome' não pode ser nulo ou vazio", Response.Status.BAD_REQUEST);

        return funcionalidadeDao.jaExisteNome(nome);
    }

    @GET
    @Path("/existe/nome/{nome}/id-diferente/{id}")
    public boolean jaExisteNomeComDiferente(@PathParam("nome") String nome, @PathParam("id") Long id) {

        if (StringUtil.isEmpty(nome))
            throw new WebApplicationException("Parâmetro 'nome' não pode ser nulo ou vazio", Response.Status.BAD_REQUEST);

        if (id == null)
            throw new WebApplicationException("Parâmetro 'id' não pode ser nulo", Response.Status.BAD_REQUEST);

        return funcionalidadeDao.jaExisteNomeAndIdDiferente(nome, id);
    }

    @Override
    protected FuncionalidadeDao dao() {
        return funcionalidadeDao;
    }
}
