package br.com.siesau.manager;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.json.JSONException;

import br.com.siesau.control.GoogleMap;
import br.com.siesau.control.viaCEP.ViaCEP;
import br.com.siesau.control.viaCEP.ViaCEPException;

@ViewScoped
@ManagedBean(name = "mbBuscaUPA")
public class ManageBeanBuscaUPA implements Serializable {

	private static final long serialVersionUID = 1L;
	private String cep;
	private String cidade;
	private String logradouro;
	private String bairro;
	private String uf;
	private String latitude;
	private String longitude;
	private ViaCEP viaCep;
	private String link;

	@PostConstruct
	public void init() {
		cep = "";
	}

	public void montaLink() {
		StringBuilder builder = new StringBuilder();
		FacesContext fc = FacesContext.getCurrentInstance();

		if (cep != null && cep != "") {
			try {
				buscaCep();

				List<String> coordenadas = GoogleMap.buscaCoordenadas(logradouro + " " + bairro + " " + cidade);

				latitude = coordenadas.get(0);
				longitude = coordenadas.get(1);

				builder.append("https://www.google.com.br/maps/search/UPA+24hrs/@");
				builder.append(latitude + ",");
				builder.append(longitude + ",13z/");

				link = builder.toString();
				fc.getExternalContext().redirect(link);
				System.out.println(link);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				link = "";
				fc.addMessage(null,
						new FacesMessage("Erro ao Encontrar endereço", 
								"Por favor, verifique o cep digitado."));
			}
		}

	}

	public void buscaCep() {
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			viaCep = new ViaCEP();

			viaCep.buscar(cep);

			cidade = viaCep.getLocalidade();
			logradouro = viaCep.getLogradouro();
			bairro = viaCep.getBairro();
			uf = viaCep.getUf();

		} catch (ViaCEPException e) {
			fc.addMessage("form2", new FacesMessage("Error: " + e.getMessage()));
			e.printStackTrace();
		} catch (JSONException e) {
			fc.addMessage("form2", new FacesMessage("Error: " + e.getMessage()));
			e.printStackTrace();
		}
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public ViaCEP getViaCep() {
		return viaCep;
	}

	public void setViaCep(ViaCEP viaCep) {
		this.viaCep = viaCep;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
}
