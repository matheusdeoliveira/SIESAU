package br.com.siesau.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the atendimento database table.
 * 
 */
@Entity
@Table(name="atendimento")
@NamedQuery(name="Atendimento.findAll", query="SELECT a FROM Atendimento a")
public class Atendimento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ATENDIMENTO_CDATEND_GENERATOR", sequenceName="SEQUENCIA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ATENDIMENTO_CDATEND_GENERATOR")
	@Column(name="cd_atend", unique=true, nullable=false)
	private Integer cdAtend;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_atend")
	private Date dataAtend;

	@Temporal(TemporalType.TIMESTAMP)
	private Date hora;

	@Column
	private String texto;

	//bi-directional many-to-one association to AtendDoenca
	@OneToMany(mappedBy="atendimento1")
	private List<AtendDoenca> atendDoencas1;

	//bi-directional many-to-one association to AtendDoenca
	@OneToMany(mappedBy="atendimento2")
	private List<AtendDoenca> atendDoencas2;

	//bi-directional many-to-one association to AtendExame
	@OneToMany(mappedBy="atendimento1")
	private List<AtendExame> atendExames1;

	//bi-directional many-to-one association to AtendExame
	@OneToMany(mappedBy="atendimento2")
	private List<AtendExame> atendExames2;

	//bi-directional many-to-one association to Funcionario
	@ManyToOne
	@JoinColumn(name="cd_func")
	private Funcionario funcionario;

	//bi-directional many-to-one association to Paciente
	@ManyToOne
	@JoinColumn(name="cd_paciente")
	private Paciente paciente;

	//bi-directional many-to-one association to SituacaoAtend
	@ManyToOne
	@JoinColumn(name="cd_sitatend")
	private SituacaoAtend situacaoAtend;

	//bi-directional many-to-many association to Doenca
	@ManyToMany(mappedBy="atendimentos")
	private List<Doenca> doencas;

	//bi-directional many-to-many association to Exame
	@ManyToMany(mappedBy="atendimentos")
	private List<Exame> exames;

	//bi-directional many-to-one association to Receita
	@OneToMany(mappedBy="atendimento")
	private List<Receita> receitas;
	
	//bi-directional many-to-one association to Especialidade
	@ManyToOne
	@JoinColumn(name="cd_espec")
	private Especialidade especialidade;

	public Atendimento() {
	}
	
	

	public Atendimento(Integer cdAtend, Date dataAtend, Date hora, String texto) {
		super();
		this.cdAtend = cdAtend;
		this.dataAtend = dataAtend;
		this.hora = hora;
		this.texto = texto;
	}



	public Atendimento(Integer cdAtend, Date dataAtend, Date hora, String texto, List<AtendDoenca> atendDoencas1,
			List<AtendDoenca> atendDoencas2, List<AtendExame> atendExames1, List<AtendExame> atendExames2,
			Funcionario funcionario, Paciente paciente, SituacaoAtend situacaoAtend, List<Doenca> doencas,
			List<Exame> exames, List<Receita> receitas, Especialidade especialidade) {
		super();
		this.cdAtend = cdAtend;
		this.dataAtend = dataAtend;
		this.hora = hora;
		this.texto = texto;
		this.atendDoencas1 = atendDoencas1;
		this.atendDoencas2 = atendDoencas2;
		this.atendExames1 = atendExames1;
		this.atendExames2 = atendExames2;
		this.funcionario = funcionario;
		this.paciente = paciente;
		this.situacaoAtend = situacaoAtend;
		this.doencas = doencas;
		this.exames = exames;
		this.receitas = receitas;
		this.especialidade = especialidade;
	}



	@Override
	public String toString() {
		return "Atendimento [cdAtend=" + cdAtend + ", dataAtend=" + dataAtend + ", hora=" + hora + ", texto=" + texto
				+ "]";
	}



	public Integer getCdAtend() {
		return this.cdAtend;
	}

	public void setCdAtend(Integer cdAtend) {
		this.cdAtend = cdAtend;
	}

	public Date getDataAtend() {
		return this.dataAtend;
	}

	public void setDataAtend(Date dataAtend) {
		this.dataAtend = dataAtend;
	}

	public Date getHora() {
		return this.hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public String getTexto() {
		return this.texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public List<AtendDoenca> getAtendDoencas1() {
		return this.atendDoencas1;
	}

	public void setAtendDoencas1(List<AtendDoenca> atendDoencas1) {
		this.atendDoencas1 = atendDoencas1;
	}

	public AtendDoenca addAtendDoencas1(AtendDoenca atendDoencas1) {
		getAtendDoencas1().add(atendDoencas1);
		atendDoencas1.setAtendimento1(this);

		return atendDoencas1;
	}

	public AtendDoenca removeAtendDoencas1(AtendDoenca atendDoencas1) {
		getAtendDoencas1().remove(atendDoencas1);
		atendDoencas1.setAtendimento1(null);

		return atendDoencas1;
	}

	public List<AtendDoenca> getAtendDoencas2() {
		return this.atendDoencas2;
	}

	public void setAtendDoencas2(List<AtendDoenca> atendDoencas2) {
		this.atendDoencas2 = atendDoencas2;
	}

	public AtendDoenca addAtendDoencas2(AtendDoenca atendDoencas2) {
		getAtendDoencas2().add(atendDoencas2);
		atendDoencas2.setAtendimento2(this);

		return atendDoencas2;
	}

	public AtendDoenca removeAtendDoencas2(AtendDoenca atendDoencas2) {
		getAtendDoencas2().remove(atendDoencas2);
		atendDoencas2.setAtendimento2(null);

		return atendDoencas2;
	}

	public List<AtendExame> getAtendExames1() {
		return this.atendExames1;
	}

	public void setAtendExames1(List<AtendExame> atendExames1) {
		this.atendExames1 = atendExames1;
	}

	public AtendExame addAtendExames1(AtendExame atendExames1) {
		getAtendExames1().add(atendExames1);
		atendExames1.setAtendimento1(this);

		return atendExames1;
	}

	public AtendExame removeAtendExames1(AtendExame atendExames1) {
		getAtendExames1().remove(atendExames1);
		atendExames1.setAtendimento1(null);

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
		atendExames2.setAtendimento2(this);

		return atendExames2;
	}

	public AtendExame removeAtendExames2(AtendExame atendExames2) {
		getAtendExames2().remove(atendExames2);
		atendExames2.setAtendimento2(null);

		return atendExames2;
	}

	public Funcionario getFuncionario() {
		return this.funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Paciente getPaciente() {
		return this.paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public SituacaoAtend getSituacaoAtend() {
		return this.situacaoAtend;
	}

	public void setSituacaoAtend(SituacaoAtend situacaoAtend) {
		this.situacaoAtend = situacaoAtend;
	}

	public List<Doenca> getDoencas() {
		return this.doencas;
	}

	public void setDoencas(List<Doenca> doencas) {
		this.doencas = doencas;
	}

	public List<Exame> getExames() {
		return this.exames;
	}

	public void setExames(List<Exame> exames) {
		this.exames = exames;
	}

	public List<Receita> getReceitas() {
		return this.receitas;
	}

	public void setReceitas(List<Receita> receitas) {
		this.receitas = receitas;
	}

	public Receita addReceita(Receita receita) {
		getReceitas().add(receita);
		receita.setAtendimento(this);

		return receita;
	}

	public Receita removeReceita(Receita receita) {
		getReceitas().remove(receita);
		receita.setAtendimento(null);

		return receita;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}