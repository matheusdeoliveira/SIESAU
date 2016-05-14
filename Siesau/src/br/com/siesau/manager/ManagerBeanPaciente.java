package br.com.siesau.manager;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
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

import org.primefaces.event.CaptureEvent;
import org.primefaces.event.RowEditEvent;

import br.com.siesau.control.GoogleMap;
import br.com.siesau.entity.Paciente;
import br.com.siesau.persistence.PacienteDao;

@ManagedBean(name = "mbPaciente")
@ViewScoped
public class ManagerBeanPaciente implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private Paciente paciente;
	private List<Paciente> pacientes;

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

			paciente.setLatitude(coordenadas.get(0));
			paciente.setLongitude(coordenadas.get(1));

			System.out.println("Coordenadas " + coordenadas);

			paciente.setDataCad(new Date());
			new PacienteDao(new Paciente()).salva(paciente);
			fc.addMessage("form1", new FacesMessage("Paciente " + paciente.getNome() + " salvo com sucesso"));
			paciente = new Paciente();
			pacientes = new PacienteDao(new Paciente()).lista();

		} catch (Exception e) {
			fc.addMessage("form1", new FacesMessage("Error: " + e.getMessage()));
		}
	}

	public void excluir() {
		FacesContext fc = FacesContext.getCurrentInstance();

		try {

			new PacienteDao(new Paciente()).deleta(paciente);
			fc.addMessage("form2", new FacesMessage("Paciente " + paciente.getNome() + " excluído"));
			pacientes = new PacienteDao(new Paciente()).lista();

		} catch (Exception e) {
			fc.addMessage("form2", new FacesMessage("Error: " + e.getMessage()));
			e.printStackTrace();
		}
	}

	public void editarLinha(RowEditEvent row) {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			Paciente selecionado = (Paciente) row.getObject();
			new PacienteDao(new Paciente()).atualiza(selecionado);
			fc.addMessage("msgs", new FacesMessage("Paciente " + selecionado.getNome() + " editado"));
			pacientes = new PacienteDao(new Paciente()).lista();

		} catch (Exception e) {
			fc.addMessage("form2", new FacesMessage("Error: " + e.getMessage()));
			e.printStackTrace();
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

	private String getRandomImageName() {
		int i = (int) (Math.random() * 10000000);

		return String.valueOf(i);
	}

	public String getFilename() {
		return filename;
	}	
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void oncapture(CaptureEvent captureEvent) {
		filename = getRandomImageName();
		byte[] data = captureEvent.getData();

		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String newFileName = externalContext.getRealPath("") + File.separator + "resources" + File.separator + "demo"
				+ File.separator + "images" + File.separator + "photocam" + File.separator + filename + ".jpeg";

		FileImageOutputStream imageOutput;
		try {
			imageOutput = new FileImageOutputStream(new File(newFileName));
			imageOutput.write(data, 0, data.length);
			imageOutput.close();
		} catch (IOException e) {
			throw new FacesException("Error in writing captured image.", e);
		}
	}

}
