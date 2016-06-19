package br.com.siesau.manager;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;

import org.json.JSONException;
import org.primefaces.event.CaptureEvent;

import br.com.siesau.control.viaCEP.ViaCEP;
import br.com.siesau.control.viaCEP.ViaCEPException;
import br.com.siesau.entity.Cargo;
import br.com.siesau.entity.Especialidade;
import br.com.siesau.entity.FuncEspec;
import br.com.siesau.entity.Funcionario;
import br.com.siesau.entity.UnidFunc;
import br.com.siesau.entity.UnidadeSaude;
import br.com.siesau.persistence.CargoDao;
import br.com.siesau.persistence.EspecialidadeDao;
import br.com.siesau.persistence.FuncEspecDao;
import br.com.siesau.persistence.FuncionarioDao;
import br.com.siesau.persistence.UnidFuncDao;
import br.com.siesau.persistence.UnidadeSaudeDao;

/**
 * @author Diogo Freitas
 *
 */

@ManagedBean(name = "mbFuncionario")
@ViewScoped
public class ManagerBeanFuncionario implements Serializable {

	private static final long serialVersionUID = 1L;
	private Funcionario funcionario;
	private Funcionario selecionado;
	private FuncEspec funcEspec;
	private List<Funcionario> funcionarios;
	private List<Funcionario> funcionariosFiltrados;
	private List<Especialidade> especialidades;
	private List<UnidadeSaude> unidadeSaudes;
	private List<String> especialidadesSelecionadas;
	private String unidadesSelecionada;
	private ViaCEP viaCep;
	private List<Cargo> cargos;
	private Map<String, String> mostraCargos;
	private Map<String, String> mostraEspec;
	private Map<String, String> mostraUnidades;
	private UnidadeSaude unidadeSaude;
	private UnidFunc unidFunc;
	private String foto;

	@PostConstruct
	public void init() {
		mostraCargos = new HashMap<String, String>();
		mostraEspec = new HashMap<String, String>();
		mostraUnidades = new HashMap<String, String>();
		funcionario = new Funcionario();
		selecionado = new Funcionario();
		funcEspec = new FuncEspec();
		funcionario.setCargo(new Cargo());
		funcionarios = new FuncionarioDao(new Funcionario()).lista();
		especialidades = new EspecialidadeDao(new Especialidade()).lista();
		unidadeSaudes = new UnidadeSaudeDao(new UnidadeSaude()).lista();
		especialidadesSelecionadas = new ArrayList<>();
		unidadeSaude = new UnidadeSaude();
		unidFunc = new UnidFunc();

		// Carregando Cargos na Lista
		cargos = new CargoDao(new Cargo()).lista();

		// Carregando Itens da Tela
		mostraCargos = new HashMap<String, String>();
		for (int i = 0; i < cargos.size(); i++) {
			mostraCargos.put(cargos.get(i).getCargo().toString().toUpperCase(), cargos.get(i).getCdCargo().toString());
		}

		// Carregando Itens da Tela
		mostraEspec = new HashMap<String, String>();
		for (int i = 0; i < especialidades.size(); i++) {
			mostraEspec.put(especialidades.get(i).getEspecialidade().toUpperCase(),
					especialidades.get(i).getEspecialidade().toUpperCase());
		}

		// Carregando Itens da Tela
		mostraUnidades = new HashMap<String, String>();
		for (int i = 0; i < unidadeSaudes.size(); i++) {
			mostraUnidades.put(
					unidadeSaudes.get(i).getCdUnidsaude() + " - " + unidadeSaudes.get(i).getFantasia().toUpperCase(),
					String.valueOf(unidadeSaudes.get(i).getCnpj()));
		}

	}

