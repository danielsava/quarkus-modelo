package base.auth;

import base.auth.config.JwtBase;
import base.auth.entity.ContaUsuario;
import base.auth.exception.TokenException;
import base.auth.temp.TokenUtils;
import org.eclipse.microprofile.jwt.Claims;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.NumericDate;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Map;

public class JwtFactory {

    private static final String PRIVATE_KEY = "/privateKey.pem";

    public static String gerarToken(ContaUsuario contaUsuario) throws TokenException {
        try {
            PrivateKey pk = readPrivateKey();
            return generateTokenString(pk, contaUsuario);
        } catch (Exception e) {
            throw new TokenException("Erro ao gerar Token para o Usuário : " + contaUsuario.login + " (" + e.getLocalizedMessage() + ")", e);
        }
    }

    private static String generateTokenString(PrivateKey privateKey, ContaUsuario contaUsuario) throws Exception {

        JwtClaims claims = JwtClaims.parse(gerarJwtBase(contaUsuario));
        long currentTimeInSecs = currentTimeInSecs();

        long token_exp_seg = contaUsuario.tempoSessaoMin() * 60;
        long exp = currentTimeInSecs + token_exp_seg;

        claims.setIssuedAt(NumericDate.fromSeconds(currentTimeInSecs()));
        claims.setClaim(Claims.auth_time.name(), NumericDate.fromSeconds(currentTimeInSecs));
        claims.setExpirationTime(NumericDate.fromSeconds(exp));

        for(Map.Entry<String, Object> entry : claims.getClaimsMap().entrySet())
            System.out.printf("\tAdded claim: %s, value: %s\n", entry.getKey(), entry.getValue());

        JsonWebSignature jws = new JsonWebSignature();
        jws.setPayload(claims.toJson());
        jws.setKey(privateKey);
        jws.setKeyIdHeaderValue(PRIVATE_KEY);
        jws.setHeader("typ", "JWT");
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);

        return jws.getCompactSerialization();

    }

    private static String gerarJwtBase(ContaUsuario contaUsuario) {
        return JwtBase.of().upn(contaUsuario.login).toJson(); ///
    }

    /** Decode a PEM encoded private key string to an RSA PrivateKey */
    private static PrivateKey readPrivateKey() throws Exception {
        InputStream contentIS = TokenUtils.class.getResourceAsStream(PRIVATE_KEY);
        byte[] tmp = new byte[4096];
        int lenght = contentIS.read(tmp);
        return decodePrivateKey(new String(tmp, 0, lenght, StandardCharsets.UTF_8));
    }

    /** Decode a PEM encoded private key string to an RSA PrivateKey */
    private static PrivateKey decodePrivateKey(final String pemEncoded) throws Exception {
        byte[] encodedBytes = toEncodedBytes(pemEncoded);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encodedBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(keySpec);
    }

    private static byte[] toEncodedBytes(final String pemEncoded) {
        final String normalizedPem = removeBeginEnd(pemEncoded);
        return Base64.getDecoder().decode(normalizedPem);

    }

    private static String removeBeginEnd(String pem) {
        pem = pem.replaceAll("-----BEGIN (.*)-----", "");
        pem = pem.replaceAll("-----END (.*)----", "");
        pem = pem.replaceAll("\r\n", "");
        pem = pem.replaceAll("\n", "");
        return pem.trim();
    }


    /** The current time in seconds since epcoh    */
    private static long currentTimeInSecs() {
        long currentTimeMS = System.currentTimeMillis();
        return currentTimeMS / 1000;
    }


}
