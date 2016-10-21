package br.com.devmedia.testes;

import javax.persistence.EntityManager;

import br.com.devmedia.beans.Grupo;
import br.com.devmedia.jpa.EntityManagerUtil;

public class TesteInserirGrupo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		EntityManager em = EntityManagerUtil.getEntityManager();
		
		Grupo g = new Grupo();
		g.setNome("Colegas");
		em.getTransaction().begin();//Iniciand transação
		em.persist(g);//persistindo o objeto
		em.getTransaction().commit();//Comitando informação no banco de dados
	}

}
