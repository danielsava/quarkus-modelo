package base.auth.service;

import base.auth.JwtFactory;
import base.auth.bean.Credencial;
import base.auth.bean.Login;
import base.auth.bean.Token;
import base.auth.entity.ContaUsuario;
import base.auth.exception.CredencialException;
import base.auth.exception.TokenException;
import base.auth.repository.ContaUsuarioDao;
import base.util.DateUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.time.LocalDateTime;

@ApplicationScoped
public class AuthService implements Serializable {

    @Inject
    ContaUsuarioDao contaDao;

    public Credencial autenticar(Login login) throws CredencialException, TokenException {
        ContaUsuario conta = contaDao.findByLogin(login.login);
        verificarUsuarioSenha(conta, login);
        validarConta(conta);
        return gerarCredenciais(conta);
    }

    public Credencial refresToken(String login) throws CredencialException, TokenException {
        try {
            ContaUsuario conta = contaDao.findByLogin(login);
            if(conta == null)
                throw new CredencialException(" Login não encontrado");
            validarConta(conta);
            return gerarCredenciais(conta);
        } catch (CredencialException e) {
            throw new CredencialException("RefreshToken não permitido", e);
        }
    }

    private void verificarUsuarioSenha(ContaUsuario contaUsuario, Login login) throws CredencialException {
        if(contaUsuario == null)
            throw new CredencialException("Login não encontrado");
        if(!(contaUsuario.senha.trim().equals(login.senha.trim())))
            throw new CredencialException("Senha incorreta");
    }

    private void validarConta(ContaUsuario contaUsuario) throws CredencialException {
        if(contaUsuario.contaBloqueada())
            throw new CredencialException("Conta Bloqueada");
        if(contaUsuario.contaExpirada())
            throw new CredencialException("Conta expirada");
    }

    private Credencial gerarCredenciais(ContaUsuario contaUsuario) throws TokenException {
        return Credencial.of()
                .login(contaUsuario.login)
                .token(gerarToken(contaUsuario));
    }

    private Token gerarToken(ContaUsuario contaUsuario) throws TokenException {

        LocalDateTime dataCriacao = LocalDateTime.now();
        long dataCriacaoEpoch = DateUtil.toEpochSegundos(dataCriacao);

        LocalDateTime dataValidade = dataCriacao.plusMinutes(contaUsuario.tempoSessaoMin());
        long dataValidadeEpoch = DateUtil.toEpochSegundos(dataValidade);;

        String rawToken = gerarRawToken(contaUsuario);

        return Token.of()
                .criadoEmEpoch(dataCriacaoEpoch)
                .expiraEmEpoch(dataValidadeEpoch)
                .tempoSessaoMin(contaUsuario.tempoSessaoMin())
                .rawToken(rawToken);

    }

    private String gerarRawToken(ContaUsuario contaUsuario) throws TokenException {
        return JwtFactory.gerarToken(contaUsuario);
    }

}
