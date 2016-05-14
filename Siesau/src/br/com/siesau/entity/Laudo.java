package br.com.siesau.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the laudo database table.
 * 
 */
@Entity
@Table(name="laudo")
@NamedQuery(name="Laudo.findAll", query="SELECT l FROM Laudo l")
public class Laudo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LAUDO_CDLAUDO_GENERATOR", sequenceName="SEQUENCIA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LAUDO_CDLAUDO_GENERATOR")
	@Column(name="cd_laudo", unique=true, nullable=false)
	private Integer cdLaudo;

	@Column
	private String resultado;

	//bi-directional many-to-one association to ExamLaud
	@OneToMany(mappedBy="laudo1")
	private List<ExamLaud> examLauds1;

	//bi-directional many-to-one association to ExamLaud
	@OneToMany(mappedBy="laudo2")
	private List<ExamLaud> examLauds2;

	//bi-directional many-to-many association to Exame
	@ManyToMany
	@JoinTable(
		name="exam_laud"
		, joinColumns={
			@JoinColumn(name="cd_laudo")
			}
		, inverseJoinColumns={
			@JoinColumn(name="cd_exame")
			}
		)
	private List<Exame> exames;

	public Laudo() {
	}

	public Integer getCdLaudo() {
		return this.cdLaudo;
	}

	public void setCdLaudo(Integer cdLaudo) {
		this.cdLaudo = cdLaudo;
	}

	public String getResultado() {
		return this.resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public List<ExamLaud> getExamLauds1() {
		return this.examLauds1;
	}

	public void setExamLauds1(List<ExamLaud> examLauds1) {
		this.examLauds1 = examLauds1;
	}

	public ExamLaud addExamLauds1(ExamLaud examLauds1) {
		getExamLauds1().add(examLauds1);
		examLauds1.setLaudo1(this);

		return examLauds1;
	}

	public ExamLaud removeExamLauds1(ExamLaud examLauds1) {
		getExamLauds1().remove(examLauds1);
		examLauds1.setLaudo1(null);

		return examLauds1;
	}

	public List<ExamLaud> getExamLauds2() {
		return this.examLauds2;
	}

	public void setExamLauds2(List<ExamLaud> examLauds2) {
		this.examLauds2 = examLauds2;
	}

	public ExamLaud addExamLauds2(ExamLaud examLauds2) {
		getExamLauds2().add(examLauds2);
		examLauds2.setLaudo2(this);

		return examLauds2;
	}

	public ExamLaud removeExamLauds2(ExamLaud examLauds2) {
		getExamLauds2().remove(examLauds2);
		examLauds2.setLaudo2(null);

		return examLauds2;
	}

	public List<Exame> getExames() {
		return this.exames;
	}

	public void setExames(List<Exame> exames) {
		this.exames = exames;
	}

}