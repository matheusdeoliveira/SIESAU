package br.com.siesau.entity;

public class DoencaDTO {
	private String nomeDoenca;
	private int qnt;
	private Double idade;
	private Double ano;
	
	public DoencaDTO() {
		// TODO Auto-generated constructor stub
	}

	public DoencaDTO(String nomeDoenca, int qnt, double idade, double ano) {
		this.nomeDoenca = nomeDoenca;
		this.qnt = qnt;
		this.idade = idade;
		this.ano = ano;
	}
	
	@Override
	public String toString() {
		return "DoencaDTO [nomeDoenca=" + nomeDoenca + ", qnt=" + qnt + ", idade=" + idade + ", ano="
				+ ano + "\n";
	}

	public String getNomeDoenca() {
		return nomeDoenca;
	}

	public void setNomeDoenca(String nomeDoenca) {
		this.nomeDoenca = nomeDoenca;
	}

	public int getQnt() {
		return qnt;
	}

	public void setQnt(int qnt) {
		this.qnt = qnt;
	}

	public Double getIdade() {
		return idade;
	}

	public void setIdade(Double idade) {
		this.idade = idade;
	}

	public Double getAno() {
		return ano;
	}

	public void setAno(Double ano) {
		this.ano = ano;
	}
}
