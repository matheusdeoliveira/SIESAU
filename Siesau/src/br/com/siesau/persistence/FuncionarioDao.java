package br.com.siesau.persistence;

import javax.persistence.TypedQuery;

import br.com.siesau.entity.Funcionario;

public class FuncionarioDao extends GenericDao<Funcionario> {

	public FuncionarioDao(Funcionario func) {
		super(func);
	}

	public Funcionario login(Funcionario f) {
		String consulta = "select f from Funcionario f where f.login = :login and f.senha = :senha";
		TypedQuery<Funcionario> query = manager.createQuery(consulta, Funcionario.class);
		query.setParameter("login", f.getLogin());
		query.setParameter("senha", f.getSenha());
		Funcionario funcionario = query.getSingleResult();
		return funcionario;
	}

}
