package entity;


import base.entity.BaseEntity;
import com.google.common.base.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/*
  Sequencia -> E a sequencia utilizada dos pontos disponibilizados pelo
  Orgao Regulador para o trecho. Não necessariamente serão utilizados pela empresa de todos os
  pontos disponibilizado pelo Orgao, sendo obrigatorio o inicio/final que compreende o trecho.

    Ex:  Trecho: Gyn-Bsb - Ptos autorizados: 1(gyn), 2(ans), 3(pirinoplis), 4(sto antonio), 5(gama), 6(Bsb). -> Seq: 1,2,3,4,5,6
         Linha.: Gyn-Bsb - Ptos utilizados.: 1(gyn), 2(ans), ............., .............., 3(gama), 4(bsb). -> Seq: 1,2,3,4

 */

@Entity
@Table(name = "ponto_parada_linha")
public class PontoParadaLinha extends BaseEntity {

    @NotEmpty (message = "Informe a Linha")
    @ManyToOne
    @JoinColumn(name = "linha_id", nullable = false)
    public Linha linha;

    @NotEmpty (message = "Informe Ponto de Parada")
    @ManyToOne
    @JoinColumn(name = "ponto_parada_id", nullable = false)
    public PontoParada pontoParada;

    @Column(name = "sequencia", nullable = false)
    public Integer sequencia;

    //-- formato: hh:mm, ver como fazer esta formatacao (hh:mm) no sistema
    @Column(name = "duracao", nullable = false) //-- (hh:mm)
    public Integer duracao;

    @Column(name = "distancia", nullable = false) //-- em km
    public Integer distancia;

    @Column(name = "distancia_total", nullable = false) //-- em km
    public Integer distanciaTotal;

    public PontoParadaLinha() {}

    public static PontoParadaLinha of() {
        return new PontoParadaLinha();
    }


    @Override
    public String toString() {
        return "[Ponto Parada Linha] " + linha.descricao + " - (" + sequencia + ") - " + pontoParada.nome + " - " + pontoParada.municipio.nome
                + "/" + pontoParada.municipio.uf.sigla;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PontoParadaLinha)) return false;
        if (!super.equals(o)) return false;
        PontoParadaLinha that = (PontoParadaLinha) o;
        return Objects.equal(linha, that.linha) &&
                Objects.equal(pontoParada, that.pontoParada) &&
                Objects.equal(sequencia, that.sequencia);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), linha, pontoParada, sequencia);
    }

}
