package entity;

import base.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

/*
Orgão Reguladores:
    ANTT - Agencia Nacional Tranporte Terreste
    AGR - Agencia Goiana de Regulação
*/

@Entity
@Table(name="orgao_regulador")
public class OrgaoRegulador extends BaseEntity {

	@NotEmpty(message="Informe a sigla")
	@Column(name="sigla", nullable=false, unique=true)
	public String sigla;

	@NotEmpty(message="Informe o nome")
	@Column(name="nome", nullable=false, unique=true)
	public String nome;

	public OrgaoRegulador() {}

	public static OrgaoRegulador of() {
		return new OrgaoRegulador();
	}


	@Override
	public String toString() {
		return "Orgão Regulador [sigla =" + sigla + ", nome = " + nome + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		OrgaoRegulador that = (OrgaoRegulador) o;
		return Objects.equals(sigla, that.sigla) &&
				Objects.equals(nome, that.nome);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), sigla, nome );
	}


}
