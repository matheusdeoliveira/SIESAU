package br.com.siesau.manager;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.json.JSONException;
import org.primefaces.event.RowEditEvent;

import br.com.siesau.control.viaCEP.ViaCEP;
import br.com.siesau.control.viaCEP.ViaCEPException;
import br.com.siesau.entity.Cargo;
import br.com.siesau.entity.Funcionario;
import br.com.siesau.persistence.FuncionarioDao;
import br.com.siesau.persistence.MedicoDao;

@ManagedBean(name = "mbMedico")
public class ManagerBeanMedico extends ManagerBeanFuncionario implements Serializable{

	private static final long serialVersionUID = 1L;
	private Funcionario medico;
	private List<Funcionario> medicos;
	private ViaCEP viaCep;
	
	@PostConstruct
	public void init() {
		medico = new Funcionario();
		medico.setCargo(new Cargo());
		medico.getCargo().setCdCargo(1);
		medicos = new MedicoDao(new Funcionario()).lista();
		
	}
	

	public void salvar() {
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			buscaCep();
			new FuncionarioDao(new Funcionario()).salva(medico);
			fc.addMessage("form1", new FacesMessage("Medico " + medico.getNome() + " salvo com sucesso."));
			medico = new Funcionario();
			medicos = new MedicoDao(new Funcionario()).lista();
		} catch (Exception e) {
			fc.addMessage("form1", new FacesMessage("Error: " + e.getMessage()));
			e.printStackTrace();
		}
	}

	public void excluir() {
		FacesContext fc = FacesContext.getCurrentInstance();

		try {

			new FuncionarioDao(new Funcionario()).deleta(medico);
			fc.addMessage("form2", new FacesMessage("Medico " + medico.getNome() + " excluído"));
			medicos = new FuncionarioDao(new Funcionario()).lista();
		} catch (Exception e) {
			fc.addMessage("form2", new FacesMessage("Error: " + e.getMessage()));
			e.printStackTrace();
		}
	}

	public void editarLinha(RowEditEvent row) {
		FacesContext fc = FacesContext.getCurrentInstance();
	try {
		
		Funcionario selecionado = (Funcionario) row.getObject();

		new FuncionarioDao(new Funcionario()).atualiza(selecionado);
		fc.addMessage("form2", new FacesMessage("Medico " + selecionado.getNome() + " editado"));
		medicos = new FuncionarioDao(new Funcionario()).lista();
		
	} catch (Exception e) {
		fc.addMessage("form2", new FacesMessage("Error: " + e.getMessage()));
		e.printStackTrace();
	}
}
	
	public void buscaCep() {
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			viaCep = new ViaCEP();
			viaCep.buscar(medico.getCep().toString());
			
			medico.setCidade(viaCep.getLocalidade());
			medico.setEndereco(viaCep.getLogradouro());
			medico.setBairro(viaCep.getBairro());
			medico.setUf(viaCep.getUf());
			
		} catch (ViaCEPException e) {
			fc.addMessage("form2", new FacesMessage("Error: " + e.getMessage()));
			e.printStackTrace();
		} catch (JSONException e) {
			fc.addMessage("form2", new FacesMessage("Error: " + e.getMessage()));
			e.printStackTrace();
		}
	}

	public Funcionario getMedico() {
		return medico;
	}

	public void setMedico(Funcionario medico) {
		this.medico = medico;
	}

	public List<Funcionario> getMedicos() {
		return medicos;
	}

	public void setMedicos(List<Funcionario> medicos) {
		this.medicos = medicos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
