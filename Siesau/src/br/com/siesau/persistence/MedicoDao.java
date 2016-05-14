package br.com.siesau.persistence;

import java.util.List;

import br.com.siesau.entity.Funcionario;

public class MedicoDao extends GenericDao<Funcionario> {

	public MedicoDao(Funcionario func) {
		super(func);
	}

	@SuppressWarnings("unchecked")
	public List<Funcionario> lista() {
		return manager.createQuery(("FROM " + classe.getClass().getName()) + " WHERE cd_cargo = 1")
				.getResultList();
	}

}
