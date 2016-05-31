package br.com.siesau.manager;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import br.com.siesau.entity.Fabricante;
import br.com.siesau.entity.Medicamento;
import br.com.siesau.persistence.FabricanteDao;
import br.com.siesau.persistence.MedicamentoDao;

/**
 * @author Diogo Freitas
 *
 */

@ManagedBean(name="mbMedicamento")
public class ManagerBeanMedicamento implements Serializable {

	private static final long serialVersionUID = 1L;
	private Medicamento medicamento;
	private List<Medicamento> medicamentos;
	private List<Medicamento> medicamentosFiltrados;
	private Map<String, String> mostraFabricante;
	private List<Fabricante> fabricantes;
	private String fabricanteSelecionado;


	@PostConstruct
	public void init() {
		medicamento = new Medicamento();
		medicamento.setFabricante(new Fabricante());
		medicamentos = new MedicamentoDao(new Medicamento()).lista();
		fabricantes = new FabricanteDao(new Fabricante()).lista();
		
		// Carregando Itens da Tela
		mostraFabricante = new HashMap<String, String>();
		for (int i = 0; i < fabricantes.size(); i++) {
			mostraFabricante.put(fabricantes.get(i).getCnpj() + " - " + fabricantes.get(i).getFantasia().toUpperCase(), String.valueOf(fabricantes.get(i).getCnpj()));
		}

	}

	public void salvar() {
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			
			Fabricante fabricante = new FabricanteDao(new Fabricante()).buscaPorCNPJ(fabricanteSelecionado);
			
			medicamento.setFabricante(fabricante);
			
			new MedicamentoDao(new Medicamento()).salva(medicamento);
		
			
			fc.addMessage("form1", new FacesMessage("Medicamento " + medicamento.getNomeRef() + " salvo com sucesso."));
			medicamento = new Medicamento();
			medicamentos = new MedicamentoDao(new Medicamento()).lista();

		} catch (Exception e) {
			fc.addMessage("form1", new FacesMessage("Error: " + e.getMessage()));
			e.printStackTrace();
		}
	}

	public void excluir() {
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			new MedicamentoDao(new Medicamento()).deleta(medicamento);

			fc.addMessage("form2", new FacesMessage("Medicamento " + medicamento.getNomeRef() + " excluído"));
			medicamentos = new MedicamentoDao(new Medicamento()).lista();
		} catch (Exception e) {
			fc.addMessage("form2", new FacesMessage("Error: " + e.getMessage()));
			e.printStackTrace();
		}
	}

	public void editarLinha(RowEditEvent row) {
		FacesContext fc = FacesContext.getCurrentInstance();
	try {
		
		Medicamento selecionado = (Medicamento) row.getObject();

		new MedicamentoDao(new Medicamento()).atualiza(selecionado);
		fc.addMessage("form2", new FacesMessage("Medicamento " + selecionado.getNomeRef() + " editado"));
		medicamentos = new MedicamentoDao(new Medicamento()).lista();
		
	} catch (Exception e) {
		fc.addMessage("form2", new FacesMessage("Error: " + e.getMessage()));
		e.printStackTrace();
	}
}
	
	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public List<Medicamento> getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(List<Medicamento> medicamentos) {
		this.medicamentos = medicamentos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Medicamento> getMedicamentosFiltrados() {
		return medicamentosFiltrados;
	}

	public void setMedicamentosFiltrados(List<Medicamento> medicamentosFiltrados) {
		this.medicamentosFiltrados = medicamentosFiltrados;
	}

	public Map<String, String> getMostraFabricante() {
		return mostraFabricante;
	}

	public void setMostraFabricante(Map<String, String> mostraFabricante) {
		this.mostraFabricante = mostraFabricante;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}

	public String getFabricanteSelecionado() {
		return fabricanteSelecionado;
	}

	public void setFabricanteSelecionado(String fabricanteSelecionado) {
		this.fabricanteSelecionado = fabricanteSelecionado;
	}

}
