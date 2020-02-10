package base.rest;

import base.entity.BaseEntity;
import base.repository.BaseDtoDao;
import base.repository.bean.RetornoConsultaPaginada;

import javax.transaction.Transactional;
import javax.validation.constraints.Size;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 *  Endereço das API sempre no plural (/grupos; /agencias; /usuarios):
 *      - https://restfulapi.net/resource-naming/
 *
 *
 *  DTO
 *      https://cassiomolin.com/2016/03/23/why-you-should-use-dtos-in-your-rest-api/
 *      https://www.baeldung.com/java-performance-mapping-frameworks (benchmark - peformance)
 *
 *
 */

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public abstract class BaseRest<E extends BaseEntity> {


    private static final int TAMANHO_MAX_FILTRO = 150; // Limita o tamanho maximo da string que pode ser utilizada para consulta.

    @GET
    @Path("/autocomplete/{filtro}")
    public Response consultaAutoComplete(@Size(max =TAMANHO_MAX_FILTRO) @PathParam("filtro") String filtro) {
        return ok(dao().autoCompleteConsulta(filtro));
    }

    @GET
    public RetornoConsultaPaginada consultarPaginado(@Size(max =TAMANHO_MAX_FILTRO) @QueryParam("search") String filtro, @QueryParam("pageOffset") Integer pagina) {
        return dao().consultaPaginada(filtro, pagina != null ? pagina : 0);
    }

    @GET
    @Path("/edit/{id}")
    public Response editar(@PathParam("id") Long id) {
        E e = dao().consultarPorId(id);
        if (e == null)
            throw new WebApplicationException("[Consulta] Nenhum registro cadastrado com 'id' " + id, Response.Status.NOT_FOUND);
        return ok(e);
    }

    @GET
    @Path("{id}")
    public Response consultarPorId(@PathParam("id") Long id) {
        E e = dao().consultarPorId(id);
        if (e == null)
            throw new WebApplicationException("[Consulta] Nenhum registro cadastrado com 'id' " + id, Response.Status.NOT_FOUND);
        return ok(e);
    }


    @POST
    @Transactional
    public Response adicionar(E e) {
        dao().adicionar(e);
        return Response.ok(e).status(201).build();
    }

    @PUT
    @Transactional
    public Response alterar(E e) {
        if(e.id == null)
            throw new WebApplicationException("[Alteração] 'ID' não pode ser nulo", Response.Status.BAD_REQUEST);
        boolean existeId = dao().existeId(e.id);
        if (!existeId)
            throw new WebApplicationException("[Alteração] Nenhum registro cadastrado com 'id' " + e.id, Response.Status.NOT_FOUND);
        return ok(dao().alterar(e));
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deletar(@PathParam("id") Long id) {
        if(id == null)
            throw new WebApplicationException("[Exclusão] 'ID' não pode ser nulo", Response.Status.BAD_REQUEST);
        E entity = dao().consultarPorId(id);
        if (entity == null)
            throw new WebApplicationException("[Exclusão] Nenhum registro cadastrado com 'id' " + id, Response.Status.NOT_FOUND);
        dao().deletar(entity);
        return Response.status(204).build();
    }


    protected Response ok(Object entity) {
        return Response.ok()
            .type(MediaType.APPLICATION_JSON)
            .entity(entity)
            .build();
    }

    protected Response erro(Object entity) {
        return Response
            .status(Response.Status.INTERNAL_SERVER_ERROR)
            .type(MediaType.APPLICATION_JSON)
            .entity(entity)
            .build();
    }

    protected abstract BaseDtoDao<E> dao();

}
