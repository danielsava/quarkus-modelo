package base.auth.entity;

import base.entity.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name="pagina",
	indexes = {
		@Index(columnList="url")
	}
)
public class Pagina extends BaseEntity {

	@NotEmpty(message="Informe a url da página")
	@Column(name="url", nullable=false, unique = true)
	public String url;	// Rota do Angular
	
	@Column(name="descricao")
	public String descricao;

	@OneToMany (mappedBy = "pagina", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<PaginaFuncionalidade> funcionalidades;


	public Pagina() {}


	public static Pagina of() {
		return new Pagina();
	}


}
