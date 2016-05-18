package br.com.siesau.persistence;

import java.util.Date;

import javax.persistence.TypedQuery;

import br.com.siesau.entity.Atendimento;
import br.com.siesau.entity.Receita;

public class ReceitaDao extends GenericDao<Receita> {

	public ReceitaDao(Receita classe) {
		super(classe);
	}

	public Receita findByAtendData(Date date, Atendimento atendimento) {

		String consulta = "select p from Receita p where p.data = :data";
		TypedQuery<Receita> query = manager.createQuery(consulta, Receita.class);
		query.setParameter("data", date);
		Receita receita = query.getSingleResult();

		return receita;
	}
}
