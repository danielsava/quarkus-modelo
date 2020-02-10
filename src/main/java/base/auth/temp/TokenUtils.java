package base.auth.temp;

import org.eclipse.microprofile.jwt.Claims;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.NumericDate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Map;
import java.util.stream.Collectors;

public class TokenUtils {


    private TokenUtils() { }


    /**
     *  Utility method to generate a JWT string from a JSON resource file that is signed by the privateKey.pem
     *  test resource key, possibly with invalid fields.
     *
     *
     *  @param jsonResName - name of test resources file
     *  @param timeClaims - used to return the exp, iat, auth_time claims
     *  @return the JWT string
     *  @throws Exception on parse failure
     *
     */
    public static String generateTokenString(String jsonResName, Map<String, Long> timeClaims) throws Exception {
        PrivateKey pk = readPrivateKey("/privateKey.pem");
        return generateTokenString(pk, "/privateKey.pem", jsonResName, timeClaims);
    }

    public static String generateTokenString(PrivateKey privateKey, String kid, String jsonResName, Map<String, Long> timeClaims) throws Exception {

        JwtClaims claims = JwtClaims.parse(readTokenContent(jsonResName));
        long currentTimeInSecs = currentTimeInSecs();
        long exp = timeClaims != null && timeClaims.containsKey(Claims.exp.name()) ? timeClaims.get(Claims.exp.name()) : currentTimeInSecs + 300;

        claims.setIssuedAt(NumericDate.fromSeconds(currentTimeInSecs()));
        claims.setClaim(Claims.auth_time.name(), NumericDate.fromSeconds(currentTimeInSecs));
        claims.setExpirationTime(NumericDate.fromSeconds(exp));

        for(Map.Entry<String, Object> entry : claims.getClaimsMap().entrySet())
            System.out.printf("\tAdded claim: %s, value: %s\n", entry.getKey(), entry.getValue());

        JsonWebSignature jws = new JsonWebSignature();
        jws.setPayload(claims.toJson());
        jws.setKey(privateKey);
        jws.setKeyIdHeaderValue(kid);
        jws.setHeader("typ", "JWT");
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);

        return jws.getCompactSerialization();

    }

    private static String readTokenContent(String jsonResName) throws IOException {
        InputStream contentIS = TokenUtils.class.getResourceAsStream(jsonResName);
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(contentIS))) {
            return buffer.lines().collect(Collectors.joining("\n"));
        }
    }

    /** Decode a PEM encoded private key string to an RSA PrivateKey */
    public static PrivateKey readPrivateKey(final String pemResName) throws Exception {
        InputStream contentIS = TokenUtils.class.getResourceAsStream(pemResName);
        byte[] tmp = new byte[4096];
        int lenght = contentIS.read(tmp);
        return decodePrivateKey(new String(tmp, 0, lenght, "UTF-8"));
    }

    /** Decode a PEM encoded private key string to an RSA PrivateKey */
    public static PrivateKey decodePrivateKey(final String pemEncoded) throws Exception {
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
    public static long currentTimeInSecs() {
        long currentTimeMS = System.currentTimeMillis();
        return currentTimeMS / 1000;
    }

}
