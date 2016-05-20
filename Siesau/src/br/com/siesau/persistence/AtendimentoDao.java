package br.com.siesau.persistence;

import java.util.ArrayList;
import java.util.Date;
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
					+ func.getEspecialidades().get(i).getCdEspec() + "AND cd_sitatend = 1").getResultList());
		}
		return atend;
	}

	@SuppressWarnings("unchecked")
	public List<Atendimento> listaNaoFinalizados() {
		return manager
				.createQuery(("FROM " + Atendimento.class.getName() + " WHERE cd_sitatend = 1 OR cd_sitatend = 2"))
				.getResultList();
	}

	public Atendimento retornaAtendimentoPorData(Date data) {
		String consulta = "select p from " + Atendimento.class.getName() + " p where data_atend = :data ";
		TypedQuery<Atendimento> query = manager.createQuery(consulta, Atendimento.class);
		query.setParameter("data", data);
		Atendimento atendimento = query.getSingleResult();
		return atendimento;
	}
}