package base.auth.temp;

import org.eclipse.microprofile.jwt.Claims;

import java.util.HashMap;

public class GenerateToken {

    /**
     *
     *  @param args
     *    - args[0]: optional name of classpath resoures for json document of claims to add; default to "/JwtClaims.json"
     *    - args[1]: optional time in seconds for expiration of generated token; default to 300
     *
     *
     */
    public static void main(String[] args) throws Exception {

        /* */
        String claimsJson = "/JwtClaims.json";

        if (args.length > 0)
            claimsJson = args[0];

        HashMap<String, Long> timeClaims = new HashMap<>();

        if (args.length > 1) {
            long duration = Long.parseLong(args[1]);
            long exp = TokenUtils.currentTimeInSecs() + duration;
            timeClaims.put(Claims.exp.name(), exp);
        }

        String token = TokenUtils.generateTokenString(claimsJson, timeClaims);
        System.out.println(token);

    }


}
