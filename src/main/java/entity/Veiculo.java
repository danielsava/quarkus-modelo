package entity;

import base.entity.BaseEntity;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Entity
@Table(name="veiculo")
public class Veiculo extends BaseEntity {

    @Valid
    @NotEmpty(message="Informe a Empresa")
    @ManyToOne
    @JoinColumn(name = "empresa_id")
    public Empresa empresa;

    @NotEmpty(message="Informe o nome")
    @Column(name = "nome", nullable = false)
    public String nome;

    @Column(name="quantidade_poltrona")
    public Integer quantidadePoltrona;

    @Column(name="quantidade_eixo")
    public Integer quantidadeEixo;

	public Veiculo() {}

	public static Veiculo of() {
		return new Veiculo();
	}

    @Override
    public String toString() {
        return "Veiculo = " + nome + " " + quantidadePoltrona + " lugares " + quantidadeEixo + " eixos " ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Veiculo)) return false;
        if (!super.equals(o)) return false;
        Veiculo veiculo = (Veiculo) o;
        return Objects.equals(nome, veiculo.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nome);
    }

}
