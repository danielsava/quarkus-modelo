package entity;

import base.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

/*
	Modalidades;
		1 - Rodoviário;
		3 - Aquaviário;
		4 - Ferroviário.
 */
@Entity
@Table(name="modalidade_transporte")
public class ModalidadeTransporte extends BaseEntity {

	@NotEmpty(message="Informe o Codigo")
	@Column(name="codigo", nullable=false, unique=true)
	public String codigo;

	@NotEmpty(message="Informe o Nome")
	@Column(name="nome", nullable=false, unique=true)
	public String nome;

	public ModalidadeTransporte() {}

	public static ModalidadeTransporte of() {
		return new ModalidadeTransporte();
	}


	@Override
	public String toString() {
		return "Modalidade Transporte [codigo = " + codigo + ", nome = " + nome + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		ModalidadeTransporte that = (ModalidadeTransporte) o;
		return Objects.equals(codigo, that.codigo) &&
				Objects.equals(nome, that.nome);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), codigo, nome );
	}


}
