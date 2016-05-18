package br.com.siesau.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.siesau.entity.AtendDoenca;
import br.com.siesau.entity.AtendExame;
import br.com.siesau.entity.Atendimento;
import br.com.siesau.entity.Doenca;
import br.com.siesau.entity.Exame;
import br.com.siesau.entity.Funcionario;
import br.com.siesau.entity.Laudo;
import br.com.siesau.entity.Medicamento;
import br.com.siesau.entity.Paciente;
import br.com.siesau.entity.ReceiMedic;
import br.com.siesau.entity.Receita;
import br.com.siesau.entity.SituacaoAtend;
import br.com.siesau.persistence.AtendDoencaDao;
import br.com.siesau.persistence.AtendExameDao;
import br.com.siesau.persistence.AtendimentoDao;
import br.com.siesau.persistence.DoencaDao;
import br.com.siesau.persistence.ExameDao;
import br.com.siesau.persistence.LaudoDao;
import br.com.siesau.persistence.MedicamentoDao;
import br.com.siesau.persistence.PacienteDao;
import br.com.siesau.persistence.ReceiMedicDao;
import br.com.siesau.persistence.ReceitaDao;

@ManagedBean(name = "mbProntuario")
@ViewScoped
public class ManagerBeanProntuario implements Serializable {

	private static final long serialVersionUID = 1L;
	private Paciente paciente;
	private int idade;
	private String itemSelecionado;
	private String campoBusca;
	private String campoAlergia;

	private Receita receita;

	private Medicamento medicamento;
	private List<Medicamento> medicamentos;

	private Funcionario funcionario;

	private Exame exame;
	private List<Exame> exames;

	private Laudo laudo;

	private Doenca doenca;
	private List<Doenca> doencas;
	private List<Doenca> doencasSelecionadas;

	private Atendimento atendimento;
	private List<Atendimento> atendimentosPorEspecialidade;

	private SituacaoAtend situacao;

	private List<AtendDoenca> atendDoencas;
	private AtendDoenca atendDoenca;

	private List<AtendExame> atendExames;
	private AtendExame atendExame;

	private ReceiMedic receiMedic;

	@PostConstruct
	public void init() {
		campoAlergia = "";
		idade = 0;
		exames = new ExameDao(new Exame()).lista();
		medicamentos = new MedicamentoDao(new Medicamento()).lista();
		receiMedic = new ReceiMedic();
		atendExame = new AtendExame();
		doencasSelecionadas = new ArrayList<>();
		atendDoenca = new AtendDoenca();
		atendDoencas = new AtendDoencaDao(new AtendDoenca()).lista();
		situacao = new SituacaoAtend();
		paciente = new Paciente();
		receita = new Receita();
		medicamento = new Medicamento();
		funcionario = new Funcionario();
		exame = new Exame();
		laudo = new Laudo();
		doenca = new Doenca();
		doencas = new DoencaDao(new Doenca()).lista();
		atendimento = new Atendimento();

		// Explicação : Diogo Freitas
		// Listagem de Atendimentos por Especialidade do Funcionario
		// É só passar o funcionario por parametro e ver a magica acontecer.

		// atendimentosPorEspecialidade = new AtendimentoDao(new
		// Atendimento()).listaPorEspecialidade(funcionario);
	}

	public void buscaPaciente() {
		FacesContext fc = FacesContext.getCurrentInstance();

		paciente = new Paciente();

		try {
			if (itemSelecionado.equals("rg")) {
				paciente = new PacienteDao(new Paciente()).pesquisaRG(campoBusca);
			}
			if (itemSelecionado.equals("cpf")) {
				paciente = new PacienteDao(new Paciente()).pesquisaCPF(campoBusca);
			}
			if (itemSelecionado.equals("sus")) {
				paciente = new PacienteDao(new Paciente()).pesquisaCartaoSUS(campoBusca);
			}
			if (itemSelecionado.equals("cdAtend")) {
				paciente = new AtendimentoDao(new Atendimento()).pesquisaCodigoAtendimento(Integer.parseInt(campoBusca)).getPaciente();
				atendimento = new AtendimentoDao(new Atendimento()).findByCode(Integer.parseInt(campoBusca));
				atendExames = new AtendExameDao(new AtendExame()).lista(atendimento);
				setIdade(paciente.getCdPaciente());;
			}

			atendimento.setPaciente(paciente);
			fc.addMessage("form1", new FacesMessage("Paciente encontrado."));

		} catch (Exception e) {
			fc.addMessage("form1", new FacesMessage("Error: " + e.getMessage()));
			e.printStackTrace();
		}
	}

