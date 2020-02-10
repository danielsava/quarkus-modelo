package base.auth.entity;

import base.entity.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name="conta_usuario"
	, indexes = {
			@Index(columnList="login")
	}
)
public class ContaUsuario extends BaseEntity {

	@NotEmpty(message="Informe um Login") @Size(min=2, message="Login deve ter no mínimo 2 caracteres")
	@Column(name = "login", nullable=false, unique=true)
	public String login;

	@NotEmpty(message="Informe a senha") @Size(min=6, message="Senha deve ter no mínimo 6 caracteres")
	@Column(name = "senha", nullable=false)
	public String senha; //
	
	@NotNull(message="Informe a data de atualização da senha")
	@Column(name = "data_senha", nullable=false)
	public LocalDate dataSenha;

	@NotNull(message="Informar se o usuário deve alterar senha")
	@Column(name = "alterar_senha", nullable=false)
	public Boolean alterarSenha;

	@Column(name = "conta_bloqueada", nullable=false)
	public Boolean contaBloqueada;

	//@Future(message="'Data Expirar Conta' não pode estar no passado")
	@Column(name = "data_expira_conta")
	public LocalDateTime dataExpiraConta;

	@NotNull(message="Informar o Login Modo Debug")
	@Column(name = "login_modo_debug", nullable=false)
	public Boolean loginModoDebug;

	//@Future(message="'Data Expirar Conta' não pode estar no passado")
	@Column(name = "data_ultimo_login")
	public LocalDateTime dataUltimoLogin;

	@Lob
	@Column(name = "foto")
	public byte[] foto;

	@NotNull(message="Informe o Grupo De Usuário")
	@ManyToOne
	@JoinColumn(name="grupo_usuario_id")
	public GrupoUsuario grupo;

	@ManyToOne
	@JoinColumn(name = "menu_item_id")
	public ItemMenu paginaInicial;

	@Column(name = "tempo_sessao_minutos")
	public Integer tempoSessaoMinutos; // expirationTime do Token

	public ContaUsuario() { }

	public static ContaUsuario of() {
		return new ContaUsuario();
	}

	public ContaUsuario login(String login) {
		this.login = login;
		return this;
	}

	public ContaUsuario senha(String senha) {
		this.senha = senha;
		return this;
	}

	public boolean contaExpirada() {
		return dataExpiraConta != null && LocalDateTime.now().isBefore(dataExpiraConta);
	}

	public boolean contaBloqueada() {
		return contaBloqueada != null && contaBloqueada;
	}

	public int tempoSessaoMin() {
		return tempoSessaoMinutos == null ? 60 : tempoSessaoMinutos;
	}

}
