package base.auth.entity;

import base.entity.BaseEntity;
import base.entity.UpperCaseConverter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="grupo_usuario")
public class GrupoUsuario extends BaseEntity {
	
	@NotEmpty(message="Informe o Nome do Grupo") @Size(min=4, message="'Nome do Grupo' com menos de 4 caracteres")
	@Column(name = "nome", nullable=false, unique=true)
	public String nome;
	
	@NotEmpty(message="Informe a Sigla do Grupo") @Size(min=2, message="'Sigla do Grupo' com menos de 2 caracteres")
	@Convert(converter= UpperCaseConverter.class)
	@Column(name = "sigla", nullable=false, unique=true)
	public String sigla;
	
	@Column(name = "descricao")
	public String descricao;

	@ManyToMany
	@JoinTable(
		name="m_menu_usuario",
		joinColumns={@JoinColumn(name="grupo_usuario_id", referencedColumnName="id")},
		inverseJoinColumns={@JoinColumn(name="menu_id", referencedColumnName="id")})
	public List<Menu> menus;
	
	@OneToMany(mappedBy = "grupoUsuario", orphanRemoval = true)
	public List<Permissao> permissoes;


	public GrupoUsuario() { }


	public static GrupoUsuario of() {
		return new GrupoUsuario();
	}
	

}
