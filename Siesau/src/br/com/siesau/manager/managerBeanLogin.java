package br.com.siesau.manager;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.siesau.entity.Funcionario;
import br.com.siesau.entity.UnidadeSaude;
import br.com.siesau.persistence.FuncionarioDao;
import br.com.siesau.persistence.UnidadeSaudeDao;

@ManagedBean(name = "mbLogin")
@SessionScoped
public class managerBeanLogin implements Serializable {

	private static final long serialVersionUID = 1L;
	private Funcionario logado;
	private Funcionario funcionario;
	private List<UnidadeSaude> unidadesDeSaude;

	@PostConstruct
	public void init() {
		logado = new Funcionario();
		funcionario = new Funcionario();
		unidadesDeSaude = new UnidadeSaudeDao(new UnidadeSaude()).lista();
	}

	public String logar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			
			logado = new FuncionarioDao(new Funcionario()).login(funcionario);
			if (logado != null) {
				
				HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
				HttpSession session = request.getSession(true);

				session.setAttribute("funcionario", logado);

				fc.addMessage("form1", new FacesMessage("Usuário Logado"));
				return "/index.jsf";
			} else {
				fc.addMessage("form1", new FacesMessage("Erro no login..."));
			}

		} catch (Exception e) {
			funcionario = new Funcionario();
			fc.addMessage("form1", new FacesMessage("Usuário ou senha incorretos.","Por favor, tente novamente"));
		}

		return null;
	}

	public String logout() {

		try {

			FacesContext fc = FacesContext.getCurrentInstance();
			HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
			HttpSession session = request.getSession(true);

			session.removeAttribute("funcionario");
			session.invalidate();
					} catch (Exception e) {
			e.printStackTrace();
		}

		return "/login.jsf?faces-redirect=true";
	}

	public void filtrar() {
		try {

			FacesContext fc = FacesContext.getCurrentInstance();
			HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
			HttpSession session = request.getSession(true);

			if (session.getAttribute("funcionario") == null) {
				fc.getExternalContext().redirect("/Siesau/login.jsf?erro=true");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Funcionario getLogado() {
		return logado;
	}

	public void setLogado(Funcionario logado) {
		this.logado = logado;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<UnidadeSaude> getUnidadesDeSaude() {
		if(unidadesDeSaude == null){
			unidadesDeSaude = new UnidadeSaudeDao(new UnidadeSaude()).lista();
		}
		return unidadesDeSaude;
	}

	public void setUnidadesDeSaude(List<UnidadeSaude> unidadesDeSaude) {
		this.unidadesDeSaude = unidadesDeSaude;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
