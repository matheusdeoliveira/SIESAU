package br.com.siesau.manager;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.PieChartModel;

import br.com.siesau.entity.PacienteDTO;

@ManagedBean(name = "mbMap")
public class MapaBean {

	private List<PacienteDTO> pacientesdto;
	private String cidades;
	private PieChartModel grafico;

	@PostConstruct
	private void inti() {

		pacientesdto = new ArrayList<>();
		grafico = new PieChartModel();
		criargrafico();
	}

	public void itemSeleciondado(ItemSelectEvent event) {
		/* Aqui se cria as atualizações nos outros componentes */
	}

	private void criargrafico() {

		grafico.setTitle("Dengue");

		int countHomens = 0;
		int countMulheres = 0;
		for (int i = 0; i < pacientesdto.size(); i++) {
			if (pacientesdto.get(i).getSexo().equalsIgnoreCase("M")) {
				countHomens++;
			} else if (pacientesdto.get(i).getSexo().equalsIgnoreCase("F")) {
				countMulheres++;
			}

		}
		grafico.set("Homens", countHomens);
		grafico.set("Mulheres", countMulheres);

		grafico.setLegendPosition("w");
	}

	public String getCidades() {
		return cidades;
	}

	public void setCidades(String cidades) {
		this.cidades = cidades;
	}

	public List<PacienteDTO> getPacientesdto() {
		return pacientesdto;
	}

	public void setPacientesdto(List<PacienteDTO> pacientesdto) {
		this.pacientesdto = pacientesdto;
	}

	public PieChartModel getGrafico() {
		return grafico;
	}

	public void setGrafico(PieChartModel grafico) {
		this.grafico = grafico;
	}

}
