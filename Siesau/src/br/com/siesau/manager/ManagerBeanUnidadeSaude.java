package br.com.siesau.manager;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.json.JSONException;

import br.com.siesau.control.viaCEP.ViaCEP;
import br.com.siesau.control.viaCEP.ViaCEPException;
import br.com.siesau.entity.UnidadeSaude;
import br.com.siesau.persistence.UnidadeSaudeDao;

@ManagedBean(name = "mbUnidadeSaude")
@ViewScoped
public class ManagerBeanUnidadeSaude {

	private static final long serialVersionUID = 1L;
	private UnidadeSaude unidadeSaude;
	private UnidadeSaude selecionado;
	private List<UnidadeSaude> unidadeSaudes;
	private List<UnidadeSaude> unidadeSaudesFiltradas;
	private ViaCEP viaCep;

	@PostConstruct
	public void init() {
		selecionado = new UnidadeSaude();
		unidadeSaude = new UnidadeSaude();
		unidadeSaudes = new UnidadeSaudeDao(new UnidadeSaude()).lista();
	}

	public void salvar() {
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			buscaCep();
			new UnidadeSaudeDao(new UnidadeSaude()).salva(unidadeSaude);
			fc.addMessage("form1",
					new FacesMessage("Unidade de CNES : " + unidadeSaude.getCnes() + " salva com sucesso"));
			unidadeSaude = new UnidadeSaude();
			unidadeSaudes = new UnidadeSaudeDao(new UnidadeSaude()).lista();

		} catch (Exception e) {
			fc.addMessage("form1", new FacesMessage("Error: " + e.getMessage()));
			e.printStackTrace();
		}

	}

	public void excluir() {
		FacesContext fc = FacesContext.getCurrentInstance();

		try {

			new UnidadeSaudeDao(new UnidadeSaude()).deleta(selecionado);

			fc.addMessage("form2", new FacesMessage("Unidade de CNES : " + selecionado.getCnes() + " excluído"));
			unidadeSaudes = new UnidadeSaudeDao(new UnidadeSaude()).lista();
		} catch (Exception e) {
			fc.addMessage("form2", new FacesMessage("Error: " + e.getMessage()));
			e.printStackTrace();
		}
	}

	public void editar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {

			new UnidadeSaudeDao(new UnidadeSaude()).atualiza(selecionado);
			fc.addMessage("form2", new FacesMessage("Unidade de CNES : " + selecionado.getCnes() + " editado"));
			unidadeSaudes = new UnidadeSaudeDao(new UnidadeSaude()).lista();

		} catch (Exception e) {
			fc.addMessage("form2", new FacesMessage("Error: " + e.getMessage()));
			e.printStackTrace();
		}
	}

	public void buscaCep() {
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			viaCep = new ViaCEP();
			viaCep.buscar(unidadeSaude.getCep().toString());

			unidadeSaude.setCidade(viaCep.getLocalidade());
			unidadeSaude.setEndereco(viaCep.getLogradouro());
			unidadeSaude.setBairro(viaCep.getBairro());
			unidadeSaude.setUf(viaCep.getUf());

		} catch (ViaCEPException e) {
			fc.addMessage("form2", new FacesMessage("Error: " + e.getMessage()));
			e.printStackTrace();
		} catch (JSONException e) {
			fc.addMessage("form2", new FacesMessage("Error: " + e.getMessage()));
			e.printStackTrace();
		}
	}

	public void buscaCepSelecionado() {
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			viaCep = new ViaCEP();
			viaCep.buscar(selecionado.getCep().toString());

			selecionado.setCidade(viaCep.getLocalidade());
			selecionado.setEndereco(viaCep.getLogradouro());
			selecionado.setBairro(viaCep.getBairro());
			selecionado.setUf(viaCep.getUf());

			System.out.println(selecionado.getBairro());

		} catch (ViaCEPException e) {
			fc.addMessage("form2", new FacesMessage("Error: " + e.getMessage()));
			e.printStackTrace();
		} catch (JSONException e) {
			fc.addMessage("form2", new FacesMessage("Error: " + e.getMessage()));
			e.printStackTrace();
		}
	}

	public UnidadeSaude getUnidadeSaude() {
		return unidadeSaude;
	}

	public void setUnidadeSaude(UnidadeSaude unidadeSaude) {
		this.unidadeSaude = unidadeSaude;
	}

	public List<UnidadeSaude> getUnidadeSaudes() {
		return unidadeSaudes;
	}

	public void setUnidadeSaudes(List<UnidadeSaude> unidadeSaudes) {
		this.unidadeSaudes = unidadeSaudes;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<UnidadeSaude> getUnidadeSaudesFiltradas() {
		return unidadeSaudesFiltradas;
	}

	public void setUnidadeSaudesFiltradas(List<UnidadeSaude> unidadeSaudesFiltradas) {
		this.unidadeSaudesFiltradas = unidadeSaudesFiltradas;
	}

	public UnidadeSaude getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(UnidadeSaude selecionado) {
		this.selecionado = selecionado;
	}

}
