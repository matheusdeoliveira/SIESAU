package br.com.siesau.control;

import jxl.*;
import jxl.read.biff.BiffException;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.siesau.entity.AtendDoenca;
import br.com.siesau.entity.Atendimento;
import br.com.siesau.entity.Doenca;
import br.com.siesau.entity.Paciente;
import br.com.siesau.entity.SituacaoAtend;
import br.com.siesau.persistence.AtendDoencaDao;
import br.com.siesau.persistence.AtendimentoDao;
import br.com.siesau.persistence.DoencaDao;
import br.com.siesau.persistence.PacienteDao;
import br.com.siesau.persistence.SituacaoAtendDao;

public class ImportaPlanilha {

	public static void xlsInputpaciente(File file) throws BiffException {

		try {

			Workbook workbook = Workbook.getWorkbook(file);

			Sheet sheet = workbook.getSheet(0);

			int linhas = sheet.getRows();
			Paciente paciente = null;
			Atendimento atendimento = null;
			AtendDoenca atendDoenca = null;
			Doenca doenca;

			for (int linha = 2; linha <= linhas; linha++) {
				try {
					paciente = new Paciente();

					paciente.setNome((sheet.getCell(0, linha)).getContents());
					paciente.setCpf((sheet.getCell(1, linha)).getContents());
					paciente.setRg((sheet.getCell(2, linha)).getContents());
					paciente.setCartaoSus((sheet.getCell(3, linha)).getContents());
					paciente.setNomePai((sheet.getCell(4, linha)).getContents());
					paciente.setNomeMae((sheet.getCell(5, linha)).getContents());
					paciente.setEndereco((sheet.getCell(6, linha)).getContents());
					paciente.setNumero((sheet.getCell(7, linha)).getContents());
					paciente.setComplemento((sheet.getCell(8, linha)).getContents());
					paciente.setBairro((sheet.getCell(9, linha)).getContents());
					paciente.setCidade((sheet.getCell(10, linha)).getContents());
					paciente.setUf((sheet.getCell(11, linha)).getContents());
					paciente.setCep(Integer.parseInt((sheet.getCell(12, linha)).getContents()));
					paciente.setTelRes((sheet.getCell(13, linha)).getContents());
					paciente.setTelCel((sheet.getCell(14, linha)).getContents());
					paciente.setEmail((sheet.getCell(15, linha)).getContents());
					paciente.setEtnia((sheet.getCell(16, linha)).getContents());
					paciente.setEstCivil((sheet.getCell(17, linha)).getContents());
					paciente.setProfissao((sheet.getCell(18, linha)).getContents());
					paciente.setNaturalidade((sheet.getCell(19, linha)).getContents());

					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
					if ((sheet.getCell(20, linha)).getContents() != "") {
						java.sql.Date data = new java.sql.Date(
								format.parse((sheet.getCell(20, linha)).getContents()).getTime());
						paciente.setDataNasc(data);
					}

					paciente.setObs((sheet.getCell(21, linha)).getContents());

					if ((sheet.getCell(22, linha)).getContents().toString() != "") {
						java.sql.Date data2 = new java.sql.Date(
								format.parse((sheet.getCell(22, linha)).getContents()).getTime());
						paciente.setDataCad(data2);
					}

					paciente.setSexo((sheet.getCell(23, linha)).getContents());

					String alergia = (sheet.getCell(24, linha)).getContents();

					if (alergia.toUpperCase().equals("S") || alergia.toUpperCase().equals("SIM")) {
						paciente.setAlergia(true);
					} else {
						paciente.setAlergia(false);
					}
					paciente.setTipoAlergia((sheet.getCell(25, linha)).getContents());

					PacienteDao pacienteDao = new PacienteDao(paciente);
					pacienteDao.salva(paciente);
					paciente = new PacienteDao(new Paciente()).pesquisaCPF(paciente.getCpf());

					doenca = new Doenca();
					doenca.setCid((sheet.getCell(26, linha)).getContents().toUpperCase());
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

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
