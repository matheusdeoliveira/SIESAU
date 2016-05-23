package br.com.siesau.persistence;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.siesau.control.GoogleMap;
import br.com.siesau.entity.Paciente;
import br.com.siesau.entity.PacienteDTO;

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
	
	public List<PacienteDTO> pesquisaDoencaCidade(String cidade) throws Exception{
		String consulta = "select "
						+"p.bairro, "
						+"p.latitude, "
						+"p.longitude, "
						+"p.sexo, "
						+"SUM ((select count(*) from doenca d where ad.cd_doenca = d.cd_doenca)) as quantidade ,"
						+ "date_part('year',a.data_atend) ano "
						+"from " 
						+"atendimento a, " 
						+"atend_doenca ad, " 
						+"doenca d, " 
						+"paciente p "
						+"where "
						+"a.cd_atend = ad.cd_atend and "
						+"ad.cd_doenca = d.cd_doenca and "
						+"a.cd_paciente = p.cd_paciente " 
						+"and p.cidade = :param "
						+"and d.cid = 'A90' "
						+"GROUP BY p.bairro, p.sexo, p.latitude, p.longitude, d.cid, ano";
				
		Query query = manager.createNativeQuery(consulta);
		query.setParameter("param", cidade);
		@SuppressWarnings("unchecked")
		List<Object[]> resultados = query.getResultList();
		List<PacienteDTO> pacientesdto = new ArrayList<>();
		for (Object[] result : resultados) {
			PacienteDTO pacientedto = new PacienteDTO();
			pacientedto.setBairro(result[0].toString());
			pacientedto.setLatitude(Double.parseDouble(result[1].toString()));
			pacientedto.setLongitude(Double.parseDouble(result[2].toString()));
			pacientedto.setSexo(result[3].toString());
			pacientedto.setQuantidade(Integer.parseInt(result[4].toString()));
			DateFormat formatter = new SimpleDateFormat("yyyy");
            Date date = (Date)formatter.parse(result[5].toString());
            pacientedto.setAno(date);
			pacientesdto.add(pacientedto);
			
		}
		return pacientesdto;
	}
	public List<PacienteDTO> pesquisaSexo(List<String> doencas, String cidade) throws Exception{
		String consulta2 = "select p.sexo, SUM ((select count(*) from "
				+ "doenca d where ad.cd_doenca = d.cd_doenca)) as quantidade, "
				+ "date_part('year',a.data_atend) ano  "
				+ "from atendimento a, atend_doenca ad, doenca d, paciente p where "
				+ "a.cd_atend = ad.cd_atend and ad.cd_doenca = d.cd_doenca and a.cd_paciente = p.cd_paciente "
				+ "and d.cid in (:doencas) and p.cidade ~* :cidade "
				+ "GROUP BY p.sexo, ano ";
		
		System.out.println(consulta2);
				
		
		Query query = manager.createNativeQuery(consulta2);
		query.setParameter("doencas", doencas );
		query.setParameter("cidade", cidade);
		@SuppressWarnings("unchecked")
		List<Object[]> resultados = query.getResultList();
		List<PacienteDTO> pacientesdto = new ArrayList<>();
		for (Object[] result : resultados) {
			
			PacienteDTO pacientedto = new PacienteDTO();
			pacientedto.setSexo(result[0].toString());
			pacientedto.setQuantidade(Integer.parseInt(result[1].toString()));
			DateFormat formatter = new SimpleDateFormat("yyyy");
            Date date = (Date)formatter.parse(result[2].toString());
            pacientedto.setAno(date);
            pacientesdto.add(pacientedto);
			
		}
		return pacientesdto;
	}
	
	public List<PacienteDTO> consultaDoencasCidade(List<String> doencas, String cidade) {
		String consulta2 = "select "
				+"p.bairro, "
				+"p.sexo, "
				+ "SUM ((select count(*) from doenca d where ad.cd_doenca = d.cd_doenca)) as quantidade "
				+"from " 
				+"atendimento a, " 
				+"atend_doenca ad, " 
				+"doenca d, "
				+"paciente p "
				+"where "
				+"a.cd_atend = ad.cd_atend and "
				+"ad.cd_doenca = d.cd_doenca and "
				+"a.cd_paciente = p.cd_paciente and "
				+"d.cid in (:doencas) and "
				+"p.cidade ~* :cidade "
				+"GROUP BY p.bairro, p.sexo  order by p.bairro";	
		
		Query query = manager.createNativeQuery(consulta2);
		query.setParameter("doencas", doencas );
		query.setParameter("cidade", cidade);
		@SuppressWarnings("unchecked")
		List<Object[]> resultados = query.getResultList();
		List<PacienteDTO> pacientesdto = new ArrayList<>();
		for (Object[] result : resultados) {
			PacienteDTO pacientedto = new PacienteDTO();
			pacientedto.setBairro(result[0].toString());
			pacientedto.setSexo(result[1].toString());
			pacientedto.setQuantidade(Integer.parseInt(result[2].toString()));
			pacientesdto.add(pacientedto);
			
		}
		return pacientesdto;
	}
	
	
	public List<PacienteDTO> consultaDoencasCidadeMapa(List<String> doencas, String cidade) {
		String consulta2 = " select " 
		        +"p.bairro, "
		        +"p.latitude, "
		        +"p.longitude, "
		        +"p.sexo "
		        +"from  atendimento a, atend_doenca ad, doenca d, paciente p " 
		        +"where a.cd_atend = ad.cd_atend and ad.cd_doenca = d.cd_doenca and a.cd_paciente = p.cd_paciente "
		        + "and d.cid in (:doencas ) and p.cidade ~* :cidade";
		   
		

		Query query = manager.createNativeQuery(consulta2);
		query.setParameter("doencas", doencas );
		query.setParameter("cidade", cidade);
		@SuppressWarnings("unchecked")
		List<Object[]> resultados = query.getResultList();
		List<PacienteDTO> pacientesdto = new ArrayList<>();
		for (Object[] result : resultados) {
			PacienteDTO pacientedto = new PacienteDTO();
			pacientedto.setBairro(result[0].toString());
			pacientedto.setLatitude(Double.parseDouble(result[1].toString()));
			pacientedto.setLongitude(Double.parseDouble(result[2].toString()));
			pacientedto.setSexo(result[3].toString());
			pacientesdto.add(pacientedto);
			
		}
		return pacientesdto;
	}
	
	public static void main(String[] args) {
		
		try {
			List<String> lis = new ArrayList<>();
			lis.add("A90");
			lis.add("A92");
			lis.add("U06");
			
			
			List<PacienteDTO> dto = new PacienteDao(new Paciente()).consultaDoencasCidadeMapa(lis, "DUQUE DE CAXIAS");
			
			
			System.out.println(dto);
			System.out.println("Quantidade: " + dto.size());
		} catch (Exception e) {
		e.printStackTrace();
		}
		
		
	}
}
