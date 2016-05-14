package br.com.siesau.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the exam_laud database table.
 * 
 */
@Entity
@Table(name="exam_laud")
@NamedQuery(name="ExamLaud.findAll", query="SELECT e FROM ExamLaud e")
public class ExamLaud implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EXAM_LAUD_ID_GENERATOR", sequenceName="SEQUENCIA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EXAM_LAUD_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	//bi-directional many-to-one association to Exame
	@ManyToOne
	@JoinColumn(name="cd_exame", insertable=false, updatable=false)
	private Exame exame1;

	//bi-directional many-to-one association to Exame
	@ManyToOne
	@JoinColumn(name="cd_exame")
	private Exame exame2;

	//bi-directional many-to-one association to Laudo
	@ManyToOne
	@JoinColumn(name="cd_laudo", insertable=false, updatable=false)
	private Laudo laudo1;

	//bi-directional many-to-one association to Laudo
	@ManyToOne
	@JoinColumn(name="cd_laudo")
	private Laudo laudo2;

	public ExamLaud() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Exame getExame1() {
		return this.exame1;
	}

	public void setExame1(Exame exame1) {
		this.exame1 = exame1;
	}

	public Exame getExame2() {
		return this.exame2;
	}

	public void setExame2(Exame exame2) {
		this.exame2 = exame2;
	}

	public Laudo getLaudo1() {
		return this.laudo1;
	}

	public void setLaudo1(Laudo laudo1) {
		this.laudo1 = laudo1;
	}

	public Laudo getLaudo2() {
		return this.laudo2;
	}

	public void setLaudo2(Laudo laudo2) {
		this.laudo2 = laudo2;
	}

}