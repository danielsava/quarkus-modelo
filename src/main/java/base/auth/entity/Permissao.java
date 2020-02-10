package base.auth.entity;

import base.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name="permissao_grupo_usuario")
public class Permissao extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "grupo_usuario_id", nullable=false)
	public GrupoUsuario grupoUsuario;

	@ManyToOne
	@JoinColumn(name = "pagina_funcionalidade_id", nullable=false)
	public PaginaFuncionalidade paginaFuncionalidade;


	public Permissao() { }

	public static Permissao of() {
		return new Permissao();
	}


}
