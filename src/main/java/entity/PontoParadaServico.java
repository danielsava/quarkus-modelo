package entity;


import base.entity.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Table(name = "ponto_parada_servico")
public class PontoParadaServico extends BaseEntity {

    @Column(name = "sequencia", nullable = false)
    public Integer sequencia;

    @NotEmpty (message = "Informe o Servico")
    @ManyToOne
    @JoinColumn(name = "servico_id", nullable = false)
    public Servico servico;

    //-- formato: hh:mm, ver como fazer esta formatacao (hh:mm) no sistema
    @Column(name = "horario_embarque", nullable = false) //-- (hh:mm)
    public String horarioEmbarque;

    @Column(name = "plataforma", nullable = false)
    public String plataforma;

    @Column(name = "dias_passado", nullable = false) //-- + dias
    public Integer diasPassado;

    @Column(name = "fuso_horario", nullable = false)
    public String fusoHorario;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "pontoParada")
    public Set<PontoParadaLinha> pontosParadaLinha;


    public PontoParadaServico() {}

    public static PontoParadaServico of() {
        return new PontoParadaServico();
    }


    @Override
    public String toString() {
        return "[Ponto Parada Servico] " + servico.nome + " - (" + sequencia + ") - " + servico.linha.descricao;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PontoParadaServico)) return false;
        if (!super.equals(o)) return false;
        PontoParadaServico that = (PontoParadaServico) o;
        return java.util.Objects.equals(sequencia, that.sequencia) &&
                java.util.Objects.equals(servico, that.servico);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), sequencia, servico);
    }
}
