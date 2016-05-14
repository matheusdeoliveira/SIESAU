package br.com.siesau.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the est_medic database table.
 * 
 */
@Entity
@Table(name="est_medic")
@NamedQuery(name="EstMedic.findAll", query="SELECT e FROM EstMedic e")
public class EstMedic implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EstMedicPK id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	@Column
	private String lote;

	@Temporal(TemporalType.TIMESTAMP)
	private Date validade;

	public EstMedic() {
	}

	public EstMedicPK getId() {
		return this.id;
	}

	public void setId(EstMedicPK id) {
		this.id = id;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getLote() {
		return this.lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public Date getValidade() {
		return this.validade;
	}

	public void setValidade(Date validade) {
		this.validade = validade;
	}

}