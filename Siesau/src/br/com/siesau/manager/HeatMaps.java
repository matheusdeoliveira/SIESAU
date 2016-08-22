package br.com.siesau.manager;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import com.google.maps.model.LatLng;

import br.com.siesau.entity.Paciente;
import br.com.siesau.entity.PacienteDTO;
import br.com.siesau.persistence.PacienteDao;

@ManagedBean(name = "hmaps")
public class HeatMaps {

	private List<String> lista;
	private List<PacienteDTO> pacientes;
	private String latitude;
	private String longitude;

	@PostConstruct
	public void init(){
		lista = new ArrayList<String>();
		try {
			pacientes = new PacienteDao(new Paciente()).pesquisaDoencaCidade("NOVA IGUAÇU");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		latitude = "-22.756132";
		longitude = "-43.460742";

		for (int i = 0; i < pacientes.size(); i++) {
			lista.add(conversorParaLatLng(pacientes.get(i).getLatitude(), pacientes.get(i).getLongitude()));
		}

	}

	public static String conversorParaLatLng(Double lat, Double lgn) {
		String conversor = new String();

		conversor = "new google.maps.LatLng(" + String.valueOf(lat) + "," + String.valueOf(lgn) + ")";

		return conversor;
	}

	public List<String> getLista() {
		return lista;
	}

	public void setLista(List<String> lista) {
		this.lista = lista;
	}

	public List<PacienteDTO> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<PacienteDTO> pacientes) {
		this.pacientes = pacientes;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

//	public static void main(String[] args) {
//		
//		 List<String> lista;
//		 List<PacienteDTO> pacientes = new ArrayList<>();;
//		 String latitude;
//		 String longitude;		
//	
//			lista = new ArrayList<String>();
//			try {
//				pacientes = new PacienteDao(new Paciente()).pesquisaDoencaCidade("NOVA IGUAÇU");
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			latitude = "-22.756132";
//			longitude = "-43.460742";
//
//			for (int i = 0; i < pacientes.size(); i++) {
//				lista.add(conversorParaLatLng(pacientes.get(i).getLatitude(), pacientes.get(i).getLongitude()));
//			}
//
//			System.out.println(lista);
//			
//		}
//	
//	

}	
	
