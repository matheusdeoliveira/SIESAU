package br.com.siesau.persistence;

import javax.persistence.TypedQuery;

import br.com.siesau.entity.Especialidade;

public class EspecialidadeDao extends GenericDao<Especialidade> {
	
	public EspecialidadeDao(Especialidade especialidade){
		super(especialidade);
	}

	public Especialidade findByEspecialidade(String stringEspecialidade) {
		if(stringEspecialidade.equals(""))
		return null;
		
		String consulta = "select esp from Especialidade esp where upper(esp.especialidade) = :data";
		TypedQuery<Especialidade> query = manager.createQuery(consulta, Especialidade.class);
		query.setParameter("data", stringEspecialidade.toUpperCase());
		Especialidade especialidade = query.getSingleResult();

		return especialidade;
	}

}
