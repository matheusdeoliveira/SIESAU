package br.com.siesau.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cid_10_capitulo database table.
 * 
 */
@Entity
@Table(name="cid_10_capitulo")
@NamedQuery(name="Cid10Capitulo.findAll", query="SELECT c FROM Cid10Capitulo c")
public class Cid10Capitulo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CID_10_CAPITULO_CDCAPITULO_GENERATOR", sequenceName="SEQUENCIA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CID_10_CAPITULO_CDCAPITULO_GENERATOR")
	@Column(name="cd_capitulo", unique=true, nullable=false)
	private Integer cdCapitulo;

	@Column
	private String catfim;

	@Column
	private String catinic;

	@Column
	private String descrabrev;

	@Column
	private String descricao;

	private Integer numcap;

	//bi-directional many-to-one association to Doenca
	@OneToMany(mappedBy="cid10Capitulo")
	private List<Doenca> doencas;

	public Cid10Capitulo() {
	}

	public Integer getCdCapitulo() {
		return this.cdCapitulo;
	}

	public void setCdCapitulo(Integer cdCapitulo) {
		this.cdCapitulo = cdCapitulo;
	}

	public String getCatfim() {
		return this.catfim;
	}

	public void setCatfim(String catfim) {
		this.catfim = catfim;
	}

	public String getCatinic() {
		return this.catinic;
	}

	public void setCatinic(String catinic) {
		this.catinic = catinic;
	}

	public String getDescrabrev() {
		return this.descrabrev;
	}

	public void setDescrabrev(String descrabrev) {
		this.descrabrev = descrabrev;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getNumcap() {
		return this.numcap;
	}

	public void setNumcap(Integer numcap) {
		this.numcap = numcap;
	}

	public List<Doenca> getDoencas() {
		return this.doencas;
	}

	public void setDoencas(List<Doenca> doencas) {
		this.doencas = doencas;
	}

	public Doenca addDoenca(Doenca doenca) {
		getDoencas().add(doenca);
		doenca.setCid10Capitulo(this);

		return doenca;
	}

	public Doenca removeDoenca(Doenca doenca) {
		getDoencas().remove(doenca);
		doenca.setCid10Capitulo(null);

		return doenca;
	}

}