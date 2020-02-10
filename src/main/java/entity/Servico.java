package entity;


import base.entity.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "servico")
public class Servico extends BaseEntity {

    @NotEmpty (message = "Informe a Linha")
    @ManyToOne
    @JoinColumn(name = "linha_id", nullable = false)
    public Linha linha;


    @Column(name = "nome", nullable = false)
    public String nome;

    @Column(name = "horario", nullable = false) //-- hh:mm ????
    public String horario;

    //-------------------- dados do veiculo ---------------------//
    @NotEmpty (message = "Informe Veiculo")
    @ManyToOne
    @JoinColumn(name = "veiculo_id", nullable = false)
    public Veiculo veiculo;

    @Column(name = "trajeto_opcional", nullable = false)
    public String trajetoOpcional;

    @Column(name = "sentido_direcao", nullable = false)  //-- Ida / Volta
    public String sentidoDirecao;

    @Column(name = "permite_viagem_em_pe", nullable = false)  //-- Sim / Nao
    public String permiteViagemEmPe;

    @Column(name = "carro_extra", nullable = false)  //-- Sim / Nao
    public String carroExtra;

    //------------ precos disponibilizados para este servico (uma lista tipo (Conv. R$ 36,008, Exec. R$ 42,30, Leito. R$ 105,90..) ------------//
    //
    @Column(name = "preco_servico", nullable = false)
    public Double precoServico;

    //--------------------------------------- operacao ------------------------------------------//
    //-- figura tipo calendario p/escolha de um dia  da semana: S  T  Q  Q  S  S  D, ====>>>> ver componente para isso.
    @Column(name = "dia_semana_operacao", nullable = false) //-- figura tipo calendario p/escolha de um dia  da semana: S  T  Q  Q  S  S  D
    public String diaSemanaOperacao; //-- SEGUNDA/TERCA/QUARTA/QUINTA/SEXTA/SABADO/DOMINGO

    @Column(name = "data_inicio_operacao", nullable=false)
    public LocalDate dataInicioOperacao;

    @Column(name = "possui_data_termino", nullable=false)
    public String possuiDataTermino;  //-- Sim / Nao

    @Column(name = "data_fim_operacao")
    public LocalDate dataFimOperacao;   //-- se opcao "possuidataTermino" = Sim, acessar este.

    @Column(name = "opera_em_feriados", nullable=false)
    public String operaEmFeriados;  //-- Opera todos os dias / Opera apenas em feriados / Nao opera em feriados.

    //--------------------------------------- troca veiculo ------------------------------------------//
    @Column(name = "data_troca_veiculo")
    public LocalDate dataTrocaVeiculo;

    //@NotEmpty (message = "Informe Veiculo Substituto (troca)")
    @ManyToOne
    @JoinColumn(name = "veiculo_troca_id")
    public Veiculo veiculoTroca;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "servico")
    public Set<PontoParadaServico> pontosParadaServico;

    public Servico() {}

    public static Servico of() {
        return new Servico();
    }


    @Override
    public String toString() {
        return "[Servico] " + nome + " - linha: " + linha.descricao + " prefixo - " + linha.prefixo + " horario: " + horario
                + " inicio: " + dataInicioOperacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Servico)) return false;
        if (!super.equals(o)) return false;
        Servico servico = (Servico) o;
        return java.util.Objects.equals(linha, servico.linha) &&
                java.util.Objects.equals(nome, servico.nome) &&
                java.util.Objects.equals(horario, servico.horario) &&
                java.util.Objects.equals(dataInicioOperacao, servico.dataInicioOperacao);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), linha, nome, horario, dataInicioOperacao);
    }
}
