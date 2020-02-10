package entity;


import base.entity.BaseEntity;
import com.google.common.base.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/*
  Sequencia -> E a sequencia de utilizacao dos pontos(localidade) disponibilizados (autorizado) pelo
  Orgao Regulador para o trecho.

  Ex:  Trecho: Gyn-Bsb - Ptos autorizados: 1(gyn), 2(ans), 3(pirinoplis), 4(sto antonio), 5(gama), 6(Bsb).

 */

@Entity
@Table(name = "ponto_parada")
public class PontoParada extends BaseEntity {

    @NotEmpty (message = "Informe o Municipio")
    @ManyToOne
    @JoinColumn(name = "municipio_id", nullable = false)
    public Municipio municipio;

    @NotEmpty(message="Informe o Nome")
    @Column(name="nome", nullable=false, unique=true)
    public String nome;

    @NotEmpty(message="Informe o Codigo")
    @Column(name="codigo", nullable=false, unique=true)
    public String codigo;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    public PontoParada() {}

    public static PontoParada of() {
        return new PontoParada();
    }


    @Override
    public String toString() {
        return "Ponto de parada = [ codigo = " + codigo + ", nome = " + nome + ", uf = " + municipio.uf.sigla + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PontoParada)) return false;
        if (!super.equals(o)) return false;
        PontoParada that = (PontoParada) o;
        return Objects.equal(municipio, that.municipio) &&
                Objects.equal(nome, that.nome) &&
                Objects.equal(codigo, that.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), municipio, nome, codigo);
    }
}
