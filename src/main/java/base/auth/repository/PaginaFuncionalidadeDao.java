package base.auth.repository;

import base.auth.entity.Pagina;
import base.auth.entity.PaginaFuncionalidade;
import base.auth.entity.PaginaFuncionalidade_;
import base.repository.BaseDao;
import com.google.common.collect.Lists;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@ApplicationScoped
public class PaginaFuncionalidadeDao extends BaseDao<PaginaFuncionalidade> {


    public PaginaFuncionalidadeDao() {
        super(PaginaFuncionalidade.class);
    }


    public List<PaginaFuncionalidade> consultarPor(Pagina pagina) {
        try {

            // Builder
            CriteriaBuilder builder = cb();

            // Retorno
            CriteriaQuery<PaginaFuncionalidade> query = builder.createQuery(eClass());

            // From
            Root<PaginaFuncionalidade> from = query.from(eClass());

            // select
            CriteriaQuery<PaginaFuncionalidade> select = query.select(from);

            query.select(from).where(builder.equal(from.get(PaginaFuncionalidade_.pagina), pagina));

            // Where
            select.where(builder.equal(from.get(PaginaFuncionalidade_.pagina), pagina));

            // Resultado
            return em().createQuery(select).getResultList();

        } catch (NoResultException e) {
            return Lists.newArrayList();
        }

    }


}
