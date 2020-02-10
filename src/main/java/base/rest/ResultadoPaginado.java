package base.rest;

import java.util.List;

public class ResultadoPaginado {

    public int pagina;

    public long total;

    public List<?> resultado;

    public static ResultadoPaginado of() {
        return new ResultadoPaginado();
    }

}
