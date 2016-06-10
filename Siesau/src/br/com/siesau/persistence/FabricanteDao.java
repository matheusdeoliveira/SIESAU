package br.com.siesau.persistence;

import javax.persistence.TypedQuery;

import br.com.siesau.entity.Fabricante;
import br.com.siesau.persistence.GenericDao;

public class FabricanteDao extends GenericDao<Fabricante> {

	public FabricanteDao(Fabricante classe) {
		super(classe);
	}

	public Fabricante buscaPorCNPJ(String cnpj) {
		if(cnpj.equals(""))
		return null;
	
		String consulta = "select m from Fabricante m where m.cnpj = :cnpj";
		TypedQuery<Fabricante> query = manager.createQuery(consulta, Fabricante.class);
		query.setParameter("cnpj", cnpj);
		Fabricante fabricante = query.getSingleResult();
		return fabricante;
	}

}
