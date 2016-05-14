package br.com.siesau.persistence;

import br.com.siesau.entity.Fornecedor;

public class FornecedoreDao extends GenericDao<Fornecedor> {
	
	public FornecedoreDao(Fornecedor forn) {
		super(forn);
	}
	
	public static void main(String[] args) {
		
		FornecedoreDao dao = new FornecedoreDao(new Fornecedor());
		
		
		Fornecedor encontrado = dao.findByCode(152);
		dao.deleta(encontrado);
		
		
		System.out.println("ok");
	}
	
}
