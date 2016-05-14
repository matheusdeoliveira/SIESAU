package br.com.siesau.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cid_10_categ database table.
 * 
 */
@Entity
@Table(name="cid_10_categ")
@NamedQuery(name="Cid10Categ.findAll", query="SELECT c FROM Cid10Categ c")
public class Cid10Categ implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CID_10_CATEG_CDCID10CATEG_GENERATOR", sequenceName="SEQUENCIA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CID_10_CATEG_CDCID10CATEG_GENERATOR")
	@Column(name="cd_cid_10_categ", unique=true, nullable=false)
	private Integer cdCid10Categ;

	@Column
	private String cat;

	@Column
	private String classif;

	@Column
	private String descrabrev;

	@Column
	private String descricao;

	@Column
	private String excluidos;

	@Column
	private String refer;

	//bi-directional many-to-one association to Doenca
	@OneToMany(mappedBy="cid10Categ")
	private List<Doenca> doencas;

	public Cid10Categ() {
	}

	public Integer getCdCid10Categ() {
		return this.cdCid10Categ;
	}

	public void setCdCid10Categ(Integer cdCid10Categ) {
		this.cdCid10Categ = cdCid10Categ;
	}

	public String getCat() {
		return this.cat;
	}

	public void setCat(String cat) {
		this.cat = cat;
	}

	public String getClassif() {
		return this.classif;
	}

	public void setClassif(String classif) {
		this.classif = classif;
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

	public String getExcluidos() {
		return this.excluidos;
	}

	public void setExcluidos(String excluidos) {
		this.excluidos = excluidos;
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
		doenca.setCid10Categ(this);

		return doenca;
	}

	public Doenca removeDoenca(Doenca doenca) {
		getDoencas().remove(doenca);
		doenca.setCid10Categ(null);

		return doenca;
	}

}