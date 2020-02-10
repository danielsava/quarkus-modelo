package base.auth.repository;

import base.auth.bean.Login;
import base.auth.config.UsuarioRootConfig;
import base.auth.entity.ContaUsuario;
import base.repository.BaseDtoDao;
import base.repository.bean.RetornoConsultaPaginada;
import com.google.common.collect.Lists;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ContaUsuarioDao extends BaseDtoDao<ContaUsuario> {


    @Inject
    UsuarioRootConfig usuarioRoot;


    public ContaUsuarioDao() {
        super(ContaUsuario.class);
    }


    public ContaUsuario findByLogin(String login) {
        if(login.equals(usuarioRoot.login()))
            return usuarioRoot();
        return null; //find("login", login).singleResult();
    }

    public void deletarPorLogin(Login login) {
        //delete("login", login.login);
    }

    private ContaUsuario usuarioRoot() {
        return ContaUsuario.of()
            .login(usuarioRoot.login())
            .senha(usuarioRoot.senha());
    }

    @Override
    public RetornoConsultaPaginada consultaPaginada(String filtro, Integer paginaOffSet) {
        return null;
    }

    @Override
    public List<ContaUsuario> autoCompleteConsulta(String filtro) {
        return Lists.newArrayList();
    }

}
