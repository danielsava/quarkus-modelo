package base.auth.bean;

public class Token {

    public String rawToken;

    public long criadoEmEpoch;

    public long expiraEmEpoch;

    public int tempoSessaoMin;

    public static Token of() {
        return new Token();
    }

    public Token rawToken(String rawToken) {
        this.rawToken = rawToken;
        return this;
    }

    public Token criadoEmEpoch(long epochSeg) {
        this.criadoEmEpoch = epochSeg;
        return this;
    }

    public Token expiraEmEpoch(long epochSeg) {
        this.expiraEmEpoch = epochSeg;
        return this;
    }

    public Token tempoSessaoMin(int tempoMin) {
        this.tempoSessaoMin = tempoMin;
        return this;
    }

}
