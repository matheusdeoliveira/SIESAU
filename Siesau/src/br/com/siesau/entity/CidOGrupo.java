package br.com.siesau.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cid_o_grupo database table.
 * 
 */
@Entity
@Table(name="cid_o_grupo")
@NamedQuery(name="CidOGrupo.findAll", query="SELECT c FROM CidOGrupo c")
public class CidOGrupo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CID_O_GRUPO_CDCIDOGRUPO_GENERATOR", sequenceName="SEQUENCIA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CID_O_GRUPO_CDCIDOGRUPO_GENERATOR")
	@Column(name="cd_cid_o_grupo", unique=true, nullable=false)
	private Integer cdCidOGrupo;

	@Column
	private String catfim;

	@Column
	private String catinic;

	@Column
	private String descricao;

	@Column
	private String refer;

	//bi-directional many-to-one association to Doenca
	@OneToMany(mappedBy="cidOGrupo")
	private List<Doenca> doencas;

	public CidOGrupo() {
	}

	public Integer getCdCidOGrupo() {
		return this.cdCidOGrupo;
	}

	public void setCdCidOGrupo(Integer cdCidOGrupo) {
		this.cdCidOGrupo = cdCidOGrupo;
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
		doenca.setCidOGrupo(this);

		return doenca;
	}

	public Doenca removeDoenca(Doenca doenca) {
		getDoencas().remove(doenca);
		doenca.setCidOGrupo(null);

		return doenca;
	}

}