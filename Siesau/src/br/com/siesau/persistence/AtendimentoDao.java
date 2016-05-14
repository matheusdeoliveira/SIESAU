package br.com.siesau.persistence;

import javax.persistence.TypedQuery;

import br.com.siesau.entity.Atendimento;

public class AtendimentoDao extends GenericDao<Atendimento> {

	public AtendimentoDao(Atendimento atendimento) {
		super(atendimento);
	}

	public Atendimento pesquisaCodigoAtendimento(Integer cd) {
		String consulta = "select a from Atendimento a where cd_atend = :cd";
		TypedQuery<Atendimento> query = manager.createQuery(consulta, Atendimento.class);
		query.setParameter("cd", cd);
		Atendimento atendimento = query.getSingleResult();
		return atendimento;
	}
}
