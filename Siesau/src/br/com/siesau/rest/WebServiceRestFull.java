package br.com.siesau.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;

import com.google.gson.Gson;

import br.com.siesau.entity.AtendDoenca;
import br.com.siesau.entity.Atendimento;
import br.com.siesau.entity.Doenca;
import br.com.siesau.entity.Fornecedor;
import br.com.siesau.entity.Paciente;
import br.com.siesau.entity.SituacaoAtend;
import br.com.siesau.persistence.AtendDoencaDao;
import br.com.siesau.persistence.AtendimentoDao;
import br.com.siesau.persistence.DoencaDao;
import br.com.siesau.persistence.FornecedoreDao;
import br.com.siesau.persistence.PacienteDao;
import br.com.siesau.persistence.SituacaoAtendDao;

@ApplicationPath("/webService")
@Path("/siesau")
public class WebServiceRestFull extends Application {

	private List<Fornecedor> fornecedores;
	private List<Paciente> pacientes;

	public WebServiceRestFull() {
		fornecedores = new ArrayList<>();
		fornecedores = new FornecedoreDao(new Fornecedor()).lista();
		pacientes = new ArrayList<>();
		pacientes = new PacienteDao(new Paciente()).lista();
	}

	@GET
	@Path("/listarFornecedores")
	@Produces("application/json;charset=UTF-8")
	public String listarFornecedores() {
		Gson gson = new Gson();
		return gson.toJson(fornecedores);
	}

	@GET
	@Path("/cadastrarFornecedor/{razSocial}/{cep}/{email}/{telefone}")
	@Produces("text/plain")
	public String cadastrarForncedor(@PathParam("razSocial") String razSocial, @PathParam("cep") String cep,
			@PathParam("email") String email, @PathParam("telefone") String telefone) {

		try {

			Fornecedor fornecedor = new Fornecedor();

			fornecedor.setRazSocial(razSocial);
			fornecedor.setCep(new Integer(cep));
			fornecedor.setEmail(email);
			fornecedor.setTel(telefone);

			System.out.println("Fornecedor " + fornecedor);

			new FornecedoreDao(new Fornecedor()).salva(fornecedor);

			return "Dados Cadastros ...";

		} catch (Exception ex) {

			return "Error : " + ex.getMessage();

		}

	}

	@GET
	@Path("/cadatroPaciente/{cartaoSus}/{cpf}/{rg}/{nome}/{nomeMae}/{nomePai}/{sexo}/{cep}/{endereco}/"
			+ "{complemento}/{bairro}/{cidade}/{numero}/{telefone}/{codCID}")
	@Produces("text/plain")
	public String cadastroPacienteDoenca(@PathParam("cartaoSus") String cartaoSus, @PathParam("cpf") String cpf,
			@PathParam("rg") String rg, @PathParam("nome") String nome, @PathParam("nomeMae") String nomeMae,
			@PathParam("nomePai") String nomePai, @PathParam("sexo") String sexo,
			@PathParam("cep") String cep, @PathParam("endereco") String endereco,
			@PathParam("complemento") String complemento, @PathParam("bairro") String bairro,
			@PathParam("cidade") String cidade, @PathParam("numero") String numero,
			@PathParam("telefone") String telefone,
			@PathParam("codCID")String codCID) {

		try {
			Paciente paciente = new Paciente();
			Atendimento atendimento = new Atendimento();
			AtendDoenca atendDoenca = new AtendDoenca();
			Doenca doenca = new Doenca();
			paciente.setAlergia(false);
			paciente.setCartaoSus(cartaoSus);
			paciente.setCpf(cpf);
			paciente.setRg(rg);
			paciente.setNome(nome);
			paciente.setNomeMae(nomeMae);
			paciente.setNomePai(nomePai);
			paciente.setSexo(sexo);
			paciente.setCep(new Integer(cep));
			paciente.setEndereco(endereco);
			paciente.setComplemento(complemento);
			paciente.setBairro(bairro);
			paciente.setCidade(cidade);
			paciente.setNumero(numero);
			paciente.setTelCel(telefone);
			paciente.setDataCad(new Date());
			paciente.setFoto("perfil.jpeg");
			
			PacienteDao pacienteDao = new PacienteDao(paciente);
			pacienteDao.salva(paciente);
			paciente = new PacienteDao(new Paciente()).pesquisaCPF(paciente.getCpf());

			doenca = new Doenca();
			doenca.setCid(codCID);
			doenca = new DoencaDao(new Doenca()).pesquisaCID(doenca);

			atendimento = new Atendimento();
			atendimento.setPaciente(paciente);
			atendimento.setDataAtend(new Date());
			atendimento.setSituacaoAtend(new SituacaoAtendDao(new SituacaoAtend()).findByCode(3));

			new AtendimentoDao(new Atendimento()).salva(atendimento);

			atendimento = new AtendimentoDao(new Atendimento())
					.retornaAtendimentoPorData(atendimento.getDataAtend());

			atendDoenca = new AtendDoenca();
			atendDoenca.setAtendimento1(atendimento);
			atendDoenca.setAtendimento2(atendimento);
			atendDoenca.setDoenca1(doenca);
			atendDoenca.setDoenca2(doenca);

			AtendDoencaDao atendDoencaDao = new AtendDoencaDao(atendDoenca);
			atendDoencaDao.salva(atendDoenca);
			return "Dados Cadastros ...";

		} catch (Exception ex) {

			return "Error : " + ex.getMessage();

		}

	}
	
	@GET
	@Path("/listarPacientes")
	@Produces("application/json;charset=UTF-8")
	public String listaPacientes() {
		Gson gson = new Gson();
		List<Paciente> lista = new ArrayList<>();
		for (int i = 20; i < pacientes.size(); i++) {
			lista.add(pacientes.get(i));
		}		
		return gson.toJson(lista);
	}
}
