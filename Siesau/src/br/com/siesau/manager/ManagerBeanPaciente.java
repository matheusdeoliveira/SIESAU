package br.com.siesau.manager;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

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

import br.com.siesau.control.GoogleMap;
import br.com.siesau.control.viaCEP.ViaCEP;
import br.com.siesau.control.viaCEP.ViaCEPException;
import br.com.siesau.entity.Paciente;
import br.com.siesau.persistence.PacienteDao;

@ManagedBean(name = "mbPaciente")
@ViewScoped
public class ManagerBeanPaciente implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private Paciente paciente;
	private Paciente selecionado;
	private List<Paciente> pacientes;
	private List<Paciente> pacientesFiltrados;
	private String foto;
	private ViaCEP viaCep;

	@PostConstruct
	private void inti() {
		paciente = new Paciente();
		pacientes = new PacienteDao(new Paciente()).lista();
	}

	public void salvar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			List<String> coordenadas = GoogleMap
					.buscaCoordenadas(paciente.getEndereco() + " " + paciente.getBairro() + " " + paciente.getCidade());

			paciente.setFoto(getFoto());
			paciente.setLatitude(coordenadas.get(0));
			paciente.setLongitude(coordenadas.get(1));

			paciente.setDataCad(new Date());
			new PacienteDao(new Paciente()).salva(paciente);
			fc.addMessage("form1", new FacesMessage("Paciente " + paciente.getNome() + " salvo com sucesso"));
			foto = "";
			paciente = new Paciente();
			pacientes = new PacienteDao(new Paciente()).lista();

		} catch (Exception e) {
			fc.addMessage("form1", new FacesMessage("Error: " + e.getMessage()));
		}
	}
	
	public void excluir() {
		FacesContext fc = FacesContext.getCurrentInstance();

		try {

			new PacienteDao(new Paciente()).deleta(selecionado);
			fc.addMessage("form2", new FacesMessage("Paciente " + selecionado.getNome() + " excluído"));
			pacientes = new PacienteDao(new Paciente()).lista();

		} catch (Exception e) {
			fc.addMessage("form2", new FacesMessage("Error: " + e.getMessage()));
			e.printStackTrace();
		}
	}
	

	public void editar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			
			new PacienteDao(new Paciente()).atualiza(selecionado);
			fc.addMessage("msgs", new FacesMessage("Paciente " + selecionado.getNome() + " editado"));
			pacientes = new PacienteDao(new Paciente()).lista();

		} catch (Exception e) {
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
	
	public void buscaCep() {
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			viaCep = new ViaCEP();
			viaCep.buscar(paciente.getCep().toString());

			paciente.setCidade(viaCep.getLocalidade());
			paciente.setEndereco(viaCep.getLogradouro());
			paciente.setBairro(viaCep.getBairro());
			paciente.setUf(viaCep.getUf());
			

		} catch (ViaCEPException e) {
			fc.addMessage("form2", new FacesMessage("Error: " + e.getMessage()));
			e.printStackTrace();
		} catch (JSONException e) {
			fc.addMessage("form2", new FacesMessage("Error: " + e.getMessage()));
			e.printStackTrace();
		}
	}
	
	public void oncapture(CaptureEvent captureEvent) {
		FacesContext fc = FacesContext.getCurrentInstance();
		
		this.foto = getNumeroRandomico() + ".jpeg";
		byte[] data = captureEvent.getData();
		
		ExternalContext externalContext = fc.getExternalContext();
		String diretorio = externalContext.getRealPath("")+ "resources" + File.separator + "fotos" ;
		
		String dir = diretorio.replace("\\", "\\\\");		
		verificaDiretorio(dir);
		
		String caminhoFoto = diretorio + File.separator	+ foto;
		
		FileImageOutputStream imageOutput;
		
		try {
			imageOutput = new FileImageOutputStream(new File(caminhoFoto));
			imageOutput.write(data, 0, data.length);
			imageOutput.close();
			
			fc.addMessage("form1",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Foto CAPTURADA com sucesso!", "Informação"));
			System.out.println("Tenta nesse diretório aqui " + caminhoFoto);
		} catch (IOException e) {
			throw new FacesException("Error in writing captured image.", e);
		}
	}
	
	private void verificaDiretorio(String param){
		Path dir = Paths.get(param);
		if(!dir.toFile().exists()){
			System.out.println("Criação do diretório: " + param);
			new File(param).mkdirs();
		}					
	}
	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

	private String filename;

	private String getNumeroRandomico() {
		int i = (int) (Math.random() * 10000000);

		return String.valueOf(i);
	}

	public String getFilename() {
		return filename;
	}	
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public List<Paciente> getPacientesFiltrados() {
		return pacientesFiltrados;
	}

	public void setPacientesFiltrados(List<Paciente> pacientesFiltrados) {
		this.pacientesFiltrados = pacientesFiltrados;
	}

	public Paciente getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(Paciente selecionado) {
		this.selecionado = selecionado;
	}

	
	
}
