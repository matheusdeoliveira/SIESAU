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
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
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

	private PieChartModel chartGeral;
	private PieChartModel chartDetalhado;

	private LineChartModel chartLinha;

	private List<Doenca> doencas;
	private String cid;

	private Map<String, String> mostraDoencas;

	@PostConstruct
	private void init() {

		doencas = new DoencaDao(new Doenca()).listaDoecasOcorridas();

		// Carregando Itens da Tela
		mostraDoencas = new HashMap<String, String>();
		for (int i = 0; i < doencas.size(); i++) {
			mostraDoencas.put(doencas.get(i).getNome().toString().toUpperCase(), doencas.get(i).getCid().toString());
		}

		setCid("A90");

		iniciarModeloPieGeral();
		iniciarModeloPieDetalhado();
		iniciarModeloLinha();

	}

	public void iniciarModeloPieGeral() {
		dtosNome = new DoencaDao(new Doenca()).listaDoecaPorNome();

		chartGeral = new PieChartModel();
		for (int i = 0; i < dtosNome.size(); i++) {
			chartGeral.set(dtosNome.get(i).getNomeDoenca(), dtosNome.get(i).getQnt());
		}
		chartGeral.setTitle("Casos Registrados de Doenças.");
		chartGeral.setShowDataLabels(true);
		chartGeral.setLegendPosition("w");
		chartGeral.setDiameter(230);
	}

	public void iniciarModeloPieDetalhado() {
		dtosIdade = new DoencaDao(new Doenca()).listaDoecaPorCID(getCid());
		chartDetalhado = new PieChartModel();

		String texto;
		String doenca = "";

		for (int i = 0; i < dtosIdade.size(); i++) {

			doenca = dtosIdade.get(i).getNomeDoenca();

			if (dtosIdade.get(i).getIdade() < 1) {
				texto = "Caso de pacientes recem nascidos (Menores de 1 ano)";
			} else {
				texto = "Caso de pacientes com " + dtosIdade.get(i).getIdade().intValue() + " anos.";
			}

			chartDetalhado.set(texto, dtosIdade.get(i).getQnt());
		}
		chartDetalhado.setTitle("Detalhamento da Doença " + doenca);
		chartDetalhado.setShowDataLabels(true);
		chartDetalhado.setDiameter(230);
	}

	public void iniciarModeloLinha() {
		dtosIdade = new DoencaDao(new Doenca()).listaDoecaPorCIDporAno(getCid());
		chartLinha = new LineChartModel();
		ChartSeries linha;

		int max = 0;
		int qnt = 0;
		int anoTemp = dtosIdade.get(0).getAno().intValue();
		linha = new ChartSeries();

		for (int i = 0; i < dtosIdade.size(); i++) {
			
			if (dtosIdade.get(i).getAno().intValue() != anoTemp){
				linha.set(anoTemp,qnt);
				qnt = 0;
				anoTemp = dtosIdade.get(i).getAno().intValue();
			}

			qnt = qnt + dtosIdade.get(i).getQnt();
			
			if (max < qnt) {
				max = max + qnt;
			}
		}

		linha.set(anoTemp,qnt);
		linha.setLabel(dtosIdade.get(0).getNomeDoenca());

		chartLinha.addSeries(linha);
		chartLinha.setShowPointLabels(true);
		chartLinha.setLegendPosition("w");
		chartLinha.setTitle("Evolução através do tempo");
		chartLinha.setLegendPosition("e");
		chartLinha.setShowPointLabels(true);
		chartLinha.getAxes().put(AxisType.X, new CategoryAxis("Ano"));
		Axis yAxis = chartLinha.getAxis(AxisType.Y);
		yAxis.setLabel("Casos");
		yAxis.setMin(0);
		yAxis.setMax(max * 2);
	}

	public List<DoencaDTO> getDtosIdade() {
		return dtosIdade;
	}

	public void setDtosIdade(List<DoencaDTO> dtosIdade) {
		this.dtosIdade = dtosIdade;
	}

	public List<DoencaDTO> getDtosNome() {
		return dtosNome;
	}

	public void setDtosNome(List<DoencaDTO> dtosNome) {
		this.dtosNome = dtosNome;
	}

	public PieChartModel getChartGeral() {
		return chartGeral;
	}

	public void setChartGeral(PieChartModel chartGeral) {
		this.chartGeral = chartGeral;
	}

	public PieChartModel getChartDetalhado() {
		return chartDetalhado;
	}

	public void setChartDetalhado(PieChartModel chartDetalhado) {
		this.chartDetalhado = chartDetalhado;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public LineChartModel getChartLinha() {
		return chartLinha;
	}

	public void setChartLinha(LineChartModel chartLinha) {
		this.chartLinha = chartLinha;
	}

}
