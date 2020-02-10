package entity;

import base.entity.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

/*
	Conforme manual MOC_BPe_Anexo I_Leiaute_v1.00b, nao tem o atributo `descricao`, apenas o `nome` ou `razao social`.

 */
@Entity
@Table(name="agencia")
public class Agencia extends BaseEntity {

	@NotEmpty(message="Informe o codigo")
	@Column(name="codigo", nullable=false)
	public String codigo;

	@NotEmpty(message="Informe o nome ou razao social")
	@Column(name="nome", nullable=false)
	public String nome;                    //-- nome ou razao social.

	@NotEmpty(message="Informe o cnpj")
	@Column(name="cnpj", nullable=false)
	public String cnpj;

	@NotEmpty(message="Informe o 'DDD' do Telefone")
	@Column(name="ddd_telefone")
	public String dddTelefone;

	@NotEmpty(message="Informe o 'Número' do Telefone")
	@Column(name="numero_telefone")
	public String numeroTelefone;

	@NotEmpty(message="Informe o Email")
	@Email(message="Email inválido")
	@Column(name = "email", nullable = false, unique=true)
	public String email;

	@NotEmpty(message="Informar se Agência é Própria ou Terceirazada")
	@Column(name = "tipo_agencia", nullable=false)
	public String tipoAgencia;                      		  //-- Propria ou Terceirizada

	@NotEmpty(message="Informar se caixa diario fecha automatico")
	@Column(name = "fechar_cx_diario_automaticamente")
	public Boolean fecharCxDiarioAutomaticamente;                 //-- Sim/Nao

	@NotEmpty(message="Informar se fechar apenas caixa conferidos")
	@Column(name = "fechar_cx_diario_conferido")
	public Boolean fecharCxDiarioConferido;                  //-- Sim/Nao

	@NotEmpty(message="Informar Viação(s) parceiras")
	@Column(name="viacao_parceira", nullable=false)
	public String viacaoParceira;                    //-- informar obrigatoriamente no minimo a empresa atual = Viacao Goianesia Ltda

	//-- configuração Boletos Bancarios
	@NotEmpty(message="Informe a Identificacao do Cedente)")
	@Column(name="identificador_cedente")
	public String identificadorCedente;

	@NotEmpty(message="Informe a Identificacao do Cedente)")
	@Column(name="dias_semana_gerar_boleto")      //-- componente tipo calendario p/escolha de um dia  da semana: S  T  Q  Q  S  S  D
	public String diasSemanaGerarBoleto;          //-- valor do atributo: SEGUNDA/TERCA/QUARTA/QUINTA/SEXTA/SABADO/DOMINGO

	@NotEmpty (message = "Informe a Localidade")
	@ManyToOne
	@JoinColumn(name = "localidade_agencia_id", nullable = false)
	public LocalidadeAgencia localidadeAgencia;

	public Agencia() {}

	public static Agencia of() {
		return new Agencia();
	}

	@Override
	public String toString() {
		return "Agencia [codigo: " + codigo + ", nome: " + nome + ", cnpj: " + cnpj +"]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Agencia)) return false;
		if (!super.equals(o)) return false;
		Agencia agencia = (Agencia) o;
		return Objects.equals(codigo, agencia.codigo) &&
				Objects.equals(nome, agencia.nome) &&
				Objects.equals(cnpj, agencia.cnpj) &&
				Objects.equals(localidadeAgencia, agencia.localidadeAgencia);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), codigo, nome, cnpj, localidadeAgencia);
	}
}
