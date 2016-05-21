package br.com.siesau.persistence;

import javax.persistence.TypedQuery;

import br.com.siesau.entity.UnidadeSaude;

public class UnidadeSaudeDao extends GenericDao<UnidadeSaude> {
	
	public UnidadeSaudeDao(UnidadeSaude unidadeSaude){
		super(unidadeSaude);
	}
	
	public UnidadeSaude findByCNPJ(String cnpj) {
		if(cnpj.equals(""))
		return null;
		
		String consulta = "select u from UnidadeSaude u where u.cnpj = :cnpj";
		TypedQuery<UnidadeSaude> query = manager.createQuery(consulta, UnidadeSaude.class);
		query.setParameter("cnpj", cnpj);
		UnidadeSaude especialidade = query.getSingleResult();

		return especialidade;
	}

}
