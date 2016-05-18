package br.com.siesau.persistence;

import javax.persistence.TypedQuery;

import br.com.siesau.entity.Doenca;

public class DoencaDao extends GenericDao<Doenca> {

	public DoencaDao(Doenca classe) {
		super(classe);
	}
	
	public Doenca pesquisaCID(Doenca doenca) {
		String consulta = "select d from Doenca d where d.cid = :cid";
		TypedQuery<Doenca> query = manager.createQuery(consulta, Doenca.class);
		query.setParameter("cid", doenca.getCid().toUpperCase());
		Doenca rDoenca = query.getSingleResult();
		return rDoenca;
	}

}
