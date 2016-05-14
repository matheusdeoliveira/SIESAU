package br.com.siesau.manager;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.siesau.entity.Exame;
import br.com.siesau.persistence.ExameDao;

@ManagedBean(name="mbExame")
@ViewScoped
public class ManagerBeanExame implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Exame exame;
	private List<Exame> exames;
	
	@PostConstruct
	public void init(){
		exame = new Exame();
	}
	
	public void create(){
		FacesContext fc = FacesContext.getCurrentInstance();
		try{
			new ExameDao(new Exame()).salva(exame);
			fc.addMessage("form1", new FacesMessage("Fornecedor  salvo com sucesso"));
			
			
			
		}catch(Exception e){}
		fc.addMessage("form1", new FacesMessage("Fornecedor  salvo com sucesso"));
	
	}
	
}
