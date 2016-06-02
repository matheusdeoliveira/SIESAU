package br.com.siesau.persistence;

import java.text.SimpleDateFormat;
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
	
	
	public Receita findByAtendimento(Date date, Atendimento atendimento) {

		String consulta = "select p from Receita p where p.atendimento = :atendimento";
		TypedQuery<Receita> query = manager.createQuery(consulta, Receita.class);
		query.setParameter("atendimento", atendimento);
		Receita receita = query.getSingleResult();

		return receita;
	}
	public static void main(String[] args) {
		try {
			
			Receita dao = new ReceitaDao(new Receita()).findByCode(8802);
			
			System.out.println(dao.getData());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
