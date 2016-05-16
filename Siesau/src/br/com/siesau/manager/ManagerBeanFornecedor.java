package br.com.siesau.manager;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import br.com.siesau.entity.Fornecedor;
import br.com.siesau.persistence.FornecedoreDao;

@ManagedBean(name = "mbFornecedor")
@ViewScoped
public class ManagerBeanFornecedor implements Serializable {

	private static final long serialVersionUID = 1L;
	private Fornecedor fornecedor;
	private Fornecedor selecionado;
	private List<Fornecedor> fornecedores;
	private String[] uf = { "", "AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MS", "MT", "PA", "PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RS", "SC", "SE", "SP", "TO" };
	
	
	@PostConstruct
	public void init() {
		fornecedor = new Fornecedor();
		fornecedores = new FornecedoreDao(new Fornecedor()).lista();
		
	}

	public void salvar() {
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			fornecedor.setAtivo(true);
			new FornecedoreDao(new Fornecedor()).salva(fornecedor);
			fc.addMessage("form1", new FacesMessage("Fornecedor " + fornecedor.getRazSocial() + " salvo com sucesso"));
			fornecedor = new Fornecedor();
			fornecedores = new FornecedoreDao(new Fornecedor()).lista();

		} catch (Exception e) {
			fc.addMessage("form1", new FacesMessage("Error: " + e.getMessage()));
			e.printStackTrace();
		}

	}

	public void excluir() {
		FacesContext fc = FacesContext.getCurrentInstance();

		try {

			new FornecedoreDao(new Fornecedor()).deleta(fornecedor);

			fc.addMessage("form2", new FacesMessage("Fornecedor " + fornecedor.getRazSocial() + " excluído"));
			fornecedores = new FornecedoreDao(new Fornecedor()).lista();
		} catch (Exception e) {
			fc.addMessage("form2", new FacesMessage("Error: " + e.getMessage()));
			e.printStackTrace();
		}
	}

	public void editar() {
			FacesContext fc = FacesContext.getCurrentInstance();
		try {
			
			new FornecedoreDao(new Fornecedor()).atualiza(selecionado);
			fc.addMessage("form2", new FacesMessage("Fornecedor " + selecionado.getRazSocial() + " editado"));
			fornecedores = new FornecedoreDao(new Fornecedor()).lista();
			
		} catch (Exception e) {
			fc.addMessage("form2", new FacesMessage("Error: " + e.getMessage()));
			e.printStackTrace();
		}
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<Fornecedor> getFornecedores() {
		if (fornecedores == null) {
			fornecedores = new FornecedoreDao(new Fornecedor()).lista();
		}
		return fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	public String[] getUf() {
		return uf;
	}

	public void setUf(String[] uf) {
		this.uf = uf;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Fornecedor getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(Fornecedor selecionado) {
		this.selecionado = selecionado;
	}
	
	

}
