package br.com.siesau.persistence;

import javax.persistence.TypedQuery;

import br.com.siesau.entity.Medicamento;

public class MedicamentoDao extends GenericDao<Medicamento> {

	public MedicamentoDao(Medicamento func) {
		super(func);
	}

	public Medicamento buscarMedicamento(Medicamento medicamento) {

		if (medicamento == null)
			return null;

		String consulta = "select m from Medicamento m where upper(m.nome_ref) = :nome_ref "
				+ "and upper(m.subst) = :subst " + "and upper(m.forma) = :forma " + "and upper(m.ms) = :ms";
		TypedQuery<Medicamento> query = manager.createQuery(consulta, Medicamento.class);
		query.setParameter("nome_ref", medicamento.getNomeRef().toUpperCase().trim());
		query.setParameter("subst", medicamento.getSubst().toUpperCase().trim());
		query.setParameter("forma", medicamento.getForma().toUpperCase().trim());
		query.setParameter("ms", medicamento.getMs().toUpperCase().trim());
		Medicamento paciente = query.getSingleResult();
		return paciente;

	}
}