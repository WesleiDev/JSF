import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.devmedia.beans.Funcionario;
import br.com.devmedia.beans.Grupo;
import br.com.devmedia.beans.Setor;
import br.com.devmedia.jpa.EntityManagerUtil;

public class TesteInserirFuncionario {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		EntityManager em = EntityManagerUtil.getEntityManager();
		Grupo grupo = em.find(Grupo.class, 1);//Consultando o Grupo no banco de dados 
		Setor setor = em.find(Setor.class, 1);//Consultando o Setor no banco de dados
		Funcionario f = new Funcionario();
		f.setAtivo(true);
		f.setCpf("089.729.349-50");
		f.setEmail("weslei.fs@hotmail.com");
		f.setGrupo(grupo);
		f.setNascimento(Calendar.getInstance());
		f.setNome("Weslei Ferreira da Silva");
		f.setNomeUsuario("Weslei");
		f.setSalario(5000.00);
		f.setSenha("12345");
		f.setSetor(setor);
		em.getTransaction().begin();
		em.persist(f);
		em.getTransaction().commit();
		
	}

}
