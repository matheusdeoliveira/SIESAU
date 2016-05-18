package br.com.siesau.persistence;

import java.util.List;

import javax.persistence.TypedQuery;

import br.com.siesau.control.GoogleMap;
import br.com.siesau.entity.Paciente;

public class PacienteDao extends GenericDao<Paciente> {

	public PacienteDao(Paciente pacpro) {
		super(pacpro);
	}

	public void salva(Paciente c) {
		try {

			List<String> coordenadas = GoogleMap
					.buscaCoordenadas(c.getEndereco() + " " + c.getBairro() + " " + c.getCidade());

			c.setLatitude(coordenadas.get(0));
			c.setLongitude(coordenadas.get(1));

			System.out.println("Coordenadas " + coordenadas);

			transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(manager.contains(c) ? c : manager.merge(c));
			transaction.commit();
		} catch (Exception e) {
			if (manager.isOpen()) {
				manager.getTransaction().rollback();
			}
			e.printStackTrace();
		} finally {
			if (manager.isOpen()) {
				manager.close();
			}
		}

	}

	public Paciente pesquisaCPF(String cpf) {
		String consulta = "select p from Paciente p where p.cpf = :cpf";
		TypedQuery<Paciente> query = manager.createQuery(consulta, Paciente.class);
		query.setParameter("cpf", cpf.toString());
		Paciente paciente = query.getSingleResult();
		return paciente;
	}

	public Paciente pesquisaRG(String rg) {
		String consulta = "select p from Paciente p where p.rg = :rg";
		TypedQuery<Paciente> query = manager.createQuery(consulta, Paciente.class);
		query.setParameter("rg", rg.toString());
		Paciente paciente = query.getSingleResult();
		return paciente;
	}

	public Paciente pesquisaCartaoSUS(String sus) {
		String consulta = "select p from Paciente p where p.cartaoSus = :sus";
		TypedQuery<Paciente> query = manager.createQuery(consulta, Paciente.class);
		query.setParameter("sus", sus);
		Paciente paciente = query.getSingleResult();
		return paciente;
	}
	
	

}
