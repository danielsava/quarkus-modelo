package base.auth.repository;

import base.auth.entity.Funcionalidade;
import base.auth.entity.Funcionalidade_;
import base.repository.BaseDtoDao;
import base.repository.bean.RetornoConsultaPaginada;
import base.util.StringUtil;
import com.google.common.collect.Lists;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@ApplicationScoped
public class FuncionalidadeDao extends BaseDtoDao<Funcionalidade> {


    public FuncionalidadeDao() {
        super(Funcionalidade.class);
    }


    public boolean jaExisteNome(final String nome) {
        return jaExisteNomeAndIdDiferente(nome, null);
    }

    public boolean jaExisteNomeAndIdDiferente(final String nome, final Long id) {
        return jaExisteColunaString(Funcionalidade_.nome, nome, id);
    }

    @Override
    public List<Funcionalidade> autoCompleteConsulta(final String filtro) {
        try {

            CriteriaBuilder builder = cb();

            // Query (Retorno)
            CriteriaQuery<Funcionalidade> query = builder.createQuery(eClass());

            // From
            Root<Funcionalidade> from = query.from(eClass());

            // Select
            CriteriaQuery<Funcionalidade> select = query.select(from);

            // Where
            if(StringUtil.isNotEmpty(filtro)) {
                Predicate predicate = builder.or(
                        builder.like(builder.lower(from.get(Funcionalidade_.nome)), "%" + filtro.toLowerCase() + "%"),
                        builder.like(builder.lower(from.get(Funcionalidade_.descricao)), "%" + filtro.toLowerCase() + "%")
                );
                select.where(predicate);
            }

            // Typed
            TypedQuery<Funcionalidade> typedQuery = em().createQuery(select);
            typedQuery.setFirstResult(0);
            typedQuery.setMaxResults(MAX_RESULTS_AUTOCOMPLETE);

            // Resultado
            return typedQuery.getResultList();


        } catch (NoResultException e) {
            return Lists.newArrayList();
        }
    }


    @Override
    public RetornoConsultaPaginada consultaPaginada(final String filtro, final Integer paginaOffSet) {

        try {

            CriteriaBuilder builder = cb();

            // Query (Retorno)
            CriteriaQuery<Funcionalidade> query = builder.createQuery(eClass());

            // From
            Root<Funcionalidade> from = query.from(eClass());

            // Select
            CriteriaQuery<Funcionalidade> select = query.select(from);

            // Where
            Predicate predicate = null;
            if(StringUtil.isNotEmpty(filtro)) {
                predicate = builder.or(
                        builder.like(builder.lower(from.get(Funcionalidade_.nome)), "%" + filtro.toLowerCase() + "%"),
                        builder.like(builder.lower(from.get(Funcionalidade_.descricao)), "%" + filtro.toLowerCase() + "%")
                );
                select.where(predicate);
            }

            // OrderBy
            query.orderBy(builder.desc(from.get(Funcionalidade_.id)));

            // Typed
            TypedQuery<Funcionalidade> typedQuery = em().createQuery(select);
            typedQuery.setFirstResult(paginaOffSet);
            typedQuery.setMaxResults(MAX_RESULTS_CONSULTA);

            List<Funcionalidade> resultado = typedQuery.getResultList();

            Long total = total(predicate);

            return RetornoConsultaPaginada.of(resultado, total, paginaOffSet);

        } catch (NoResultException e) {
            return RetornoConsultaPaginada.of(Lists.newArrayList(), 0, paginaOffSet);
        }

    }

}
