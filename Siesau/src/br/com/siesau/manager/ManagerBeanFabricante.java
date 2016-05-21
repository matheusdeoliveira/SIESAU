package br.com.siesau.manager;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.json.JSONException;

import br.com.siesau.control.viaCEP.ViaCEP;
import br.com.siesau.control.viaCEP.ViaCEPException;
import br.com.siesau.entity.Fabricante;
import br.com.siesau.entity.FabricanteDao;

@ManagedBean(name = "mbFabricante")
@ViewScoped
public class ManagerBeanFabricante implements Serializable {

	private static final long serialVersionUID = 1L;
	private Fabricante fabricante;
	private Fabricante selecionado;
	private List<Fabricante> fabricantes;
	private List<Fabricante> fabricantesSelecionados;
	private List<Fabricante> fabricantesFiltrados;
	private ViaCEP viaCep;
	private String[] uf = { "", "AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MS", "MT", "PA",
			"PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RS", "SC", "SE", "SP", "TO" };

	@PostConstruct
	public void init() {

		fabricante = new Fabricante();
		fabricantes = new FabricanteDao(new Fabricante()).lista();

	}

	public void salvar() {
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			buscaCep();
			fabricante.setAtivo(true);
			new FabricanteDao(new Fabricante()).salva(fabricante);
			fc.addMessage("form1", new FacesMessage("Fabricante " + fabricante.getRazSocial() + " salvo com sucesso"));
			fabricante = new Fabricante();
			fabricantes = new FabricanteDao(new Fabricante()).lista();

		} catch (Exception e) {
			fc.addMessage("form1", new FacesMessage("Error: " + e.getMessage()));
			e.printStackTrace();
		}

	}

	public void excluir() {
		FacesContext fc = FacesContext.getCurrentInstance();

		try {

			new FabricanteDao(new Fabricante()).deleta(selecionado);

			fc.addMessage("form2", new FacesMessage("Fornecedor " + selecionado.getRazSocial() + " exclu�do"));
			fabricantes = new FabricanteDao(new Fabricante()).lista();
		} catch (Exception e) {
			fc.addMessage("form2", new FacesMessage("Error: " + e.getMessage()));
			e.printStackTrace();
		}
	}

	public void editar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {

			new FabricanteDao(new Fabricante()).atualiza(selecionado);
			fc.addMessage("form2", new FacesMessage("Fornecedor " + selecionado.getRazSocial() + " editado"));
			fabricantes = new FabricanteDao(new Fabricante()).lista();

		} catch (Exception e) {
			fc.addMessage("form2", new FacesMessage("Error: " + e.getMessage()));
			e.printStackTrace();
		}
	}

	public void buscaCep() {
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			viaCep = new ViaCEP();

			viaCep.buscar(fabricante.getCep().toString());

			fabricante.setCidade(viaCep.getLocalidade());
			fabricante.setEndereco(viaCep.getLogradouro());
			fabricante.setBairro(viaCep.getBairro());
			fabricante.setUf(viaCep.getUf());

		} catch (ViaCEPException e) {
			fc.addMessage("form2", new FacesMessage("Error: " + e.getMessage()));
			e.printStackTrace();
		} catch (JSONException e) {
			fc.addMessage("form2", new FacesMessage("Error: " + e.getMessage()));
			e.printStackTrace();
		}
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public Fabricante getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(Fabricante selecionado) {
		this.selecionado = selecionado;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}

	public String[] getUf() {
		return uf;
	}

	public void setUf(String[] uf) {
		this.uf = uf;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Fabricante> getFabricantesSelecionados() {
		return fabricantesSelecionados;
	}

	public void setFabricantesSelecionados(List<Fabricante> fabricantesSelecionados) {
		this.fabricantesSelecionados = fabricantesSelecionados;
	}

	public List<Fabricante> getFabricantesFiltrados() {
		return fabricantesFiltrados;
	}

	public void setFabricantesFiltrados(List<Fabricante> fabricantesFiltrados) {
		this.fabricantesFiltrados = fabricantesFiltrados;
	}

}
