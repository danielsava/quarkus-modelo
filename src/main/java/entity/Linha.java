package entity;

import base.entity.BaseEntity;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="linha")
public class Linha extends BaseEntity {

    @NotEmpty(message="Informe a Descrição")
    @Column(name = "descricao", nullable = false)
    public String descricao;

    @NotEmpty(message="Informe o Prefixo")
    @Column(name = "prefixo", nullable = false)
    public String prefixo;

    @Valid
    @NotEmpty(message="Informe Tipo Serviço")
    @ManyToOne
    @JoinColumn(name = "tipo_servico_id")
    public TipoServico tipoServico;

    @Valid
    @NotEmpty(message="Informe Orgão Regulador")
    @ManyToOne
    @JoinColumn(name = "orgao_regulador_id")
    public OrgaoRegulador orgaoRegulador;

    @Valid
    @NotEmpty(message="Informe a Modalidade Transporte")
    @ManyToOne
    @JoinColumn(name = "modalidade_transporte_id")
    public ModalidadeTransporte modalidadeTransporte;

    @Valid
    @NotEmpty(message="Informe a CategoriaLinha")
    @ManyToOne
    @JoinColumn(name = "categoria_linha_id")
    public CategoriaLinha categoriaLinha;

    @NotEmpty(message="Informe Trajeto Opcional")
    @Column(name = "trajeto_opcional", nullable = false)
    public String trajetoOpcional;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "pontoParada")
    public Set<PontoParadaLinha> pontosParadaLinha;


	public Linha() {}

	public static Linha of() {
		return new Linha();
	}


    @Override
	public String toString() {
		return "Linha = [" + descricao + ", prefixo: " + prefixo + " servico: " + tipoServico.descricao + " Orgao: " + orgaoRegulador.sigla
                           + " categoria: " + categoriaLinha.nome +"]";
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Linha)) return false;
        if (!super.equals(o)) return false;
        Linha linha = (Linha) o;
        return Objects.equals(descricao, linha.descricao) &&
                Objects.equals(prefixo, linha.prefixo) &&
                Objects.equals(orgaoRegulador, linha.orgaoRegulador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), descricao, prefixo, orgaoRegulador);
    }
}
