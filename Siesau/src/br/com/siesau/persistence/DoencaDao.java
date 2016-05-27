package br.com.siesau.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.siesau.entity.Doenca;
import br.com.siesau.entity.DoencaDTO;

public class DoencaDao extends GenericDao<Doenca> {

	public DoencaDao(Doenca classe) {
		super(classe);
	}

	public Doenca pesquisaCID(Doenca doenca) {
		String consulta = "select d from Doenca d where d.cid = :cid";
		TypedQuery<Doenca> query = manager.createQuery(consulta, Doenca.class);
		query.setParameter("cid", doenca.getCid().toUpperCase());
		Doenca rDoenca = query.getSingleResult();
		return rDoenca;
	}

	public List<DoencaDTO> listaDoecaPorNome(){
		
		String consulta = "select d.nome, "
				+ "SUM ((select count(*) from doenca d where ad.cd_doenca = d.cd_doenca)) "
				+ "as quantidade, date_part('year',a.data_atend) ano "
				+ "from atendimento a join atend_doenca ad on a.cd_atend = ad.cd_atend "
				+ "join doenca d on ad.cd_doenca = d.cd_doenca "
				+ "join paciente p on a.cd_paciente = p.cd_paciente "
				+ "where d.cid in (d.cid) "
				+ "group by d.nome, ano order by nome,ano;";

		Query query = manager.createNativeQuery(consulta);
		@SuppressWarnings("unchecked")
		List<Object[]> resultados = query.getResultList();
		List<DoencaDTO> doencasDTO = new ArrayList<>();
		for (Object[] result : resultados) {
			DoencaDTO doencaDTO = new DoencaDTO();
			doencaDTO.setNomeDoenca(result[0].toString());
			doencaDTO.setQnt(Integer.parseInt(result[1].toString()));
			doencaDTO.setAno(Double.parseDouble(result[2].toString()));
			doencasDTO.add(doencaDTO);
		}
		return doencasDTO;
	}
	
	public List<DoencaDTO> listaDoecaPorIdade(){
		String consulta = "Select d.nome,"
				+ "SUM ((select count(*) from doenca d where ad.cd_doenca = d.cd_doenca)) as quantidade, "
				+"date_part('year',age(p.data_nasc)) idade, "
				+ "date_part('year',a.data_atend) ano from atendimento a join atend_doenca ad "
				+ "on a.cd_atend = ad.cd_atend join doenca d on ad.cd_doenca = d.cd_doenca "
				+ "join paciente p on a.cd_paciente = p.cd_paciente where "
				+ "d.cid in (d.cid)group by d.nome, idade, ano order by ano;";

		Query query = manager.createNativeQuery(consulta);
		@SuppressWarnings("unchecked")
		List<Object[]> resultados = query.getResultList();
		List<DoencaDTO> doencasDTO = new ArrayList<>();
		for (Object[] result : resultados) {
			DoencaDTO doencaDTO = new DoencaDTO();
			doencaDTO.setNomeDoenca(result[0].toString());
			doencaDTO.setQnt(Integer.parseInt(result[1].toString()));
			doencaDTO.setIdade(Double.parseDouble(result[2].toString()));
			doencaDTO.setAno(Double.parseDouble(result[3].toString()));
			doencasDTO.add(doencaDTO);
		}
		return doencasDTO;
	}

	public List<DoencaDTO> listaDoecaPorCID(String cid){
		
		String consulta = "Select d.nome,"
				+ "SUM ((select count(*) from doenca d where ad.cd_doenca = d.cd_doenca)) as quantidade, "
				+"date_part('year',age(p.data_nasc)) idade,"
				+ "date_part('year',a.data_atend) ano "
				+ "from atendimento a join atend_doenca ad "
				+ "on a.cd_atend = ad.cd_atend join doenca d on ad.cd_doenca = d.cd_doenca "
				+ "join paciente p on a.cd_paciente = p.cd_paciente where "
				+ "d.cid = '"+cid+"' group by d.nome, idade, ano order by idade;";

		Query query = manager.createNativeQuery(consulta);
		@SuppressWarnings("unchecked")
		List<Object[]> resultados = query.getResultList();
		List<DoencaDTO> doencasDTO = new ArrayList<>();
		for (Object[] result : resultados) {
			DoencaDTO doencaDTO = new DoencaDTO();
			doencaDTO.setNomeDoenca(result[0].toString());
			doencaDTO.setQnt(Integer.parseInt(result[1].toString()));
			doencaDTO.setIdade(Double.parseDouble(result[2].toString()));
			doencaDTO.setAno(Double.parseDouble(result[3].toString()));
			doencasDTO.add(doencaDTO);
		}
		return doencasDTO;
	}
	
public List<DoencaDTO> listaDoecaPorCIDporAno(String cid){
		
		String consulta = "Select d.nome,"
				+ "SUM ((select count(*) from doenca d where ad.cd_doenca = d.cd_doenca)) as quantidade, "
				+"date_part('year',age(p.data_nasc)) idade,"
				+ "date_part('year',a.data_atend) ano "
				+ "from atendimento a join atend_doenca ad "
				+ "on a.cd_atend = ad.cd_atend join doenca d on ad.cd_doenca = d.cd_doenca "
				+ "join paciente p on a.cd_paciente = p.cd_paciente where "
				+ "d.cid = '"+cid+"' group by d.nome, idade, ano order by ano;";

		Query query = manager.createNativeQuery(consulta);
		@SuppressWarnings("unchecked")
		List<Object[]> resultados = query.getResultList();
		List<DoencaDTO> doencasDTO = new ArrayList<>();
		for (Object[] result : resultados) {
			DoencaDTO doencaDTO = new DoencaDTO();
			doencaDTO.setNomeDoenca(result[0].toString());
			doencaDTO.setQnt(Integer.parseInt(result[1].toString()));
			doencaDTO.setIdade(Double.parseDouble(result[2].toString()));
			doencaDTO.setAno(Double.parseDouble(result[3].toString()));
			doencasDTO.add(doencaDTO);
		}
		return doencasDTO;
	}
	

	public List<Doenca> listaDoecasOcorridas(){
	
	String consulta = "select d.cd_doenca, d.cid,d.nome, count(*) "
			+ "from doenca d inner join atend_doenca ad "
			+ "on (d.cd_doenca = ad.cd_doenca) "
			+ "group by d.cd_doenca, d.cid,d.nome;";

	Query query = manager.createNativeQuery(consulta);
	@SuppressWarnings("unchecked")
	List<Object[]> resultados = query.getResultList();
	List<Doenca> doencas = new ArrayList<>();
	for (Object[] result : resultados) {
		Doenca doenca = new Doenca();
		doenca.setCdDoenca(Integer.parseInt(result[0].toString()));
		doenca.setCid(result[1].toString());
		doenca.setNome(result[2].toString());
		doencas.add(doenca);
	}
	return doencas;
}

	public static void main(String[] args) {
		DoencaDao dao = new DoencaDao(new Doenca());
		List<DoencaDTO> doencaDTOs = new ArrayList<>();

		try {
			doencaDTOs = dao.listaDoecaPorCID("a90");
			System.out.println(doencaDTOs.toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
