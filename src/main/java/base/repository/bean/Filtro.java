package base.repository.bean;

import base.entity.BaseEntity;
import com.google.common.collect.Lists;

import javax.persistence.metamodel.SingularAttribute;
import java.util.List;

public class Filtro<E extends BaseEntity> {

    public List<Campo<E, ?>> campos;

    public OperacaoLogica operacao;

    private Filtro() {
        this.campos = Lists.newArrayList();
    }

    public static <E extends BaseEntity> Filtro<E> of() {
         return new Filtro<E>();
    }

    public CampoContexto and() {
        this.operacao = OperacaoLogica.AND;
        return new CampoContexto(this);
    }

    public CampoContexto or() {
        this.operacao = OperacaoLogica.OR;
        return new CampoContexto(this);
    }

    class CampoContexto {

        private Filtro<E> filtro;

        public CampoContexto(Filtro<E> filtro) {
            this.filtro = filtro;
        }

        public <C extends Class<?>> CampoContexto addCampo(SingularAttribute<E, C> atributo, C valor, OperacaoCampo operacao) {
            return createCampo(atributo, valor, operacao, null);
        }

        public <C extends Class<?>> CampoContexto addCampo(SingularAttribute<E, C> atributo, C valor, OperacaoCampo operacao, OrdenacaoCampo ordenacao) {
            return createCampo(atributo, valor, operacao, ordenacao);
        }

        private <C extends Class<?>> CampoContexto createCampo(SingularAttribute<E, C> atributo, C valor, OperacaoCampo operacao, OrdenacaoCampo ordenacao) {

            Campo<E, C> c = new Campo<E, C>();
            c.atributo = atributo;
            c.classCampo = atributo.getJavaType();
            c.valor = valor;
            c.ordenacao = ordenacao != null ? ordenacao : OrdenacaoCampo.ASC;
            c.operacao = operacao;

            this.filtro.campos.add(c);

            return this;
        }

    }

}


