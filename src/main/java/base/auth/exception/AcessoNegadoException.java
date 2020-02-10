package base.auth.exception;

public class AcessoNegadoException extends Exception {

    public AcessoNegadoException() {
        super();
    }

    public AcessoNegadoException(String s) {
        super(s);
    }

    public AcessoNegadoException(String s, Throwable throwable) {
        super(s, throwable);
    }

}
