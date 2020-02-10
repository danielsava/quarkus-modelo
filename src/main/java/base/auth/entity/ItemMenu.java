package base.auth.entity;

import base.entity.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="item_menu",
    uniqueConstraints =
        @UniqueConstraint(columnNames = { "pagina_id", "label" }),
    indexes = {
       @Index(columnList="label")
    }
)
public class ItemMenu extends BaseEntity {

	@NotNull(message = "Informe a View do Menu")
	@ManyToOne
    @JoinColumn(name = "pagina_id", nullable = false)
	public Pagina pagina;

	@NotEmpty(message="Informe o Label do Menu")
	@Column(name = "label", nullable=false)
    public String label;

    @Column(name="icone")
    public String icone;

    @Column(name = "renderizar")
    public Boolean renderizar;

    @Column(name="fator_ordenacao")
    public Integer fatorOrdenacao;

    @Column(name = "descricao")
    public String descricao;

	public ItemMenu() { }

	public static ItemMenu of() {
		return new ItemMenu();
	}


}
