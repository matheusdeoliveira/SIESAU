package br.com.siesau.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cid_o_categ database table.
 * 
 */
@Entity
@Table(name="cid_o_categ")
@NamedQuery(name="CidOCateg.findAll", query="SELECT c FROM CidOCateg c")
public class CidOCateg implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CID_O_CATEG_CDCIDOCATEG_GENERATOR", sequenceName="SEQUENCIA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CID_O_CATEG_CDCIDOCATEG_GENERATOR")
	@Column(name="cd_cid_o_categ", unique=true, nullable=false)
	private Integer cdCidOCateg;

	@Column
	private String cat;

	@Column
	private String descricao;

	@Column
	private String refer;

	//bi-directional many-to-one association to Doenca
	@OneToMany(mappedBy="cidOCateg")
	private List<Doenca> doencas;

	public CidOCateg() {
	}

	public Integer getCdCidOCateg() {
		return this.cdCidOCateg;
	}

	public void setCdCidOCateg(Integer cdCidOCateg) {
		this.cdCidOCateg = cdCidOCateg;
	}

	public String getCat() {
		return this.cat;
	}

	public void setCat(String cat) {
		this.cat = cat;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getRefer() {
		return this.refer;
	}

	public void setRefer(String refer) {
		this.refer = refer;
	}

	public List<Doenca> getDoencas() {
		return this.doencas;
	}

	public void setDoencas(List<Doenca> doencas) {
		this.doencas = doencas;
	}

	public Doenca addDoenca(Doenca doenca) {
		getDoencas().add(doenca);
		doenca.setCidOCateg(this);

		return doenca;
	}

	public Doenca removeDoenca(Doenca doenca) {
		getDoencas().remove(doenca);
		doenca.setCidOCateg(null);

		return doenca;
	}

}