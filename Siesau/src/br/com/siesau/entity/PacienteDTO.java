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
	private String cid;
	private String cor;

	public PacienteDTO() {
		// TODO Auto-generated constructor stub
	}

	public PacienteDTO(String bairro, String sexo, Integer quantidade, Double latitude, Double longitude, Date ano) {
		super();
		this.bairro = bairro;
		this.sexo = sexo;
		this.quantidade = quantidade;
		this.latitude = latitude;
		this.longitude = longitude;
		this.ano = ano;
	}

	
	@Override
	public String toString() {
		return "PacienteDTO [bairro=" + bairro + ", sexo=" + sexo + ", quantidade=" + quantidade + ", latitude="
				+ latitude + ", longitude=" + longitude + ", ano=" + ano + ", cid=" + cid + ", cor=" + cor + "]"+ "\n";
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

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

}
