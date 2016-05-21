package br.com.siesau.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PacienteDTO {
	private String bairro;
	private String sexo;
	private Integer quantidade;
	private Double latitude;
	private Double longitude;
	private Date ano;
	public PacienteDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Date getAno() {
		new SimpleDateFormat("yyyy").format(ano);
		return ano;
	}

	public void setAno(Date ano) {
		this.ano = ano;
	}
	
}
