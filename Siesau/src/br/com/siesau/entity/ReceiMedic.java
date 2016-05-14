package br.com.siesau.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the recei_medic database table.
 * 
 */
@Entity
@Table(name="recei_medic")
@NamedQuery(name="ReceiMedic.findAll", query="SELECT r FROM ReceiMedic r")
public class ReceiMedic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="RECEI_MEDIC_ID_GENERATOR", sequenceName="SEQUENCIA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RECEI_MEDIC_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	//bi-directional many-to-one association to Medicamento
	@ManyToOne
	@JoinColumn(name="cd_medicam", insertable=false, updatable=false)
	private Medicamento medicamento1;

	//bi-directional many-to-one association to Medicamento
	@ManyToOne
	@JoinColumn(name="cd_medicam")
	private Medicamento medicamento2;

	//bi-directional many-to-one association to Receita
	@ManyToOne
	@JoinColumn(name="cd_receita", insertable=false, updatable=false)
	private Receita receita1;

	//bi-directional many-to-one association to Receita
	@ManyToOne
	@JoinColumn(name="cd_receita")
	private Receita receita2;

	public ReceiMedic() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Medicamento getMedicamento1() {
		return this.medicamento1;
	}

	public void setMedicamento1(Medicamento medicamento1) {
		this.medicamento1 = medicamento1;
	}

	public Medicamento getMedicamento2() {
		return this.medicamento2;
	}

	public void setMedicamento2(Medicamento medicamento2) {
		this.medicamento2 = medicamento2;
	}

	public Receita getReceita1() {
		return this.receita1;
	}

	public void setReceita1(Receita receita1) {
		this.receita1 = receita1;
	}

	public Receita getReceita2() {
		return this.receita2;
	}

	public void setReceita2(Receita receita2) {
		this.receita2 = receita2;
	}

}