package br.com.siesau.manager;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import br.com.siesau.entity.Paciente;
import br.com.siesau.persistence.PacienteDao;

@ManagedBean(name = "mbMap")
public class MapaBean {

	private List<Paciente> pacientes;

	@PostConstruct
	private void inti() {

		pacientes = new PacienteDao(new Paciente()).lista();
	}

	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

}
