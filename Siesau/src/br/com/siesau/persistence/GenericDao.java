package br.com.siesau.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public abstract class GenericDao<Classe> {

	protected EntityManager manager;
	protected EntityTransaction transaction;
	protected Query query;
	
	protected Classe classe;
	
	public GenericDao(Classe classe) {
		this.classe = classe;
		manager = JPAUtil.getEntityManager();		
	}
	
	
	
	public void salva(Classe c) {
		try{
		transaction = manager.getTransaction();
		 transaction.begin();
		    manager.persist(manager.contains(c) ? c : manager.merge(c));
		 transaction.commit();
		}catch(Exception e){
			if(manager.isOpen()){
				manager.getTransaction().rollback();
		}
				e.printStackTrace();
		}finally {
			if(manager.isOpen()){
			manager.close();
			}
		}
		 
	}
	
	public void atualiza(Classe c) {
		try{
		transaction = manager.getTransaction();
		 transaction.begin();
		    manager.merge(c);
		 transaction.commit();
		}catch(Exception e){
			if(manager.isOpen()){
				manager.getTransaction().rollback();
		}
				e.printStackTrace();
		}finally {
			if(manager.isOpen()){
			manager.close();
			}
		}
		 
	}
	
	public void deleta(Classe c) {
		try{
		transaction = manager.getTransaction();
		 transaction.begin();
		    manager.remove(manager.contains(c) ? c : manager.merge(c));
		 transaction.commit();
		}catch(Exception e){
			if(manager.isOpen()){
				manager.getTransaction().rollback();
		}
				e.printStackTrace();
		}finally {
			if(manager.isOpen()){
			manager.close();
			}
		}
		 
	}
	
		
	@SuppressWarnings("unchecked")
	public List<Classe> lista(){			
		return manager.createQuery(("FROM " + classe.getClass().getName()))
				.getResultList();	
	}
	
	@SuppressWarnings("unchecked")
	public Classe findByCode(Integer cod){
		return (Classe) manager.find(classe.getClass(), cod);
		
	}

}

	
