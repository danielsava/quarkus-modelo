package base.auth.entity;

import base.entity.BaseEntity;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name="menu"
		, uniqueConstraints = 
			@UniqueConstraint(columnNames={"label", "menu_pai_id"})
		, indexes = { 
			@Index(columnList="label")
		}
)
public class Menu extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="Informe o Label do Menu")
	@Column(name = "label", nullable=false)
	public String label;
	
	@Column(name = "descricao")
	public String descricao;
	
	@Column(name="icone")
	public String icone;

    @Column(name="fator_ordenacao")
	public Integer fatorOrdenacao;
	
	@Valid
	@ManyToOne
    @JoinColumn(name="menu_pai_id")
	public Menu menuPai;
	
	@OneToMany(mappedBy="menuPai", orphanRemoval=true)
	public List<Menu> subMenus;

    @ManyToMany
    @JoinTable(
            name="m_menu_menuitem",
            joinColumns={@JoinColumn(name="menu_id", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="menu_item_id", referencedColumnName="id")})
	public List<ItemMenu> itensMenu;


	public Menu() { }

	public static Menu of() {
		return new Menu();
	}


}


