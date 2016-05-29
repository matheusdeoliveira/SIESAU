package br.com.siesau.manager;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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
@ViewScoped
public class MapaBean implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private List<String> cidades;
	private String cidadeSelecionada;
	private List<PacienteDTO> pacientedtoMap;
	private List<PacienteDTO> pacientesdto;
	private List<PacienteDTO> sexodto;
	private PieChartModel grafico;
	private BarChartModel grafico2;
	private List<String> doencas;
	private List<String> doencasSelecionadas;


	@PostConstruct
	public void inti() {
		
			cidades = new ArrayList<>();			
			doencas = new ArrayList<>();	
			grafico = new PieChartModel();
			criargrafico();
			grafico2 = new BarChartModel();
			criargrafico2();
			

		
	}
	
	public void buscar(){
					
		try {

			pacientedtoMap = new PacienteDao(new Paciente()).consultaDoencasCidadeMapa(doencasSelecionadas, cidadeSelecionada);

			pacientesdto = new PacienteDao(new Paciente()).consultaDoencasCidade(doencasSelecionadas, cidadeSelecionada);

			sexodto = new PacienteDao(new Paciente()).pesquisaSexo(doencasSelecionadas, cidadeSelecionada);
			
			criargrafico();
			criargrafico2();
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}


	public void limpar(){
					
		try {

			pacientedtoMap = new ArrayList<>();

			pacientesdto = new ArrayList<>();

			sexodto = new ArrayList<>();
			
			
			grafico = new PieChartModel();
			grafico.setTitle("Aguardando Filtros");
			grafico.set("Homens", 0);
			grafico.set("Mulheres", 0);

			grafico.setLegendPosition("w");

			
			grafico2 = new BarChartModel();
			ChartSeries homens = new ChartSeries();
			ChartSeries mulheres = new ChartSeries();
			grafico2 = new BarChartModel();
			grafico2.setLegendPosition("e");
			grafico2.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
			grafico.setSeriesColors("4876FF,FF00FF");
			grafico2.setSeriesColors("4876FF,FF00FF");
			grafico2.setBarWidth(30);
			homens.setLabel("Homens");
			mulheres.setLabel("Mulheres");

			Axis xAxis = grafico2.getAxis(AxisType.X);
			xAxis.setLabel("Ano");

			Axis yAxis = grafico2.getAxis(AxisType.Y);
			yAxis.setLabel("Sexo");
			yAxis.setMin(0);
			yAxis.setMax(1000);
			
			homens.set(0, 0);
			mulheres.set(0,0);
			grafico2.setTitle("Índices por Ano");
			grafico2.addSeries(homens);
			grafico2.addSeries(mulheres);	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

	
	
	public void itemSeleciondado(ItemSelectEvent event) {
		/* Aqui se cria as atualizações nos outros componentes */
	}

	private void criargrafico() {

		if(sexodto == null){
			grafico.setTitle("Aguardando Filtros");
			grafico.set("Homens", 0);
			grafico.set("Mulheres", 0);

			grafico.setLegendPosition("w");
		}else{
		
		grafico.setTitle("Doenças");
		
		grafico.set("Homens", sexodto.get(1).getQuantidade());
		grafico.set("Mulheres", sexodto.get(0).getQuantidade());

		grafico.setLegendPosition("w");
		}
	}

	private void criargrafico2() {

		ChartSeries homens = new ChartSeries();
		ChartSeries mulheres = new ChartSeries();
		grafico2 = new BarChartModel();
		grafico2.setLegendPosition("e");
		grafico2.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
		grafico.setSeriesColors("4876FF,FF00FF");
		grafico2.setSeriesColors("4876FF,FF00FF");
		grafico2.setBarWidth(30);

		if(sexodto == null){
			homens.setLabel("Homens");
			mulheres.setLabel("Mulheres");

			Axis xAxis = grafico2.getAxis(AxisType.X);
			xAxis.setLabel("Ano");

			Axis yAxis = grafico2.getAxis(AxisType.Y);
			yAxis.setLabel("Sexo");
			yAxis.setMin(0);
			yAxis.setMax(1000);
			
			homens.set(0, 0);
			mulheres.set(0,0);
			grafico2.setTitle("Índices por Ano");
			grafico2.addSeries(homens);
			grafico2.addSeries(mulheres);			
			
		}else{
		homens.set(new SimpleDateFormat("yyyy").format(sexodto.get(1).getAno()), sexodto.get(1).getQuantidade());
		mulheres.set(new SimpleDateFormat("yyyy").format(sexodto.get(0).getAno()), sexodto.get(0).getQuantidade());
		grafico2.setTitle("Índices por Ano");
		grafico2.addSeries(homens);
		grafico2.addSeries(mulheres);
		homens.setLabel("Homens");
		mulheres.setLabel("Mulheres");

		Axis xAxis = grafico2.getAxis(AxisType.X);
		xAxis.setLabel("Ano");

		Axis yAxis = grafico2.getAxis(AxisType.Y);
		yAxis.setLabel("Sexo");
		yAxis.setMin(0);
		yAxis.setMax(1000);
		}
		
	}


	public List<String> getCidades() {
		return cidades;
	}

	public void setCidades(List<String> cidades) {
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

	public BarChartModel getGrafico2() {
		return grafico2;
	}

	public void setGrafico2(BarChartModel grafico2) {
		this.grafico2 = grafico2;
	}

	public List<PacienteDTO> getSexodto() throws Exception {
		return sexodto;
	}

	public void setSexodto(List<PacienteDTO> sexodto) {
		this.sexodto = sexodto;
	}

	public List<String> getDoencas() {
		return doencas;
	}

	public void setDoencas(List<String> doencas) {
		this.doencas = doencas;
	}

	public List<PacienteDTO> getPacientedtoMap() {
		return pacientedtoMap;
	}

	public void setPacientedtoMap(List<PacienteDTO> pacientedtoMap) {
		this.pacientedtoMap = pacientedtoMap;
	}

	public String getCidadeSelecionada() {
		return cidadeSelecionada;
	}

	public void setCidadeSelecionada(String cidadeSelecionada) {
		this.cidadeSelecionada = cidadeSelecionada;
	}

	public List<String> getDoencasSelecionadas() {
		return doencasSelecionadas;
	}

	public void setDoencasSelecionadas(List<String> doencasSelecionadas) {
		this.doencasSelecionadas = doencasSelecionadas;
	}
	
	

}
