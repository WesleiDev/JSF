package br.com.devmedia.modelo;

import java.io.Serializable;
import java.util.List;


import br.com.devmedia.beans.Projeto;

import javax.persistence.EntityManager;
import javax.swing.text.Utilities;

import br.com.devmedia.jpa.EntityManagerUtil;
import br.com.devmedia.util.UtilErros;
import br.com.devmedia.util.UtilMensagens;

public class ProjetoDAO implements Serializable{

	private EntityManager em ;
	
	public ProjetoDAO(){
		em = EntityManagerUtil.getEntityManager();
	}

	/**
	 * Este método faz uma consulta de todos os registro no banco de dados
	 * @return
	 */
	public List<Projeto> listarTodos(){
		
	return em.createQuery("from Projeto order by nome").getResultList();//Estamos realizando uma consulta orientada objeto nas classes do meu projeto
	}
	
	/**
	 * Este método salva o objeto no banco de dados 
	 * @param obj
	 * @return
	 */
	public Boolean gravar(Projeto obj){
		try{
			em.getTransaction().begin();
			if(obj.getId() == null){//verifica se o objeto informado já esta cadastrado
				em.persist(obj);
			}else{
				em.merge(obj);
			}
			em.getTransaction().commit();
			UtilMensagens.mensagemInformacao("Objeto persistido com sucesso");
			return true;
		}catch(Exception e){
			if(em.getTransaction().isActive() == false){//Verifica se a transaão não esta ativa para ser inicializada
				em.getTransaction().begin();
			}
			em.getTransaction().rollback();
			UtilMensagens.mensagemErro("Erro ao persistir objeto: " + 
											UtilErros.getMensagemErro(e));
			return false;
		}
	}
	
	/**
	 * Este método exclui o objeto no banco de dados
	 * @param obj
	 * @return
	 */
	public Boolean excluir(Projeto obj){
		try{
			em.getTransaction().begin();
			em.remove(obj);
			em.getTransaction().commit();
			UtilMensagens.mensagemInformacao("Objeto removido com sucesso");
			return true;
		}catch(Exception e){
			if(em.getTransaction().isActive() == false){//Verifica se a transaão não esta ativa para ser inicializada
				em.getTransaction().begin();
			}
			em.getTransaction().rollback();
			UtilMensagens.mensagemErro("Erro ao excluir objeto: " + 
											UtilErros.getMensagemErro(e));
			return false;
		}
	}
	
	public Projeto localizar(Integer id){
		return em.find(Projeto.class, id);
	}
	
	public void rooolbac(){
		if(em.getTransaction().isActive() == false){
			em.getTransaction().begin();
		
		}
		em.getTransaction().rollback();
	}
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
}
