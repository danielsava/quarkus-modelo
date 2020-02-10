package base.auth.exception;

public class TokenException extends Exception {

    public TokenException() {
        super();
    }

    public TokenException(String s) {
        super(s);
    }

    public TokenException(String s, Throwable throwable) {
        super(s, throwable);
    }

}
