package br.com.siesau.manager;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.model.ArrayDataModel;

import br.com.siesau.entity.Coordenadas;
import br.com.siesau.entity.Paciente;
import br.com.siesau.persistence.PacienteDao;

@ManagedBean(name = "mbMap")
public class MapaBean {

	private List<Coordenadas> markers;
	private List<Paciente> pacientes;

	@PostConstruct
	private void inti() {

		pacientes = new PacienteDao(new Paciente()).lista();
		markers = new ArrayList<>();

		markers.add(new Coordenadas(new Double(782551), -122.445368));
		markers.add(new Coordenadas(new Double(37.782745), -122.444586));
		markers.add(new Coordenadas(new Double(37.782842), -122.443688));
		markers.add(new Coordenadas(new Double(37.782919), -122.442815));
		markers.add(new Coordenadas(new Double(37.782992), -122.442112));
		markers.add(new Coordenadas(new Double(37.783100), -122.441461));
		markers.add(new Coordenadas(new Double(37.783206), -122.440829));
		markers.add(new Coordenadas(new Double(37.783273), -122.440324));
		markers.add(new Coordenadas(new Double(37.783316), -122.440023));
		markers.add(new Coordenadas(new Double(37.783357), -122.439794));
		markers.add(new Coordenadas(new Double(37.783371), -122.439687));
		markers.add(new Coordenadas(new Double(37.783368), -122.439666));
		markers.add(new Coordenadas(new Double(37.783383), -122.439594));
		markers.add(new Coordenadas(new Double(37.783508), -122.439525));
		markers.add(new Coordenadas(new Double(37.783842), -122.439591));
		markers.add(new Coordenadas(new Double(37.784147), -122.439668));
		markers.add(new Coordenadas(new Double(37.784206), -122.439686));
		markers.add(new Coordenadas(new Double(37.784386), -122.439790));

	}

	public List<Coordenadas> getMarkers() {
		return markers;
	}

	public void setMarkers(List<Coordenadas> markers) {
		this.markers = markers;
	}

	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

}
