package br.com.siesau.persistence;

import java.util.List;

import br.com.siesau.entity.ExamLaud;
import br.com.siesau.entity.Laudo;

public class ExamLaudDao extends GenericDao<ExamLaud> {
	
	public ExamLaudDao(ExamLaud examLaud){
		super(examLaud);
	}
	
	@SuppressWarnings("unchecked")
	public List<ExamLaud> lista(Laudo laudo){			
		return manager.createQuery(("FROM " + classe.getClass().getName())+" WHERE cd_laudo = "+laudo.getCdLaudo())
				.getResultList();	
	}

}
