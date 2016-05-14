package br.com.siesau.persistence;

import br.com.siesau.entity.Laudo;
import br.com.siesau.persistence.GenericDao;

public class LaudoDao extends GenericDao<Laudo> {
	
	public LaudoDao(Laudo laudo){
		super(laudo);
	}

}
