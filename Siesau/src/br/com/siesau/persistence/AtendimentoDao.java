package br.com.siesau.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import br.com.siesau.entity.Atendimento;
import br.com.siesau.entity.Funcionario;

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

	@SuppressWarnings("unchecked")
	public List<Atendimento> listaPorEspecialidade(Funcionario func) {

		List<Atendimento> atend = new ArrayList<>();

		for (int i = 0; i <= func.getEspecialidades().size(); i++) {
			atend.addAll(manager.createQuery(("FROM " + classe.getClass().getName()) + " WHERE cd_espec = "
					+ func.getEspecialidades().get(i).getCdEspec()).getResultList());
		}
		return atend;
	}

}