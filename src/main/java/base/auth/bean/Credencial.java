package base.auth.bean;

import java.io.Serializable;
import java.util.List;

public class Credencial implements Serializable {

    public String login;

    public Token token;

    public String pagInicial;

    public List<String> hashPermissoes;

    public static Credencial of() {
        return new Credencial();
    }

    public Credencial token(Token token) {
        this.token = token;
        return this;
    }

    public Credencial login(String login) {
        this.login = login;
        return this;
    }

}
