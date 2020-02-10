package entity;

import base.entity.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Entity
@Table(name="localidade_agencia")
public class LocalidadeAgencia extends BaseEntity {

	@NotEmpty (message = "Informe Ponto de Parada")
	@ManyToOne
	@JoinColumn(name = "ponto_parada_id", nullable = false)
	public PontoParada pontoParada;

	@NotEmpty(message="Informe o cep")
	@Column(name="cep", nullable=false)
	public String cep;

	@NotEmpty(message="Informe o endereco")
	@Column(name="endereco")
	public String endereco;

	@NotEmpty(message="Informe o numero")
	@Column(name="numero")
	public String numero;

	@NotEmpty(message="Informe o complemento")
	@Column(name="complemento")
	public String complemento;

	@NotEmpty(message="Informe o bairro")
	@Column(name="bairro")
	public String bairro;

	public LocalidadeAgencia() {}

	public static LocalidadeAgencia of() {
		return new LocalidadeAgencia();
	}

	@Override
	public String toString() {
		return "Local Agencia [codigo = " + pontoParada.codigo + ", nome = " + pontoParada.nome + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof LocalidadeAgencia)) return false;
		if (!super.equals(o)) return false;
		LocalidadeAgencia that = (LocalidadeAgencia) o;
		return Objects.equals(pontoParada, that.pontoParada) &&
				Objects.equals(cep, that.cep) &&
				Objects.equals(endereco, that.endereco);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), pontoParada, cep, endereco);
	}
}
