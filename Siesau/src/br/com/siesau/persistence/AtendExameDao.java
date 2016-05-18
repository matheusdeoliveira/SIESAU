package br.com.siesau.persistence;

import java.util.List;

import br.com.siesau.entity.AtendExame;
import br.com.siesau.entity.Atendimento;

public class AtendExameDao extends GenericDao<AtendExame> {

	public AtendExameDao(AtendExame atendExame) {
		super(atendExame);
	}
	
	@SuppressWarnings("unchecked")
	public List<AtendExame> lista(Atendimento atendimento){			
		return manager.createQuery(("FROM " + classe.getClass().getName())+" WHERE cd_atend = "+atendimento.getCdAtend())
				.getResultList();	
	}
}
