package br.com.devmedia.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.sun.faces.util.Cache.Factory;
/**
 * Gereciador de entidades
 * @author Weslei
 *
 */
public class EntityManagerUtil {
	
	private static EntityManagerFactory factory = null;
	private static EntityManager em = null;
	
	public static EntityManager getEntityManager(){
		if (factory == null){
			factory = Persistence.createEntityManagerFactory("ProjetosJSF");
		}
		if (em == null){
			em = factory.createEntityManager();
		}

		return em;
	}

}
