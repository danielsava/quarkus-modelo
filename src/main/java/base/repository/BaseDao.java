package base.repository;

import base.entity.BaseEntity;
import base.entity.BaseEntity_;
import base.util.StringUtil;
import com.google.common.collect.Lists;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import java.util.List;


/**
 *  JPQL
 *    -  https://thoughts-on-java.org/jpql/
 *
 *  Like (operador)
 *    - Não há um padrão para todos os bancos, mas no MySQL as consultas com like ignoram acentos e cases
 *
 *  Consultando Colunas
 *    -  Tuple: https://stackoverflow.com/questions/12618489/jpa-criteria-api-select-only-specific-columns
 *
 *
 *
 */

public abstract class BaseDao<E extends BaseEntity> {

    protected static final int MAX_RESULTS_CONSULTA = 10;

    protected static final int MAX_RESULTS_AUTOCOMPLETE = 4;

    @Inject
    EntityManager em;

    protected Class<E> eClass;

    public BaseDao(Class<E> eClass) {
        this.eClass = eClass;
    }

    public void adicionar(E e) {
        em.persist(e);
    }

    public E alterar(E e) {
        return em.merge(e);
    }

    public void salvar(E e) {
        if (e.id == null)
            adicionar(e);
        else
            alterar(e);
    }

    public void deletar(E e) {
        em.remove(e);
    }

    public E consultarPorId(Long id) {
        return em.find(eClass, id);
    }

    public boolean existeId(Long id) {
        try {
            return em
                .createQuery("select e.id from " + eClass.getName() + " e where e.id=:id", Long.class)
                .setParameter("id", id)
                .getSingleResult() > 0;
        } catch (NoResultException e) {
            return false;
        }
    }

    public List<E> consultarTodos() {
        return em.createQuery("select e from " + eClass.getName() + " e", eClass).getResultList();
    }

    public List<E> consultarUltimos() {
        return consultarUltimos(MAX_RESULTS_CONSULTA);
    }

    public List<E> consultarUltimos(int quantidade) {
        return em()
            .createQuery("select e from " + eClass.getName() + " e ORDER BY e.id DESC", eClass)
            .setFirstResult(0)           // Página
            .setMaxResults(quantidade)   // Tamanho da Paginação
            .getResultList();
    }


    public Long total() {
        return  em().createQuery("select count(e.id) from " + eClass.getName() + " e", Long.class).getSingleResult();
    }

    public Long total(Predicate filtro) {

        CriteriaBuilder builder = cb();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<E> from = query.from(eClass());
        CriteriaQuery<Long> select = query.select(builder.count(from));

        if(filtro != null)
            select.where(filtro);

        return em().createQuery(select).getSingleResult();
    }

    public boolean jaExisteColunaString(final SingularAttribute<E, String> atributo, final String valor, final Long id) {  //public <Y> boolean jaExisteColuna(final SingularAttribute<E, Y> atributo, final Class<Y> valorAtributo, final Long id) {
        try {

            // Builder
            CriteriaBuilder builder = cb();

            // Query (Retorno)
            CriteriaQuery<String> query = builder.createQuery(String.class);

            // From
            Root<E> from = query.from(eClass());

            // Select
            CriteriaQuery<String> select = query.multiselect(from.get(atributo));

            String valorLower = valor.toLowerCase();
            Predicate filtro = builder.equal(builder.lower(from.get(atributo)), valorLower);

            if(id != null)
                filtro = builder.and(
                        filtro,                                         // Filtro com valor
                        builder.notEqual(from.get(BaseEntity_.id), id)  // Filtro com Id diferente
                );

            select.where(filtro);

            return em().createQuery(select).getSingleResult() != null;

        } catch (NoResultException e) {
            return false;
        }

    }

    public <C> TypedQuery<C> paginar(final CriteriaQuery<C> select, final Integer paginaOffSet, final Integer total) {
        TypedQuery<C> typedQuery = em().createQuery(select);
        typedQuery.setFirstResult(paginaOffSet);
        typedQuery.setMaxResults(total);
        return typedQuery;
    }

    public Class<E> eClass() { return this.eClass; }

    protected EntityManager em() {
        return em;
    }

    protected CriteriaBuilder cb() {
        return em().getCriteriaBuilder();
    }


    /* Exemplos de Referencia */
    public List<E> __filtrarPorCamposString(final String valor, final SingularAttribute<E, String>... atributos) {
        try {


            CriteriaBuilder builder = cb();

            // Query (Retorno)
            CriteriaQuery<E> query = builder.createQuery(eClass());

            // From
            Root<E> from = query.from(eClass());

            // Select
            CriteriaQuery<E> select = query.select(from);

            // Where
            if (StringUtil.isNotEmpty(valor) && atributos != null && atributos.length > 0) {
                String filtroLower = valor.toLowerCase();
                Predicate filtro = null;
                for (SingularAttribute<E, String> a : atributos) {
                    if(filtro == null) {
                        filtro = builder.like(builder.lower(from.get(a)), "%" + filtroLower + "%");
                        continue;
                    }
                    filtro = builder.or(filtro, builder.like(builder.lower(from.get(a)), "%" + filtroLower + "%"));
                }
                select.where(filtro);
            }

            // Typed
            TypedQuery<E> typedQuery = em().createQuery(select);
            typedQuery.setFirstResult(0);
            typedQuery.setMaxResults(MAX_RESULTS_CONSULTA);

            // Resultado
            return typedQuery.getResultList();


        } catch (NoResultException e) {
            return Lists.newArrayList();
        }
    }

    public List<E> __consultarPaginado(final int page, final int pageSize) {
        return em()
                .createQuery("select e from " + eClass.getName() + " e ORDER BY e.id DESC", eClass)
                .setFirstResult( (page - 1) * pageSize)    // Página
                .setMaxResults(pageSize)                   // Tamanho da Paginação
                .getResultList();
    }


    /* Exemplo Criteria */
    private List<E> __consultarTodosCriteria() {
        try {

            // Builder
            CriteriaBuilder builder = cb();

            // Query (Retorno)
            CriteriaQuery<E> query = builder.createQuery(eClass);

            // From
            Root<E> from = query.from(eClass);

            // Select
            query.select(from);

            // Resultado
            return em().createQuery(query).getResultList();


        } catch (NoResultException e) {
            return Lists.newArrayList();
        }
    }


    /* Exemplo Criteria */
    private List<E> __selectWhere(final Predicate filtro) {

        try {

            // Builder
            CriteriaBuilder builder = cb();

            // Query (Retorno)
            CriteriaQuery<E> query = builder.createQuery(eClass);

            // From
            Root<E> from = query.from(eClass);

            // Select
            CriteriaQuery<E> select = query.select(from);

            // Where
            select.where(filtro);

            // Typed
            TypedQuery<E> typedQuery = em().createQuery(select);

            // Resultado
            List<E> results = typedQuery.getResultList();

            // Retorno
            return results;


        } catch (NoResultException e) {
            return null;
        }

    }


}
