package br.com.siesau.control;

import jxl.*;
import jxl.read.biff.BiffException;
import java.io.File;
import java.text.SimpleDateFormat;

import br.com.siesau.entity.Paciente;
import br.com.siesau.persistence.PacienteDao;

public class ImportaPlanilha {

	public static void main(String[] args) throws BiffException {
		File file = new File("C:\\Users\\Diogo\\Downloads\\exemplo.xls");
		xlsInputpaciente(file);
	}

	public static void xlsInputpaciente(File file) throws BiffException {

		try {

			Workbook workbook = Workbook.getWorkbook(file);

			Sheet sheet = workbook.getSheet(0);

			int linhas = sheet.getRows();
			Paciente paciente;

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
					java.sql.Date data = new java.sql.Date(
							format.parse((sheet.getCell(20, linha)).getContents()).getTime());
					paciente.setDataNasc(data);

					paciente.setObs((sheet.getCell(21, linha)).getContents());

					data = new java.sql.Date(format.parse((sheet.getCell(22, linha)).getContents()).getTime());
					paciente.setDataCad(data);

					paciente.setSexo((sheet.getCell(23, linha)).getContents());

					PacienteDao pacienteDao = new PacienteDao(paciente);
					pacienteDao.salva(paciente);
					
				} catch (Exception e) {
					e.getMessage();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
