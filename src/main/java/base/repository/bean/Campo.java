package base.repository.bean;

import base.entity.BaseEntity;

import javax.persistence.metamodel.SingularAttribute;

public class Campo<E extends BaseEntity, C> {

    public SingularAttribute<E, C> atributo;
    public Class<C> classCampo;
    public OrdenacaoCampo ordenacao;
    public OperacaoCampo operacao;
    public C valor;

    public boolean isString() {
        return valor instanceof String;
    }

}
