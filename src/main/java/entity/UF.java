package entity;

import base.entity.BaseEntity;
import base.entity.UpperCaseConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@Entity
@Table(name="uf")
public class UF extends BaseEntity {

    @NotEmpty(message="Informe a UF") @Pattern(regexp = "[a-zA-Z]{2}", message="UF inválida")
    @Convert(converter=UpperCaseConverter.class)
    @Column(name = "sigla", nullable=false, unique=true)
    public String sigla;

    @NotEmpty(message="Informe o nome")
    @Column(name = "nome", nullable=false, unique=true)
    public String nome;

    @NotEmpty(message="Informe o Coódigo do IBGE")
    @Pattern(regexp = "[0-9]{2}", message="Código IBGE inválido")
    @Column(name = "codg_ibge", nullable=false, unique=true)
    public String codgIBGE;

	public UF() {}

	public static UF of() {
		return new UF();
	}

    @Override
    public String toString() {
        return "UF [sigla =" + sigla + ", nome = " + nome + "]";
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UF)) return false;
        if (!super.equals(o)) return false;
        UF uf = (UF) o;
        return Objects.equals(sigla, uf.sigla) &&
                Objects.equals(nome, uf.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), sigla, nome);
    }

}
