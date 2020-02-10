package base.repository;

import base.entity.BaseEntity;
import base.repository.bean.RetornoConsultaPaginada;

import java.util.List;


public abstract class BaseDtoDao<E extends BaseEntity> extends BaseDao<E> {


    public BaseDtoDao(Class<E> eClass) {
        super(eClass);
    }


    public abstract RetornoConsultaPaginada consultaPaginada(final String filtro, final Integer paginaOffSet);

    public abstract List<E> autoCompleteConsulta(String filtro);

}
