package base.auth.entity;

import base.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="funcionalidade",
	indexes = {
		@Index(columnList="nome")
	}
)
public class Funcionalidade extends BaseEntity {

	@NotEmpty(message="Informe o Nome da Funcionalidade")
	@Column(name="nome", nullable=false, unique=true)
	public String nome;

	@NotEmpty(message="Informe a Descrição da Funcionalidade")
	@Column(name="descricao")
	public String descricao;


	public Funcionalidade() {}


	public static Funcionalidade of() {
		return new Funcionalidade();
	}


}