	public void salvarReceita() {
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			atendimento = new AtendimentoDao(new Atendimento()).findByCode(atendimento.getCdAtend());

			medicamento = new MedicamentoDao(new Medicamento()).findByCode(medicamento.getCdMedicam());

			receiMedic.setReceita1(new Receita());
			receiMedic.setMedicamento1(new Medicamento());

			receita.setAtendimento(atendimento);
			receita.setData(new Date());

			new ReceitaDao(receita).salva(receita);
			receita = new ReceitaDao(new Receita()).findByAtendData(receita.getData(), atendimento);

			receiMedic.setReceita1(new Receita());
			receiMedic.setReceita1(receita);
			receiMedic.setReceita2(receita);
			receiMedic.setMedicamento1(new Medicamento());
			receiMedic.setMedicamento1(medicamento);
			receiMedic.setMedicamento2(medicamento);
			new ReceiMedicDao(new ReceiMedic()).salva(receiMedic);

			fc.addMessage("form1", new FacesMessage("Medicamento: " + medicamento.getNomeRef() + " receitado."));

		} catch (Exception e) {
			fc.addMessage("form1", new FacesMessage("Erro: " + e.getMessage() + "."));
			e.printStackTrace();
		}
		receita = new Receita();
		medicamento = new Medicamento();
	}

	public void salvarExame() {
		FacesContext fc = FacesContext.getCurrentInstance();

		try {

			atendimento = new AtendimentoDao(new Atendimento()).findByCode(Integer.parseInt(campoBusca));
			exame = new ExameDao(new Exame()).findByCode(exame.getCdExame());

			atendExame.setAtendimento2(atendimento);
			atendExame.setExame2(exame);

			new AtendExameDao(new AtendExame()).salva(atendExame);

			fc.addMessage("form1", new FacesMessage("Exame " + exame.getCdExame() + " salvo."));
		} catch (Exception e) {
			fc.addMessage("form1", new FacesMessage("Erro: " + e.getMessage() + "."));
			e.printStackTrace();
		}

		exame = new Exame();
		atendExame = new AtendExame();
	}

	public void salvarLaudo() {
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			atendimento = new AtendimentoDao(new Atendimento()).findByCode(Integer.parseInt(campoBusca));
			exame = new ExameDao(new Exame()).findByCode(exame.getCdExame());

			new LaudoDao(new Laudo()).salva(laudo);

			fc.addMessage("form_laudo", new FacesMessage("Laudo " + laudo.getCdLaudo() + " salvo."));

		} catch (Exception e) {
			fc.addMessage("form_laudo", new FacesMessage("Erro: " + e.getMessage() + "."));
			e.printStackTrace();
		}

		laudo = new Laudo();
		exame = new Exame();
	}

	public void salvarDoenca() {

		atendimento = new AtendimentoDao(new Atendimento()).findByCode(Integer.parseInt(campoBusca));

		FacesContext fc = FacesContext.getCurrentInstance();

		try {

			atendDoenca.setAtendimento2(atendimento);
			atendDoenca.setDoenca2(new DoencaDao(new Doenca()).pesquisaCID(doenca));

			new AtendDoencaDao(new AtendDoenca()).salva(atendDoenca);

			fc.addMessage("form_doenca", new FacesMessage("Doenca " + doenca.getNome() + " salva."));
		} catch (Exception e) {
			fc.addMessage("form_doenca", new FacesMessage("Erro: " + e.getMessage() + "."));
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		atendDoenca = new AtendDoenca();
		doenca = new Doenca();
	}

	public void finalizaAtendimento() {
		FacesContext fc = FacesContext.getCurrentInstance();
		atendimento = new AtendimentoDao(new Atendimento()).findByCode(Integer.parseInt(campoBusca));

		try {
			atendimento = new AtendimentoDao(new Atendimento()).pesquisaCodigoAtendimento(Integer.parseInt(campoBusca));
			situacao.setCdSitatend(3);
			atendimento.setSituacaoAtend(situacao);
			;

			new AtendimentoDao(new Atendimento()).atualiza(atendimento);
			fc.addMessage("form1", new FacesMessage("Atendimento " + atendimento.getCdAtend() + " finalizado."));
		} catch (Exception e) {
			fc.addMessage("form1", new FacesMessage("Erro : " + e.getMessage()));
		}
		init();
		campoBusca = "";
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public String getItemSelecionado() {
		return itemSelecionado;
	}

	public void setItemSelecionado(String itemSelecionado) {
		this.itemSelecionado = itemSelecionado;
	}

	public String getCampoBusca() {
		return campoBusca;
	}

	public void setCampoBusca(String campoBusca) {
		this.campoBusca = campoBusca;
	}

	public Receita getReceita() {
		return receita;
	}

	public void setReceita(Receita receita) {
		this.receita = receita;
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Exame getExame() {
		return exame;
	}

	public void setExame(Exame exame) {
		this.exame = exame;
	}

	public Laudo getLaudo() {
		return laudo;
	}

	public void setLaudo(Laudo laudo) {
		this.laudo = laudo;
	}

	public Doenca getDoenca() {
		return doenca;
	}

	public void setDoenca(Doenca doenca) {
		this.doenca = doenca;
	}

	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public SituacaoAtend getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoAtend situacao) {
		this.situacao = situacao;
	}

	public List<Atendimento> getAtendimentosPorEspecialidade() {
		return atendimentosPorEspecialidade;
	}

	public void setAtendimentosPorEspecialidade(List<Atendimento> atendimentosPorEspecialidade) {
		this.atendimentosPorEspecialidade = atendimentosPorEspecialidade;
	}

	public List<Doenca> getDoencas() {
		return doencas;
	}

	public void setDoencas(List<Doenca> doencas) {
		this.doencas = doencas;
	}

	public List<Doenca> getDoencasSelecionadas() {
		return doencasSelecionadas;
	}

	public void setDoencasSelecionadas(List<Doenca> doencasSelecionadas) {
		this.doencasSelecionadas = doencasSelecionadas;
	}

	public AtendDoenca getAtendDoenca() {
		return atendDoenca;
	}

	public void setAtendDoenca(AtendDoenca atendDoenca) {
		this.atendDoenca = atendDoenca;
	}

	public AtendExame getAtendExame() {
		return atendExame;
	}

	public void setAtendExame(AtendExame atendExame) {
		this.atendExame = atendExame;
	}

	public ReceiMedic getReceiMedic() {
		return receiMedic;
	}

	public void setReceiMedic(ReceiMedic receiMedic) {
		this.receiMedic = receiMedic;
	}

	public List<AtendDoenca> getAtendDoencas() {
		return atendDoencas;
	}

	public void setAtendDoencas(List<AtendDoenca> atendDoencas) {
		this.atendDoencas = atendDoencas;
	}

	public List<AtendExame> getAtendExames() {
		return atendExames;
	}

	public void setAtendExames(List<AtendExame> atendExames) {
		this.atendExames = atendExames;
	}

	public List<Exame> getExames() {
		return exames;
	}

	public void setExames(List<Exame> exames) {
		this.exames = exames;
	}

	public List<Medicamento> getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(List<Medicamento> medicamentos) {
		this.medicamentos = medicamentos;
	}

	public String getCampoAlergia() {
		if (paciente.isAlergia()) {
			campoAlergia = "Alérgico";
		} else {
			campoAlergia = "Não alérgico.";
		}

		return campoAlergia;
	}

	public void setCampoAlergia(String campoAlergia) {
		this.campoAlergia = campoAlergia;
	}

}
