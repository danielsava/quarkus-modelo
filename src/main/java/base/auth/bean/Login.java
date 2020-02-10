package base.auth.bean;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class Login implements Serializable {

    @NotEmpty (message = "login não informado")
    public String login;

    @NotEmpty @NotEmpty (message = "senha não informada")
    public String senha;

    public Boolean remember;

}
