
package br.com.siesau.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public final class JPAUtil {

	public static final String PERSISTENNCE_UNIT = "siesau";

	private static ThreadLocal<EntityManager> threadEntityManager = new ThreadLocal<>();

	private static EntityManagerFactory entityManagerFactory;

	private JPAUtil() {

	}

	public static EntityManager getEntityManager() {
		if (entityManagerFactory == null) {
			entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENNCE_UNIT);
		}

		EntityManager entityManager = threadEntityManager.get();

		if (entityManager == null || !entityManager.isOpen()) {

			entityManager = entityManagerFactory.createEntityManager();
			JPAUtil.threadEntityManager.set(entityManager);
		}

		return entityManager;
	}

	public static void closeEntityManager() {
		EntityManager em = threadEntityManager.get();

		if (em != null) {
			EntityTransaction transaction = em.getTransaction();

			if (transaction.isActive()) {
				transaction.commit();
			}

			em.close();

			threadEntityManager.set(null);
		}

	}

	public static void closeEntityManagerFactory() {
		closeEntityManager();
		entityManagerFactory.close();
	}
	
	 public static void main(String[] args) {
         
	        try{
	        EntityManager mf = JPAUtil.getEntityManager();
	        System.out.println(mf);
	 
	        }catch(Exception e){
	            e.printStackTrace();
	            
	            System.out.println("Error : " + e.getMessage());
	        }
	 }

}
