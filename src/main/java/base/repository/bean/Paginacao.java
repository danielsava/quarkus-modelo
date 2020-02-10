package base.repository.bean;

public class Paginacao {

    public Integer pagina;
    public Integer tamanhoPagina;

    private Paginacao(Integer pagina, Integer tamanhoPagina) {
        this.pagina = pagina;
        this.tamanhoPagina = tamanhoPagina;
    }

    public static Paginacao of(Integer pagina, Integer tamanhoPagina) {
        return new Paginacao(pagina, tamanhoPagina);
    }

}
