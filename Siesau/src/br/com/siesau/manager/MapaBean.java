package br.com.siesau.manager;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.PieChartModel;

import br.com.siesau.entity.Paciente;
import br.com.siesau.entity.PacienteDTO;
import br.com.siesau.persistence.PacienteDao;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LegendPlacement;

@ManagedBean(name = "mbMap")
public class MapaBean {

	private List<PacienteDTO> pacientesdto;
	private List<PacienteDTO> sexodto;
	private String cidades;
	private PieChartModel grafico;
	private BarChartModel grafico2;

	@PostConstruct
	public void inti() {
		try {
			
			pacientesdto = new PacienteDao(new Paciente()).pesquisaDoencaCidade("DUQUE DE CAXIAS");
			sexodto = new PacienteDao(new Paciente()).pesquisaSexo();
		} catch (Exception e) {
			e.printStackTrace();
		}

		grafico = new PieChartModel();
		criargrafico();
		grafico2 = new BarChartModel();
		criargrafico2();
		grafico2.setLegendPosition("e");
		grafico2.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
		grafico.setSeriesColors("4876FF,FF00FF");
		grafico2.setSeriesColors("4876FF,FF00FF");
		grafico2.setBarWidth(30);
	}

	public void itemSeleciondado(ItemSelectEvent event) {
		/* Aqui se cria as atualizações nos outros componentes */
	}

	private void criargrafico() {

		grafico.setTitle("Dengue");

		/*for (int i = 0; i < pacientesdto.size(); i++) {
			if (pacientesdto.get(i).getSexo().equalsIgnoreCase("M")) {
				countHomens++;
			} else if (pacientesdto.get(i).getSexo().equalsIgnoreCase("F")) {
				countMulheres++;
			}

		}*/
		grafico.set("Homens", sexodto.get(1).getQuantidade());
		grafico.set("Mulheres", sexodto.get(0).getQuantidade());

		grafico.setLegendPosition("w");
	}

	private void criargrafico2() {
		
		ChartSeries homens = new ChartSeries();
		ChartSeries mulheres = new ChartSeries();

		homens.setLabel("Homens");
		mulheres.setLabel("Mulheres");
		
		Axis xAxis = grafico2.getAxis(AxisType.X);
        xAxis.setLabel("Ano");
         
        Axis yAxis = grafico2.getAxis(AxisType.Y);
        yAxis.setLabel("Sexo");
        yAxis.setMin(0);
        yAxis.setMax(1000);

		/*for (int i = 0; i < sexodto.size(); i++) {
			if (pacientesdto.get(i).getSexo().equalsIgnoreCase("m")) {
				homens.set(pacientesdto.get(i).getAno(), pacientesdto.get(i).getQuantidade());

			} else if (pacientesdto.get(i).getSexo().equalsIgnoreCase("f")) {
				mulheres.set(pacientesdto.get(i).getAno(), pacientesdto.get(i).getQuantidade());

			}
		}*/
		homens.set(sexodto.get(1).getAno(),sexodto.get(1).getQuantidade());
		mulheres.set(sexodto.get(0).getAno(),sexodto.get(0).getQuantidade());
		grafico2.setTitle("Índices por Ano");
		grafico2.addSeries(homens);
		grafico2.addSeries(mulheres);
		
	}

	public String getCidades() {
		return cidades;
	}

	public void setCidades(String cidades) {
		this.cidades = cidades;
	}

	public List<PacienteDTO> getPacientesdto() {
		if(pacientesdto == null){
			try{
			pacientesdto = new PacienteDao(new Paciente()).pesquisaDoencaCidade("DUQUE DE CAXIAS");
			}catch(Exception e){
				e.printStackTrace();
			}
			}
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

	public BarChartModel getGrafico2() {
		return grafico2;
	}

	public void setGrafico2(BarChartModel grafico2) {
		this.grafico2 = grafico2;
	}

	public List<PacienteDTO> getSexodto() {
		if(sexodto ==  null){
			try {
				sexodto = new PacienteDao(new Paciente()).pesquisaSexo();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sexodto;
	}

	public void setSexodto(List<PacienteDTO> sexodto) {
		this.sexodto = sexodto;
	}

	
}
