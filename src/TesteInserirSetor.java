import javax.persistence.EntityManager;

import br.com.devmedia.beans.Setor;
import br.com.devmedia.jpa.EntityManagerUtil;

public class TesteInserirSetor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	EntityManager em = EntityManagerUtil.getEntityManager();
	
	Setor s = new Setor();
	s.setNome("Administrativo");
	Setor s2 = new Setor();
	s2.setNome("Operacional");
	em.getTransaction().begin();
	em.persist(s);
	em.persist(s2);
	em.getTransaction().commit();
	System.out.println("Sucesso na inserção");
	
	}

}
