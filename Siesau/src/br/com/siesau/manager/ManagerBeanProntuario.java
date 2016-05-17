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
import br.com.siesau.entity.Receita;
import br.com.siesau.entity.SituacaoAtend;
import br.com.siesau.persistence.AtendDoencaDao;
import br.com.siesau.persistence.AtendimentoDao;
import br.com.siesau.persistence.DoencaDao;
import br.com.siesau.persistence.ExameDao;
import br.com.siesau.persistence.LaudoDao;
import br.com.siesau.persistence.MedicamentoDao;
import br.com.siesau.persistence.PacienteDao;
import br.com.siesau.persistence.ReceitaDao;

@ManagedBean(name = "mbProntuario")
@ViewScoped
public class ManagerBeanProntuario implements Serializable {

	private static final long serialVersionUID = 1L;
	private Paciente paciente;
	private int idade;
	private String itemSelecionado;
	private int campoBusca;
	private Receita receita;
	private Medicamento medicamento;
	private Funcionario funcionario;
	private Exame exame;
	private Laudo laudo;
	private Doenca doenca;
	private List<Doenca> doencas;
	private List<Doenca> doencasSelecionadas;
	private Atendimento atendimento;
	private SituacaoAtend situacao;
	private List<Atendimento> atendimentosPorEspecialidade;
	
	private AtendDoenca atendDoenca;
	private AtendExame atendExame;

	@PostConstruct
	public void init() {
		atendExame = new AtendExame();
		doencasSelecionadas = new ArrayList<>();
		atendDoenca = new AtendDoenca();
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
				paciente = new AtendimentoDao(new Atendimento()).pesquisaCodigoAtendimento(campoBusca).getPaciente();
				atendimento = new AtendimentoDao(new Atendimento()).findByCode(campoBusca);
			}

			atendimento.setPaciente(paciente);

		} catch (Exception e) {
			fc.addMessage("form1", new FacesMessage("Error: " + e.getMessage()));
			e.printStackTrace();
		}
	}

	public void salvarReceita() {
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			atendimento = new AtendimentoDao(new Atendimento()).findByCode(campoBusca);
			receita.setAtendimento(atendimento);
			receita.setData(new Date());
			receita.setCdReceita(atendimento.getCdAtend() + 100);

			new ReceitaDao(new Receita()).salva(receita);

			fc.addMessage("form1", new FacesMessage("Receita " + receita.getCdReceita() + " salva."));

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
			
			atendimento = new AtendimentoDao(new Atendimento()).findByCode(campoBusca);
			exame = new ExameDao(new Exame()).findByCode(exame.getCdExame());
			
			
			
			exame.setData(new Date());			
			atendExame.setAtendimento2(atendimento);
			atendExame.setExame2(exame);
			
			new AtendExameDao(new AtendExame()).salva(atendExame);
			
			fc.addMessage("form_exames", new FacesMessage("Exame " + exame.getCdExame() + " salvo."));
		} catch (Exception e) {
			fc.addMessage("form_exames", new FacesMessage("Erro: " + e.getMessage() + "."));
			e.printStackTrace();
		}
		
		exame = new Exame();
		atendExame = new AtendExame();
	}

	public void salvarLaudo() {
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			/**
			Precisa integrar tabelas de Laudos com Exames.
			**/
			atendimento = new AtendimentoDao(new Atendimento()).findByCode(campoBusca);
			new LaudoDao(new Laudo()).salva(laudo);
			
			
			fc.addMessage("form_laudo", new FacesMessage("Laudo " + laudo.getCdLaudo() + " salvo."));

		} catch (Exception e) {
			fc.addMessage("form_laudo", new FacesMessage("Erro: " + e.getMessage() + "."));
			e.printStackTrace();
		}
	}

	public void salvarDoenca() {
		
		//Botão não responde. Porém metodo funciona
		
		atendimento = new AtendimentoDao(new Atendimento()).findByCode(campoBusca);

		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			for (int i = 0; i < doencasSelecionadas.size(); i++) {
				Doenca doencaTemp = (Doenca) doencasSelecionadas.get(i);
				atendDoenca = new AtendDoenca();
				atendDoenca.setAtendimento2(atendimento);
				atendDoenca.setDoenca2(doencaTemp);

				new AtendDoencaDao(new AtendDoenca()).salva(atendDoenca);
			}

			fc.addMessage("form_doenca", new FacesMessage("Doenca " + doenca.getNome() + " salva."));
		} catch (Exception e) {
			fc.addMessage("form_doenca", new FacesMessage("Erro: " + e.getMessage() + "."));
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		atendDoenca = new AtendDoenca();
	}

	// Finalizando Atendimento OK
	public void finalizaAtendimento() {
		FacesContext fc = FacesContext.getCurrentInstance();
		atendimento = new AtendimentoDao(new Atendimento()).findByCode(campoBusca);

		try {
			atendimento = new AtendimentoDao(new Atendimento()).pesquisaCodigoAtendimento(campoBusca);
			situacao.setCdSitatend(3);
			atendimento.setSituacaoAtend(situacao);
			;

			new AtendimentoDao(new Atendimento()).atualiza(atendimento);
			fc.addMessage("form1", new FacesMessage("Atendimento " + atendimento.getCdAtend() + " finalizado."));
		} catch (Exception e) {
			fc.addMessage("form1", new FacesMessage("Erro : " + e.getMessage()));
		}
		init();
		campoBusca = 0;
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

	public int getCampoBusca() {
		return campoBusca;
	}

	public void setCampoBusca(int campoBusca) {
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

	@SuppressWarnings("deprecation")
	public int getIdade() {
		try {

			int ano = getPaciente().getDataNasc().getYear();
			idade = new Date().getYear() - ano;

		} catch (Exception e) {
			return 0;
		}
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

	public static void main(String[] main) {
		Receita receita = new Receita();
		Atendimento atendimento = new Atendimento();
		
		
		receita.setAtendimento(atendimento);
		receita.setData(new Date());
		receita.setCdReceita(atendimento.getCdAtend() + 100);

		new ReceitaDao(new Receita()).salva(receita);
	}

}
