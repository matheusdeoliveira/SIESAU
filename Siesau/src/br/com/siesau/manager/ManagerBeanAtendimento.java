package br.com.siesau.manager;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import br.com.siesau.entity.Atendimento;
import br.com.siesau.entity.Especialidade;
import br.com.siesau.entity.Paciente;
import br.com.siesau.entity.SituacaoAtend;
import br.com.siesau.persistence.AtendimentoDao;
import br.com.siesau.persistence.EspecialidadeDao;
import br.com.siesau.persistence.PacienteDao;
import br.com.siesau.persistence.SituacaoAtendDao;

@ManagedBean(name = "mbAtendimento")
@ViewScoped
public class ManagerBeanAtendimento implements Serializable {

	private static final long serialVersionUID = 1L;
	private String itemSelecionado;
	private String campoBusca;
	private int sitAtend;
	private int idade;

	private Atendimento atendimento;
	private List<Atendimento> atendimentos;
	private List<Atendimento> atendimentosFiltrados;

	private Paciente paciente;

	private Especialidade especialidade;

	private List<SituacaoAtend> situacaoAtends;
	private Map<String, String> mostraSituacaoAtends = new HashMap<String, String>();

	private List<Especialidade> especialidades;
	private Map<String, String> mostraEspecialidades = new HashMap<String, String>();

	@PostConstruct
	public void init() {
		atendimento = new Atendimento();
		atendimento.setPaciente(new Paciente());
		atendimento.setSituacaoAtend(new SituacaoAtend());
		atendimentos = new AtendimentoDao(new Atendimento()).lista();

		paciente = new Paciente();

		especialidade = new Especialidade();
		especialidades = new EspecialidadeDao(new Especialidade()).lista();

		mostraEspecialidades = new HashMap<String, String>();
		for (int i = 0; i < especialidades.size(); i++) {
			mostraEspecialidades.put(especialidades.get(i).getEspecialidade().toString().toUpperCase(),
					especialidades.get(i).getCdEspec().toString());
		}

		situacaoAtends = new SituacaoAtendDao(new SituacaoAtend()).lista();

		mostraSituacaoAtends = new HashMap<String, String>();
		for (int i = 0; i < situacaoAtends.size(); i++) {
			mostraSituacaoAtends.put(situacaoAtends.get(i).getSituacao().toString().toUpperCase(),
					situacaoAtends.get(i).getCdSitatend().toString());
		}
	}

	public void buscaPaciente() {
		FacesContext fc = FacesContext.getCurrentInstance();

		atendimento = new Atendimento();
		atendimento.setPaciente(new Paciente());
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

			atendimento.setPaciente(paciente);

		} catch (Exception e) {
			fc.addMessage("form_paciente", new FacesMessage("Error: " + e.getMessage()));
		}
	}

	public void salvar() {
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			atendimento.setEspecialidade(especialidade);
			atendimento.setDataAtend(new Date());
			atendimento.setSituacaoAtend(new SituacaoAtend());
			atendimento.getSituacaoAtend().setCdSitatend(1);

			new AtendimentoDao(new Atendimento()).salva(atendimento);
			fc.addMessage("form_paciente",
					new FacesMessage("Atendimento do paciente " + atendimento.getPaciente().getNome() + " agendado."));
			atendimento = new Atendimento();
			paciente = new Paciente();
			atendimentos = new AtendimentoDao(new Atendimento()).lista();
			itemSelecionado = "";
			campoBusca = "";

		} catch (Exception e) {
			fc.addMessage("form_paciente", new FacesMessage("Error: " + e.getMessage()));
			e.printStackTrace();
		}

	}

	public void excluir() {
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			new AtendimentoDao(new Atendimento()).deleta(atendimento);
			fc.addMessage("form_paciente",
					new FacesMessage("Atendimento nº " + atendimento.getCdAtend() + " removido."));
			atendimentos = new AtendimentoDao(new Atendimento()).listaNaoFinalizados();
		} catch (Exception e) {
			fc.addMessage("form_paciente", new FacesMessage("Error: " + e.getMessage()));
			e.printStackTrace();
		}
	}

	public void editarLinha(RowEditEvent row) {
		FacesContext fc = FacesContext.getCurrentInstance();

		try {

			Atendimento selecionado = (Atendimento) row.getObject();

			new AtendimentoDao(new Atendimento()).atualiza(selecionado);
			fc.addMessage("form_paciente", new FacesMessage("Atendimento nº " + selecionado.getCdAtend() + " editado"));
			atendimentos = new AtendimentoDao(new Atendimento()).listaNaoFinalizados();

		} catch (Exception e) {
			fc.addMessage("form_paciente", new FacesMessage("Error: " + e.getMessage()));
			e.printStackTrace();
		}
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

	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	public List<Atendimento> getAtendimentos() {
		return atendimentos;
	}

	public void setAtendimentos(List<Atendimento> atendimentos) {
		this.atendimentos = atendimentos;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Especialidade> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<Especialidade> especialidades) {
		this.especialidades = especialidades;
	}

	public Map<String, String> getMostraEspecialidades() {
		return mostraEspecialidades;
	}

	public void setMostraEspecialidades(Map<String, String> mostraEspecialidades) {
		this.mostraEspecialidades = mostraEspecialidades;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	public List<SituacaoAtend> getSituacaoAtends() {
		return situacaoAtends;
	}

	public void setSituacaoAtends(List<SituacaoAtend> situacaoAtends) {
		this.situacaoAtends = situacaoAtends;
	}

	public Map<String, String> getMostraSituacaoAtends() {
		return mostraSituacaoAtends;
	}

	public void setMostraSituacaoAtends(Map<String, String> mostraSituacaoAtends) {
		this.mostraSituacaoAtends = mostraSituacaoAtends;
	}

	public int getSitAtend() {
		return sitAtend;
	}

	public void setSitAtend(int sitAtend) {
		this.sitAtend = sitAtend;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public List<Atendimento> getAtendimentosFiltrados() {
		return atendimentosFiltrados;
	}

	public void setAtendimentosFiltrados(List<Atendimento> atendimentosFiltrados) {
		this.atendimentosFiltrados = atendimentosFiltrados;
	}

}
