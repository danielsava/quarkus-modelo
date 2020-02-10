package base.auth.entity;

import base.entity.BaseEntity;

import javax.persistence.*;
import java.util.List;


/**
 *
 * 	Representa as Funcionalidades que cada Página possui. É uma tabela de ligação entre Página e Funcionalidade
 *
 *
 *
 */

@Entity
@Table(name="pagina_funcionalidade",
	uniqueConstraints =
		@UniqueConstraint(columnNames = { "pagina_id", "funcionalidade_id" })
)
public class PaginaFuncionalidade extends BaseEntity {

	@ManyToOne
	@JoinColumn(name="pagina_id", nullable = false)
	public Pagina pagina;
	
	@ManyToOne
	@JoinColumn(name="funcionalidade_id", nullable = false)
	public Funcionalidade funcionalidade;

	@OneToMany(mappedBy = "paginaFuncionalidade", orphanRemoval = true) // Caso esta funcionalidade da página seja removida, a permissão para esta funcionalidade também deverá ser removida. (Não faz sentido existir permissão para uma funcionalidade que não existe)
	public List<Permissao> permissoesGrupoUsuario;
	

	public PaginaFuncionalidade() { }


}
