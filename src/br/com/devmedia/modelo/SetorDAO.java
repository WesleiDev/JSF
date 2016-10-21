package br.com.devmedia.modelo;

import java.io.Serializable;
import java.util.List;


import br.com.devmedia.beans.Setor;

import javax.persistence.EntityManager;
import javax.swing.text.Utilities;

import br.com.devmedia.jpa.EntityManagerUtil;
import br.com.devmedia.util.UtilErros;
import br.com.devmedia.util.UtilMensagens;

public class SetorDAO implements Serializable{

	private EntityManager em ;
	
	public SetorDAO(){
		em = EntityManagerUtil.getEntityManager();
	}

	/**
	 * Este método faz uma consulta de todos os registro no banco de dados
	 * @return
	 */
	public List<Setor> listarTodos(){
		
	return em.createQuery("from Setor order by nome").getResultList();//Estamos realizando uma consulta orientada objeto nas classes do meu projeto
	}
	
	/**
	 * Este método salva o objeto no banco de dados 
	 * @param obj
	 * @return
	 */
	public Boolean gravar(Setor obj){
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
	public Boolean excluir(Setor obj){
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
	
	public Setor localizar(Integer id){
		return em.find(Setor.class, id);
	}
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
}
