package base.repository.bean;

import java.util.List;

public class RetornoConsultaPaginada {

    public List<?> resultado;

    public int pageOffSet;

    public long total;

    public RetornoConsultaPaginada(final List<?> resultado, final long total, final int pageOffSet) {
        this.resultado = resultado;
        this.total = total;
        this.pageOffSet = pageOffSet;
    }

    public static RetornoConsultaPaginada of(final List<?> resultado, final long total, final int paginaOffSet) {
        return new RetornoConsultaPaginada(resultado, total, paginaOffSet);
    }

}
