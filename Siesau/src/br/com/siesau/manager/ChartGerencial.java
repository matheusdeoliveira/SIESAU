package br.com.siesau.manager;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;

import br.com.siesau.entity.Doenca;
import br.com.siesau.entity.DoencaDTO;
import br.com.siesau.persistence.DoencaDao;

@ManagedBean(name = "chartGerencial")
@ViewScoped
public class ChartGerencial implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<DoencaDTO> dtosIdade;
	private List<DoencaDTO> dtosNome;

	private PieChartModel chartPie;
	private PieChartModel chartPie2;

	private LineChartModel chartLine;
	private List<Doenca> doencas;
	private String cid;

	private Map<String, String> mostraDoencas;

	@PostConstruct
	private void init() {
		dtosIdade = new DoencaDao(new Doenca()).listaDoecaPorIdade();
		dtosNome = new DoencaDao(new Doenca()).listaDoecaPorNome();
		doencas = new DoencaDao(new Doenca()).lista();
		criarModeloPie();
		criarModeloLine();

		// Carregando Itens da Tela
		mostraDoencas = new HashMap<String, String>();
		for (int i = 0; i < doencas.size(); i++) {
			mostraDoencas.put(doencas.get(i).getNome().toString().toUpperCase(), doencas.get(i).getCid().toString());
		}
	}

	public void recarregaPie(){
		iniciarModeloPie2();
	}
	
	public void criarModeloPie() {
		iniciarModeloPie();
		iniciarModeloPie2();
	}

	public void criarModeloLine() {
		chartLine = iniciarModeloLine();
		int max = 0;

		for (int i = 0; i < dtosNome.size(); i++) {
			max = +dtosNome.get(i).getQnt();
		}

		chartLine.setTitle("Evolução das Doenças através do Tempo");
		chartLine.setLegendPosition("e");
		chartLine.setShowPointLabels(true);
		chartLine.getAxes().put(AxisType.X, new CategoryAxis("Years"));
		Axis yAxis = chartLine.getAxis(AxisType.Y);
		yAxis.setLabel("Numero de Casos");
		yAxis.setMin(0);
		yAxis.setMax(max);
	}

	public LineChartModel iniciarModeloLine() {
		LineChartModel model = new LineChartModel();

		LineChartSeries linhaDoenca = new LineChartSeries();

		String temp = "";

		for (int j = 0; j < dtosNome.size(); j++) {

			if (temp != dtosNome.get(j).getNomeDoenca()) {
				temp = dtosNome.get(j).getNomeDoenca();

				for (int i = 0; i < dtosNome.size(); i++) {
					linhaDoenca.setLabel(temp);

					if (dtosNome.get(i).getNomeDoenca().equals(temp)) {
						linhaDoenca.set(String.valueOf(dtosNome.get(i).getAno().intValue()), dtosNome.get(i).getQnt());
					}
				}
				model.addSeries(linhaDoenca);
			}
		}
		return model;
	}

	public void iniciarModeloPie() {
		chartPie = new PieChartModel();
		for (int i = 0; i < dtosNome.size(); i++) {
			chartPie.set(dtosNome.get(i).getNomeDoenca(), dtosNome.get(i).getQnt());
		}
	}

	public void iniciarModeloPie2() {
		System.out.println(cid);
		List<DoencaDTO> dtosCID = new DoencaDao(new Doenca()).listaDoecaPorCID(cid);
		chartPie2 = new PieChartModel();
			for (int i = 0; i < dtosCID.size(); i++) {
				chartPie2.set("Casos de " + dtosCID.get(i).getNomeDoenca() + " com "
						+ dtosCID.get(i).getIdade().intValue() + " anos", dtosCID.get(i).getQnt());
		}
		
	}

	public PieChartModel getChartDonut() {
		return chartPie2;
	}

	public LineChartModel getChartLine() {
		return chartLine;
	}

	public List<Doenca> getDoencas() {
		return doencas;
	}

	public void setDoencas(List<Doenca> doencas) {
		this.doencas = doencas;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public Map<String, String> getMostraDoencas() {
		return mostraDoencas;
	}

	public void setMostraDoencas(Map<String, String> mostraDoencas) {
		this.mostraDoencas = mostraDoencas;
	}

	public PieChartModel getChartPie() {
		return chartPie;
	}

	public PieChartModel getChartPie2() {
		return chartPie2;
	}

}
