package br.com.siesau.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.json.JSONException;

import br.com.siesau.control.viaCEP.ViaCEP;
import br.com.siesau.control.viaCEP.ViaCEPException;
import br.com.siesau.entity.Especialidade;
import br.com.siesau.entity.UnidEspec;
import br.com.siesau.entity.UnidadeSaude;
import br.com.siesau.persistence.EspecialidadeDao;
import br.com.siesau.persistence.UnidEspecDao;
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
	private HashMap<String, String> mostraEspec;
	private List<Especialidade> especialidades;
	private List<String> especialidadesSelecionadas;
	private UnidEspec unidEspec;

	@PostConstruct
	public void init() {
		especialidadesSelecionadas = new ArrayList<>();
		unidEspec = new UnidEspec();
		especialidades = new EspecialidadeDao(new Especialidade()).lista();
		selecionado = new UnidadeSaude();
		unidadeSaude = new UnidadeSaude();
		unidadeSaudes = new UnidadeSaudeDao(new UnidadeSaude()).lista();

		// Carregando Itens da Tela
		mostraEspec = new HashMap<String, String>();
		for (int i = 0; i < especialidades.size(); i++) {
			mostraEspec.put(especialidades.get(i).getEspecialidade().toUpperCase(),
					especialidades.get(i).getEspecialidade().toUpperCase());
		}
	}

	public void salvar() {
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			buscaCep();

			new UnidadeSaudeDao(new UnidadeSaude()).salva(unidadeSaude);

			unidadeSaude = new UnidadeSaudeDao(new UnidadeSaude()).findByCNPJ(unidadeSaude.getCnpj().trim());

			for (int j = 0; j < especialidadesSelecionadas.size(); j++) {

				Especialidade especialidade = new Especialidade();
				especialidade = new EspecialidadeDao(new Especialidade())
						.findByEspecialidade(especialidadesSelecionadas.get(j).trim());

				unidEspec.setEspecialidade1(especialidade);
				unidEspec.setEspecialidade2(especialidade);

				unidEspec.setUnidadeSaude1(unidadeSaude);
				unidEspec.setUnidadeSaude1(unidadeSaude);

				new UnidEspecDao(new UnidEspec()).salva(unidEspec);
			}

			fc.addMessage("form1",
					new FacesMessage("Unidade de CNES : " + unidadeSaude.getCnes() + " salva com sucesso"));
			unidadeSaude = new UnidadeSaude();
			unidEspec = new UnidEspec();
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

	public HashMap<String, String> getMostraEspec() {
		return mostraEspec;
	}

	public void setMostraEspec(HashMap<String, String> mostraEspec) {
		this.mostraEspec = mostraEspec;
	}

	public List<Especialidade> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<Especialidade> especialidades) {
		this.especialidades = especialidades;
	}

	public List<String> getEspecialidadesSelecionadas() {
		return especialidadesSelecionadas;
	}

	public void setEspecialidadesSelecionadas(List<String> especialidadesSelecionadas) {
		this.especialidadesSelecionadas = especialidadesSelecionadas;
	}

	public UnidEspec getUnidEspec() {
		return unidEspec;
	}

	public void setUnidEspec(UnidEspec unidEspec) {
		this.unidEspec = unidEspec;
	}
}