	public void salvar() {
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			buscaCep();

			funcionario.setFoto(getFoto());
			funcionario.setDataAdmis(new Date());
			funcionario.setAtivo(true);

			new FuncionarioDao(new Funcionario()).salva(funcionario);

			funcionario = new FuncionarioDao(new Funcionario()).findByCPF(funcionario.getCpf());

			for (int j = 0; j < especialidadesSelecionadas.size(); j++) {

				Especialidade especialidade = new Especialidade();
				especialidade = new EspecialidadeDao(new Especialidade())
						.findByEspecialidade(especialidadesSelecionadas.get(j).trim());

				funcEspec.setEspecialidade1(especialidade);
				funcEspec.setEspecialidade2(especialidade);

				funcEspec.setFuncionario1(funcionario);
				funcEspec.setFuncionario2(funcionario);

				new FuncEspecDao(new FuncEspec()).salva(funcEspec);
			}

			UnidadeSaude unidadeSaude = new UnidadeSaude();
			unidadeSaude = new UnidadeSaudeDao(new UnidadeSaude()).findByCNPJ(unidadesSelecionada);

			unidFunc.setUnidadeSaude1(unidadeSaude);
			unidFunc.setUnidadeSaude2(unidadeSaude);

			unidFunc.setFuncionario1(funcionario);
			unidFunc.setFuncionario2(funcionario);

			new UnidFuncDao(new UnidFunc()).salva(unidFunc);

			fc.addMessage("form1", new FacesMessage("Funcionario " + funcionario.getNome() + " salvo com sucesso."));
			unidFunc = new UnidFunc();
			funcEspec = new FuncEspec();
			foto = "";
			funcionario = new Funcionario();
			unidadeSaude = new UnidadeSaude();
			funcionarios = new FuncionarioDao(new Funcionario()).lista();

		} catch (Exception e) {
			fc.addMessage("form1", new FacesMessage("Error: " + e.getMessage()));
			e.printStackTrace();
		}
	}

	public void excluir() {
		FacesContext fc = FacesContext.getCurrentInstance();

		try {

			fc.addMessage("form2", new FacesMessage("Funcion�rio " + selecionado.getNome() + " exclu�do"));
			new FuncionarioDao(new Funcionario()).deleta(selecionado);
			funcionarios = new FuncionarioDao(new Funcionario()).lista();
		} catch (Exception e) {
			fc.addMessage("form2", new FacesMessage("Error: " + e.getMessage()));
			e.printStackTrace();
		}
	}

	public void editar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			Funcionario temp = selecionado;

			new FuncionarioDao(new Funcionario()).atualiza(selecionado);
			fc.addMessage("form2", new FacesMessage("Funcionário " + temp.getNome() + " editado"));
			selecionado = new Funcionario();
			temp = new Funcionario();
			funcionarios = new FuncionarioDao(new Funcionario()).lista();

		} catch (Exception e) {
			fc.addMessage("form2", new FacesMessage("Error: " + e.getMessage()));
			e.printStackTrace();
		}
	}

	public void buscaCep() {
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			viaCep = new ViaCEP();
			viaCep.buscar(funcionario.getCep().toString());

			funcionario.setCidade(viaCep.getLocalidade());
			funcionario.setEndereco(viaCep.getLogradouro());
			funcionario.setBairro(viaCep.getBairro());
			funcionario.setUf(viaCep.getUf());

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

	private String getNumeroRandomico() {
		int i = (int) (Math.random() * 10000);
		return String.valueOf(i);
	}

	public void oncapture(CaptureEvent captureEvent) {
		FacesContext fc = FacesContext.getCurrentInstance();

		this.foto = getNumeroRandomico() + ".jpeg";
		byte[] data = captureEvent.getData();

		ExternalContext externalContext = fc.getExternalContext();
		String diretorio = externalContext.getRealPath("") + "resources" + File.separator + "fotos";

		String dir = diretorio.replace("\\", "\\\\");
		verificaDiretorio(dir);

		String caminhoFoto = diretorio + File.separator + foto;
				
		FileImageOutputStream imageOutput;

		try {
			imageOutput = new FileImageOutputStream(new File(caminhoFoto));
			imageOutput.write(data, 0, data.length);
			imageOutput.close();

			fc.addMessage("form1",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Foto CAPTURADA com sucesso!", "Informa��o"));
			System.out.println("Tenta nesse diret�rio aqui " + caminhoFoto);
		} catch (IOException e) {
			throw new FacesException("Error in writing captured image.", e);
		}
	}

	private void verificaDiretorio(String param) {
		Path dir = Paths.get(param);
		if (!dir.toFile().exists()) {
			System.out.println("Cria��o do diret�rio: " + param);
			new File(param).mkdirs();
		}
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public List<Cargo> getCargos() {
		if (cargos == null) {
			cargos = new CargoDao(new Cargo()).lista();
		}
		return cargos;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}

	public Map<String, String> getMostraCargos() {
		if (mostraCargos == null) {
			for (int i = 0; i < cargos.size(); i++) {
				mostraCargos.put(cargos.get(i).getCargo().toString().toUpperCase(),
						cargos.get(i).getCdCargo().toString());
			}
		}
		return mostraCargos;
	}

	public void setMostraCargos(Map<String, String> mostraCargos) {
		this.mostraCargos = mostraCargos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public UnidadeSaude getUnidadeSaude() {
		return unidadeSaude;
	}

	public void setUnidadeSaude(UnidadeSaude unidadeSaude) {
		this.unidadeSaude = unidadeSaude;
	}

	public UnidFunc getUnidFunc() {
		return unidFunc;
	}

	public void setUnidFunc(UnidFunc unidFunc) {
		this.unidFunc = unidFunc;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Funcionario getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(Funcionario selecionado) {
		this.selecionado = selecionado;
	}

	public List<Especialidade> getEspecialidades() {
		if (especialidades == null) {
			especialidades = new EspecialidadeDao(new Especialidade()).lista();
		}
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

	public FuncEspec getFuncEspec() {
		return funcEspec;
	}

	public void setFuncEspec(FuncEspec funcEspec) {
		this.funcEspec = funcEspec;
	}

	public List<Funcionario> getFuncionariosFiltrados() {
		return funcionariosFiltrados;
	}

	public void setFuncionariosFiltrados(List<Funcionario> funcionariosFiltrados) {
		this.funcionariosFiltrados = funcionariosFiltrados;
	}

	public Map<String, String> getMostraEspec() {
		return mostraEspec;
	}

	public void setMostraEspec(Map<String, String> mostraEspec) {
		this.mostraEspec = mostraEspec;
	}

	public String getUnidadesSelecionadas() {
		return unidadesSelecionada;
	}

	public void setUnidadesSelecionadas(String unidadesSelecionada) {
		this.unidadesSelecionada = unidadesSelecionada;
	}

	public Map<String, String> getMostraUnidades() {
		return mostraUnidades;
	}

	public void setMostraUnidades(Map<String, String> mostraUnidades) {
		this.mostraUnidades = mostraUnidades;
	}

	public List<UnidadeSaude> getUnidadeSaudes() {
		return unidadeSaudes;
	}

	public void setUnidadeSaudes(List<UnidadeSaude> unidadeSaudes) {
		this.unidadeSaudes = unidadeSaudes;
	}

	public String getUnidadesSelecionada() {
		return unidadesSelecionada;
	}

	public void setUnidadesSelecionada(String unidadesSelecionada) {
		this.unidadesSelecionada = unidadesSelecionada;
	}
}