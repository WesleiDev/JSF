package br.com.devmedia.modelo;

import java.io.Serializable;
import java.util.List;

import br.com.devmedia.beans.Funcionario;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.text.Utilities;

import br.com.devmedia.jpa.EntityManagerUtil;
import br.com.devmedia.util.UtilErros;
import br.com.devmedia.util.UtilMensagens;

public class FuncionarioDAO implements Serializable{

	private EntityManager em ;
	
	public FuncionarioDAO(){
		em = EntityManagerUtil.getEntityManager();
	}

	/**
	 * Este método faz uma consulta de todos os registro no banco de dados
	 * @return
	 */
	public List<Funcionario> listarTodos(){
		return em.createQuery("from Funcionario order by nome").getResultList();
	}
	
	/**
	 * Este método salva o objeto no banco de dados 
	 * @param obj
	 * @return
	 */
	public Boolean gravar(Funcionario obj){
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
	public Boolean excluir(Funcionario obj){
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
	
	public Funcionario localizar(Integer id){
		return em.find(Funcionario.class, id);
	}
	
	/**
	 * Verifica se o usuário existe no banco de dados
	 * @param usuario
	 * @param senha
	 * @return
	 */
	public Boolean login(String usuario, String senha){
		Query query = em.createQuery("from Funcionario where upper(nomeUsuario) = :usurio"
				+ "and upper(senha) = :senha and ativo = true");
		
		query.setParameter("usuario", usuario.toUpperCase());
		query.setParameter("senha", senha.toUpperCase());
		if(!query.getResultList().isEmpty()){
			return true;
		}else{
			return false;
		}
		
	}
	
	/**
	 * Método que retorna o usuário consultado no banco de dados
	 * @param usuario
	 * @return
	 */
	public Funcionario localizaPorNome(String usuario){
		return (Funcionario) em.createQuery("from Funcionario where upper(nomeUsuario) = "
				+ ":suario").setParameter("usuario", usuario.toUpperCase()).getSingleResult();
	}
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
}
