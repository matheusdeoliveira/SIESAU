package br.com.siesau.manager;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.siesau.entity.Laudo;
import br.com.siesau.persistence.LaudoDao;

/**
 * @author Diogo Freitas
 *
 */

@ManagedBean(name = "mbLaudo")
public class ManagerBeanLaudo implements Serializable {

	private final static long serialVersionUID = 1L;
	private Laudo laudo;
	private List<Laudo> laudos;

	@PostConstruct
	public void init() {
		laudo = new Laudo();
		laudos = new LaudoDao(new Laudo()).lista();
	}

	public void salvar() {
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			new LaudoDao(new Laudo()).salva(laudo);
			fc.addMessage("form1", new FacesMessage("Laudo nº " + laudo.getCdLaudo() + " salvo com sucesso."));
			laudo = new Laudo();

		} catch (Exception e) {
			fc.addMessage("form1", new FacesMessage("Error: " + e.getMessage()));
			e.printStackTrace();
		}
	}

	public Laudo getLaudo() {
		return laudo;
	}

	public void setLaudo(Laudo laudo) {
		this.laudo = laudo;
	}

	public List<Laudo> getLaudos() {
		return laudos;
	}

	public void setLaudos(List<Laudo> laudos) {
		this.laudos = laudos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
