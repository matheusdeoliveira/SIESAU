package br.com.siesau.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the situacao_atend database table.
 * 
 */
@Entity
@Table(name = "situacao_atend")
@NamedQuery(name = "SituacaoAtend.findAll", query = "SELECT s FROM SituacaoAtend s")
public class SituacaoAtend implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SITUACAO_ATEND_CDSITATEND_GENERATOR", sequenceName = "SEQUENCIA")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SITUACAO_ATEND_CDSITATEND_GENERATOR")
	@Column(name = "cd_sitatend", unique = true, nullable = false)
	private Integer cdSitatend;

	@Column
	private String situacao;

	// bi-directional many-to-one association to Atendimento
	@OneToMany(mappedBy = "situacaoAtend")
	private List<Atendimento> atendimentos;

	public SituacaoAtend() {
	}

	public Integer getCdSitatend() {
		return this.cdSitatend;
	}

	public void setCdSitatend(Integer cdSitatend) {
		this.cdSitatend = cdSitatend;
	}

	public String getSituacao() {
		return this.situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public List<Atendimento> getAtendimentos() {
		return this.atendimentos;
	}

	public void setAtendimentos(List<Atendimento> atendimentos) {
		this.atendimentos = atendimentos;
	}

	public Atendimento addAtendimento(Atendimento atendimento) {
		getAtendimentos().add(atendimento);
		atendimento.setSituacaoAtend(this);

		return atendimento;
	}

	public Atendimento removeAtendimento(Atendimento atendimento) {
		getAtendimentos().remove(atendimento);
		atendimento.setSituacaoAtend(null);

		return atendimento;
	}

	@Override
	public int hashCode() {
		return (cdSitatend == null) ? 0 : cdSitatend;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj instanceof SituacaoAtend) {
			return ((SituacaoAtend) obj).getCdSitatend().equals(this.cdSitatend);
		}
		return false;
	}

}