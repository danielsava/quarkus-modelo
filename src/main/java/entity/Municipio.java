package entity;

import base.entity.BaseEntity;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@Entity
@Table(name = "municipio",
        uniqueConstraints = @UniqueConstraint(columnNames={"nome","uf_id"}),
        indexes = {
                @Index(name = "index_nome", columnList = "nome"),
                @Index(name = "index_nome_uf", columnList = "nome, uf_id")
        })
public class Municipio extends BaseEntity {

    @NotEmpty(message="Selecione uma UF")
    @Valid
    @ManyToOne
    @JoinColumn(name="uf_id", nullable=false)
    public UF uf;

    @NotEmpty(message="Informe o Nome do Município") @Pattern(regexp = "[A-Z ]+", message="Valor Inválido - Não utilizar caracteres especiais, com acentos, numeros ou cedilha")
    @Column(name = "nome", nullable=false)
    public String nome;

    //
    // Codigo IBGE do Municipio 7 digitos sendo os 2 primeiros referente a UF, necessario(obrigatorio) na emissao de NFSe,
    // diferente do codigo IBGE do CEP (endereco) para emissao da NFe
    //
    // Ex: 5208707 - Goiania, 5201405 - Aparecida, 5300108 - Brasilia-DF, 3108602 - Brasilia de Minas, 3553955 - Taruma-SP, etc..
    //
    @NotEmpty(message="Informe o Código do IBGE")
    @Pattern(regexp = "[0-9]{7}", message="Código IBGE inválido")
    @Column(name = "codg_ibge", nullable=false, unique=true)
    public String codgIBGE;

	public Municipio() {}

	public static Municipio of() {
		return new Municipio();
	}

    public Municipio(UF uf, String nome, String sigla) {
        super();
        this.uf = uf;
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Municipio [ nome = " + nome + ", UF = " + uf.sigla + "]";
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Municipio)) return false;
        if (!super.equals(o)) return false;
        Municipio uf = (Municipio) o;
        return Objects.equals(nome, uf.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), uf.nome, nome);
    }

}
