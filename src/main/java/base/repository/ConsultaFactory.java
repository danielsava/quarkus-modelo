package base.repository;

import base.entity.BaseEntity;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

public class ConsultaFactory<E extends BaseEntity> {


    private Class<E> fromEntityClass;
    private CriteriaBuilder builder;
    private CriteriaQuery<E> query;
    private Root<E> from;
    private CriteriaQuery<E> select;
    private Predicate filtro;
    private TypedQuery<E> typedQuery;

    private ConsultaFactory(CriteriaBuilder cb, Class<E> fromEntityClass) {
        this.fromEntityClass = fromEntityClass;
        this.builder = cb;
        this.query = this.builder.createQuery(fromEntityClass);
        this.from = this.query.from(fromEntityClass);
        this.select = this.query.select(from);
     }

    public static <E extends BaseEntity> ConsultaFactory<E> of(CriteriaBuilder cb, Class<E> fromEntityClass) {
        return new ConsultaFactory<E>(cb, fromEntityClass);
    }

    /*  select().where().campo(Funcionalidade_nome).lower().equals().and() */

    public WhereContexto where() {
        return new WhereContexto();
    }


    private class WhereContexto {
        public <C extends Class<?>> CampoContexto campo(SingularAttribute<E, Class<C>> campo, C valor) {
            return new CampoContexto();
        }
    }



    private class CampoContexto {
        public <C extends Class<?>> ValorContexto campo(SingularAttribute<E, Class<C>> campo, C valor) {
            return null;
        }
    }


    private class ValorContexto {

        public ValorContexto lower() {
            return null;
        }

        public OperadoresContexto igual() {
            return null;
        }


        public OperadoresContexto gt() {
            return null;
        }

        public OperadoresContexto lt() {
            return null;
        }

        public OperadoresContexto like() {
            return null;
        }

    }


    private class OperadoresContexto {


        public CampoContexto and() {
            return null;
        }

        public CampoContexto or() {
            return null;
        }

    }


}







