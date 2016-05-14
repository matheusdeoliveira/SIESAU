package br.com.siesau.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the exame database table.
 * 
 */
@Entity
@Table(name="exame")
@NamedQuery(name="Exame.findAll", query="SELECT e FROM Exame e")
public class Exame implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EXAME_CDEXAME_GENERATOR", sequenceName="SEQUENCIA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EXAME_CDEXAME_GENERATOR")
	@Column(name="cd_exame", unique=true, nullable=false)
	private Integer cdExame;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	@Column
	private String nome;

	@Column
	private String obs;

	//bi-directional many-to-one association to AtendExame
	@OneToMany(mappedBy="exame1")
	private List<AtendExame> atendExames1;

	//bi-directional many-to-one association to AtendExame
	@OneToMany(mappedBy="exame2")
	private List<AtendExame> atendExames2;

	//bi-directional many-to-one association to ExamLaud
	@OneToMany(mappedBy="exame1")
	private List<ExamLaud> examLauds1;

	//bi-directional many-to-one association to ExamLaud
	@OneToMany(mappedBy="exame2")
	private List<ExamLaud> examLauds2;

	//bi-directional many-to-many association to Atendimento
	@ManyToMany
	@JoinTable(
		name="atend_exame"
		, joinColumns={
			@JoinColumn(name="cd_exame")
			}
		, inverseJoinColumns={
			@JoinColumn(name="cd_atend")
			}
		)
	private List<Atendimento> atendimentos;

	//bi-directional many-to-one association to TipoExame
	@ManyToOne
	@JoinColumn(name="cd_tipoexame")
	private TipoExame tipoExame;

	//bi-directional many-to-many association to Laudo
	@ManyToMany(mappedBy="exames")
	private List<Laudo> laudos;

	public Exame() {
	}

	public Integer getCdExame() {
		return this.cdExame;
	}

	public void setCdExame(Integer cdExame) {
		this.cdExame = cdExame;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getObs() {
		return this.obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public List<AtendExame> getAtendExames1() {
		return this.atendExames1;
	}

	public void setAtendExames1(List<AtendExame> atendExames1) {
		this.atendExames1 = atendExames1;
	}

	public AtendExame addAtendExames1(AtendExame atendExames1) {
		getAtendExames1().add(atendExames1);
		atendExames1.setExame1(this);

		return atendExames1;
	}

	public AtendExame removeAtendExames1(AtendExame atendExames1) {
		getAtendExames1().remove(atendExames1);
		atendExames1.setExame1(null);

		return atendExames1;
	}

	public List<AtendExame> getAtendExames2() {
		return this.atendExames2;
	}

	public void setAtendExames2(List<AtendExame> atendExames2) {
		this.atendExames2 = atendExames2;
	}

	public AtendExame addAtendExames2(AtendExame atendExames2) {
		getAtendExames2().add(atendExames2);
		atendExames2.setExame2(this);

		return atendExames2;
	}

	public AtendExame removeAtendExames2(AtendExame atendExames2) {
		getAtendExames2().remove(atendExames2);
		atendExames2.setExame2(null);

		return atendExames2;
	}

	public List<ExamLaud> getExamLauds1() {
		return this.examLauds1;
	}

	public void setExamLauds1(List<ExamLaud> examLauds1) {
		this.examLauds1 = examLauds1;
	}

	public ExamLaud addExamLauds1(ExamLaud examLauds1) {
		getExamLauds1().add(examLauds1);
		examLauds1.setExame1(this);

		return examLauds1;
	}

	public ExamLaud removeExamLauds1(ExamLaud examLauds1) {
		getExamLauds1().remove(examLauds1);
		examLauds1.setExame1(null);

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
		examLauds2.setExame2(this);

		return examLauds2;
	}

	public ExamLaud removeExamLauds2(ExamLaud examLauds2) {
		getExamLauds2().remove(examLauds2);
		examLauds2.setExame2(null);

		return examLauds2;
	}

	public List<Atendimento> getAtendimentos() {
		return this.atendimentos;
	}

	public void setAtendimentos(List<Atendimento> atendimentos) {
		this.atendimentos = atendimentos;
	}

	public TipoExame getTipoExame() {
		return this.tipoExame;
	}

	public void setTipoExame(TipoExame tipoExame) {
		this.tipoExame = tipoExame;
	}

	public List<Laudo> getLaudos() {
		return this.laudos;
	}

	public void setLaudos(List<Laudo> laudos) {
		this.laudos = laudos;
	}

}