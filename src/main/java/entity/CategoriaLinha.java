package entity;

import base.entity.BaseEntity;
import com.google.common.base.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/*
 Categorias de Transportes;
    - INTERESTADUAL
    - MUNICIPAL
    - INTERNACIONAL
    - INTERMUNICIPAL
*/

@Entity
@Table(name = "categoria_linha")
public class CategoriaLinha extends BaseEntity {

    @NotEmpty(message="Informe o nome")
    @Column(name="nome", nullable=false, unique=true)
    public String nome;

    public CategoriaLinha() {}

    public static CategoriaLinha of() {
        return new CategoriaLinha();
    }

    @Override
    public String toString() {
        return "Categoria Linha [" + nome + "]";
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoriaLinha)) return false;
        if (!super.equals(o)) return false;
        CategoriaLinha that = (CategoriaLinha) o;
        return Objects.equal(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), nome);
    }

}
