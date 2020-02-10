package base.auth.repository;

import base.auth.entity.Pagina;
import base.auth.entity.Pagina_;
import base.repository.BaseDtoDao;
import base.repository.bean.RetornoConsultaPaginada;
import base.util.StringUtil;
import com.google.common.collect.Lists;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class PaginaDao extends BaseDtoDao<Pagina> {

    @Inject
    PaginaFuncionalidadeDao pagFuncDao;


    public PaginaDao() {
        super(Pagina.class);
    }


    public Pagina consultarComFuncionalidades(Long id) {
        Pagina pagina = consultarPorId(id);
        pagina.funcionalidades = pagFuncDao.consultarPor(pagina);
        return pagina;
    }

    public boolean jaExisteUrl(final String url) {
        return jaExisteUrlAndIdDiferente(url, null);
    }

    public boolean jaExisteUrlAndIdDiferente(final String url, final Long id) {
        return jaExisteColunaString(Pagina_.url, url, id);
    }

    @Override
    public List<Pagina> autoCompleteConsulta(final String filtro) {
        try {

            CriteriaBuilder builder = cb();

            // Query (Retorno)
            CriteriaQuery<Tuple> query = builder.createTupleQuery();

            // From
            Root<Pagina> from = query.from(eClass());

            // Select
            CriteriaQuery<Tuple> select =  query.multiselect(from.get(Pagina_.id), from.get(Pagina_.url), from.get(Pagina_.descricao));

            // Where
            if(StringUtil.isNotEmpty(filtro)) {
                Predicate predicate = builder.or(
                        builder.like(builder.lower(from.get(Pagina_.url)), "%" + filtro.toLowerCase() + "%"),
                        builder.like(builder.lower(from.get(Pagina_.descricao)), "%" + filtro.toLowerCase() + "%")
                );
                select.where(predicate);
            }

            // Typed
            TypedQuery<Tuple> typedQuery = paginar(select, 0, MAX_RESULTS_AUTOCOMPLETE);

            // Tuple em Pagina
            List<Pagina> resultado = typedQuery.getResultList().stream().map(t -> {
                Pagina pagina = new Pagina();
                pagina.id = t.get(from.get(Pagina_.id));
                pagina.url = t.get(from.get(Pagina_.url));
                pagina.descricao = t.get(from.get(Pagina_.descricao));
                return pagina;
            }).collect(Collectors.toList());

            // Resultado
            return resultado;

        } catch (NoResultException e) {
            return Lists.newArrayList();
        }

    }


    @Override
    public RetornoConsultaPaginada consultaPaginada(final String filtro, final Integer paginaOffSet) {

        try {

            CriteriaBuilder builder = cb();

            // Query (Retorno)
            CriteriaQuery<Tuple> query = builder.createTupleQuery(); //CriteriaQuery<Pagina> query = builder.createQuery(Pagina.class);

            // From
            Root<Pagina> from = query.from(eClass());

            // Select
            CriteriaQuery<Tuple> select = query.multiselect(from.get(Pagina_.id), from.get(Pagina_.url), from.get(Pagina_.descricao));

            // Where
            Predicate predicate = null;
            if(StringUtil.isNotEmpty(filtro)) {
                predicate = builder.or(
                        builder.like(builder.lower(from.get(Pagina_.url)), "%" + filtro.toLowerCase() + "%"),
                        builder.like(builder.lower(from.get(Pagina_.descricao)), "%" + filtro.toLowerCase() + "%")
                );
                select.where(predicate);
            }

            // OrderBy
            query.orderBy(builder.desc(from.get(Pagina_.id)));

            // Paginacao
            TypedQuery<Tuple> typedQuery = paginar(select, paginaOffSet, MAX_RESULTS_CONSULTA);

            // Tuple em Pagina
            List<Pagina> resultado = typedQuery.getResultList().stream().map(t -> { //List<PaginaDto> resultado = typedQuery.getResultList();
                Pagina pagina = new Pagina();
                pagina.id = t.get(from.get(Pagina_.id));
                pagina.url = t.get(from.get(Pagina_.url));
                pagina.descricao = t.get(from.get(Pagina_.descricao));
                return pagina;
            }).collect(Collectors.toList());

            Long total = total(predicate);

            return RetornoConsultaPaginada.of(resultado, total, paginaOffSet);

        } catch (NoResultException e) {
            return RetornoConsultaPaginada.of(Lists.newArrayList(),0, paginaOffSet);
        }

    }

}
