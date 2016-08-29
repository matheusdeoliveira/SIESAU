package br.com.siesau.manager;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.google.maps.model.LatLng;

import br.com.siesau.entity.Paciente;
import br.com.siesau.entity.PacienteDTO;
import br.com.siesau.persistence.PacienteDao;

@ManagedBean(name = "hmaps")
@ViewScoped
public class HeatMaps {

	private List<String> lista;
	private List<PacienteDTO> pacientes;
	private String latitude;
	private String longitude;
	private String doencaSelecionada;
	private String cidadeSelecionada;

	@PostConstruct
	public void init(){
		lista = new ArrayList<String>();
		
		latitude = "-22.756132";
		longitude = "-43.460742";

	}
	
	public String buscar(){
		
		FacesContext fc = FacesContext.getCurrentInstance();
		List<String> listaDeDoencas = new ArrayList<>();
		try {
			
			listaDeDoencas.add(doencaSelecionada);
			
			pacientes = new PacienteDao(new Paciente()).consultaDoencasCidadeMapa(listaDeDoencas,cidadeSelecionada);
			lista = new ArrayList<>();
			
			
			
			if(pacientes.size() > 0 ){
			
				latitude = pacientes.get(0).getLatitude().toString();
				longitude =  pacientes.get(0).getLongitude().toString();
				
				for (PacienteDTO paciente : pacientes) {
					lista.add(conversorParaLatLng(paciente.getLatitude(), paciente.getLongitude()));
					
				}
			
			}else{
				
				init();
				fc.addMessage("formFiltros", new FacesMessage("Não existem dados para os parâmetros passados"));
				
			}		
			listaDeDoencas = new ArrayList<>();
			
			return null;
				
		} catch (Exception e) {
			fc.addMessage("formFiltros", new FacesMessage("Error: " + e.getMessage()));
		}
		
		return "mapadecalor.jsf";
				
	}	
	
	
	public String limpar(){
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			
			lista = new ArrayList<>();
			pacientes = new ArrayList<>();
			
			latitude = "-22.756132";
			longitude = "-43.460742";
			
			return "mapadecalor.jsf";
			
		} catch (Exception e) {
			fc.addMessage("formFiltros", new FacesMessage("Error: " + e.getMessage()));
		}
		
		
		return "mapadecalor.jsf";
		
	}



	private static String conversorParaLatLng(Double lat, Double lgn) {
		String conversor = new String();

		conversor = "new google.maps.LatLng(" + String.valueOf(lat) + "," + String.valueOf(lgn) + ")";

		return conversor;
	}
		
	public String getDoencaSelecionada() {
		return doencaSelecionada;
	}

	public void setDoencaSelecionada(String doencaSelecionada) {
		this.doencaSelecionada = doencaSelecionada;
	}

	public String getCidadeSelecionada() {
		return cidadeSelecionada;
	}

	public void setCidadeSelecionada(String cidadeSelecionada) {
		this.cidadeSelecionada = cidadeSelecionada;
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
	
