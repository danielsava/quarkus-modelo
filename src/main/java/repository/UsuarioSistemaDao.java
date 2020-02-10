package repository;

import base.repository.BaseDtoDao;
import base.repository.bean.RetornoConsultaPaginada;
import com.google.common.collect.Lists;
import entity.UsuarioSistema;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class UsuarioSistemaDao extends BaseDtoDao<UsuarioSistema> {


    public UsuarioSistemaDao() {
        super(UsuarioSistema.class);
    }


    @Override
    public RetornoConsultaPaginada consultaPaginada(String filtro, Integer paginaOffSet) {
        return null;
    }

    @Override
    public List<UsuarioSistema> autoCompleteConsulta(String filtro) {
        return Lists.newArrayList();
    }
}
