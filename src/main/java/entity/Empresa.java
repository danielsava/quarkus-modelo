package entity;

import base.entity.BaseEntity;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;
import java.util.Set;

/*
		Regime Tributario: (criar este, ENUM???)
			1 – Simples Nacional;
			2 – Simples Nacional – excesso de sublimite de receita bruta;
			3 – Regime Normal.

 */
@Entity
@Table(name="empresa")
public class Empresa extends BaseEntity {

    @NotEmpty(message="Informe o Nome Fantasia")
    @Column(name = "nome_fantasia", nullable = false)
    public String nomeFantasia;

    @NotEmpty(message="Informe a Razão Social")
    @Column(name = "razao_social", nullable = false)
    public String razaoSocial;

    @NotEmpty(message="Informe o CNPJ")
    @CNPJ(message = "CNPJ Inválido")
    @Column(name = "cnpj", nullable = false)
    public String cnpj;

    @NotEmpty(message="Informe a Inscrição Estadual")
    @Column(name = "inscricao_estadual", nullable = false)
    public String inscricaoEstadual;

    @OneToMany(orphanRemoval = true, mappedBy = "empresa")
    public Set<Veiculo> veiculos;

	public Empresa() {}

	public static Empresa of() {
		return new Empresa();
	}

	@Override
	public String toString() {
		return "Empresa [CNPJ =" + cnpj + ", nome = " + nomeFantasia + "]";
	}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Empresa)) return false;
        if (!super.equals(o)) return false;
        Empresa empresa = (Empresa) o;
        return Objects.equals(razaoSocial, empresa.razaoSocial) &&
                Objects.equals(cnpj, empresa.cnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), razaoSocial, cnpj);
    }

}
