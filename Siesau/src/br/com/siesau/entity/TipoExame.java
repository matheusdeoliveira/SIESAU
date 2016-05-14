package br.com.siesau.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipo_exame database table.
 * 
 */
@Entity
@Table(name="tipo_exame")
@NamedQuery(name="TipoExame.findAll", query="SELECT t FROM TipoExame t")
public class TipoExame implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TIPO_EXAME_CDTIPOEXAME_GENERATOR", sequenceName="SEQUENCIA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TIPO_EXAME_CDTIPOEXAME_GENERATOR")
	@Column(name="cd_tipoexame", unique=true, nullable=false)
	private Integer cdTipoexame;

	@Column
	private String tipo;

	//bi-directional many-to-one association to Exame
	@OneToMany(mappedBy="tipoExame")
	private List<Exame> exames;

	public TipoExame() {
	}

	public Integer getCdTipoexame() {
		return this.cdTipoexame;
	}

	public void setCdTipoexame(Integer cdTipoexame) {
		this.cdTipoexame = cdTipoexame;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Exame> getExames() {
		return this.exames;
	}

	public void setExames(List<Exame> exames) {
		this.exames = exames;
	}

	public Exame addExame(Exame exame) {
		getExames().add(exame);
		exame.setTipoExame(this);

		return exame;
	}

	public Exame removeExame(Exame exame) {
		getExames().remove(exame);
		exame.setTipoExame(null);

		return exame;
	}

}