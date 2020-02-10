package entity;

import base.auth.entity.ContaUsuario;
import base.entity.BaseEntity;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name="usuario_sistema")
public class UsuarioSistema extends BaseEntity {
	
	//@ManyToOne
	//@JoinColumn(name="id_agencia")
	//private Agencia agencia;

	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="login_id")
	public ContaUsuario login;

	@NotBlank(message="Informe o Nome") @Size(min=2, message="'Nome' pouco significativo")
	@Column(name = "nome", nullable=false)
	public String nome;

	@NotBlank(message="Informe o Sobrenome") @Size(min=2, message="'Sobrenome' pouco significativo")
	@Column(name = "sobrenome", nullable = false)
	public String sobrenome;

	@NotBlank(message="Informe o Email") @Email(message="Email inválido")
	@Column(name = "email", nullable = false, unique=true)
	public String email;

	@NotBlank(message="Informe o CPF")
	@CPF(message="CPF inválido") //@CPF, @CNPJ e @TituloEleitoral (Hibernate), @IE(Caelum Stella, https://github.com/caelum/caelum-stella/wiki/Anotando-o-modelo)
	@Column(name = "cpf", nullable = false, unique = true)
	private String cpf; // Força a criar somente um Usuário de Sistema por Login


	public UsuarioSistema() { }

	public static UsuarioSistema of() {
        return new UsuarioSistema();
    }
	

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		UsuarioSistema that = (UsuarioSistema) o;
		return Objects.equals(login, that.login) &&
				Objects.equals(nome, that.nome) &&
				Objects.equals(sobrenome, that.sobrenome) &&
				Objects.equals(email, that.email) &&
				Objects.equals(cpf, that.cpf);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), login, nome, sobrenome, email, cpf);
	}

}
