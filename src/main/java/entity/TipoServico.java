package entity;

import base.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

/*
Tipos Servicos;
    1-Convencional com sanitário,
    2-Convencional sem sanitário,
    3-Semileito,
    4-Leito com ar condicionado,
    5-Leito sem ar condicionado,
    6-Executivo,
    7-Semiurbano,
    8-Longitudinal,
    9-Travessia
 */
@Entity
@Table(name="tipo_servico")
public class TipoServico extends BaseEntity {

	@NotEmpty(message="Informe o Código")
	@Column(name="codigo", nullable=false, unique=true)
	public String codigo;

	@NotEmpty(message="Informe a Descrição")
	@Column(name="descricao", nullable=false, unique=true)
	public String descricao;

	public TipoServico() {}

	public static TipoServico of() {
		return new TipoServico();
	}


	@Override
	public String toString() {
		return "Tipo Serviço [codigo =" + codigo + ", descrição = " + descricao + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof TipoServico)) return false;
		if (!super.equals(o)) return false;
		TipoServico that = (TipoServico) o;
		return Objects.equals(codigo, that.codigo) &&
				Objects.equals(descricao, that.descricao);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), codigo, descricao);
	}
}
