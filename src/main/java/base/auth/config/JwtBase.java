package base.auth.config;

import javax.json.bind.JsonbBuilder;

/**
 *
 *  Considerações sobre o TokenJwt
 *  - If JWTs are used for Authentication, they will contain at least a user ID and an expiration timestamp
 *
 *  Sobre a Chave Publica, que é utilizada para verificar a assinatura dos Tokens
 *  - "the public key can be published in a URL and automatically read by the Application server at startup time and periodically"
 *
 *  Sobre 'key rotation and revocation' de Chaves Públicas
 *   - This last part (publicar as chaves publicas em uma url) is a great feature:
 *     being able to publish the validating key gives us built-in key rotation and revocation, and we will implement that in this post!
 *     This is because in order to enable a new key pair we simply publish a new public key, and we will see that in action.
 *     https://blog.angular-university.io/angular-jwt-authentication/
 *
 *
 *
 *
 */
public class JwtBase {

    /** Emissor Responsável pelo Token */
    public String iss = "https://savasoft.com.br";

    /** User Principal Name */
    public String upn;

    public static JwtBase of() {
        return new JwtBase();
    }

    public JwtBase upn(String upn) {
        this.upn = upn.trim();
        return this;
    }

    public String toJson() {
        return JsonbBuilder.create().toJson(this);
    }

}
