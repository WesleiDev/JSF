package br.com.devmedia.modelo;

import java.io.Serializable;
import java.util.List;

import br.com.devmedia.beans.Grupo;
import javax.persistence.EntityManager;
import javax.swing.text.Utilities;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.devmedia.jpa.EntityManagerUtil;
import br.com.devmedia.util.UtilErros;
import br.com.devmedia.util.UtilMensagens;

public class GrupoDAO implements Serializable {

	private EntityManager em;
	private String ordem = "id";
	private String filtro = "";
	@NotNull(message="Maximo de objetos não pode ser nulo")
	private Integer maximosObjetos = 2;
	private Integer posicaoAtual = 0;
	private Integer totalObjetos = 0;

	public GrupoDAO() {
		em = EntityManagerUtil.getEntityManager();
	}

	/**
	 * Metodo para realizar a paginação
	 * @return
	 */
	public List<Grupo> listar() {
		String where = "";
		if (filtro.length() > 0) {
			if (ordem.equals("id")) {
				try {
					Integer.parseInt(filtro);
					where = "where " + ordem + " ='" + "' ";
				} catch (Exception e) {

				}
			} else {
				where = "where upper ( " + ordem + ") like '" + filtro.toUpperCase() + "%'";
			}
		}

		String jqpl = "from Grupo " + where + "order by " + ordem;
		totalObjetos = em.createQuery("Select id from Grupo " + where + " order by " + ordem).getResultList().size();
		return em.createQuery(jqpl).setFirstResult(posicaoAtual).setMaxResults(maximosObjetos).getResultList();
	}
	
	public void primeiro(){
		posicaoAtual = 0;
	}
	
	public void anterior(){
		posicaoAtual -= maximosObjetos;
		if(posicaoAtual < 0){
			posicaoAtual = 0;
		}
	}
	
	public void ultimo(){
		int resto = totalObjetos % maximosObjetos;
		if(resto > 0){
			posicaoAtual = totalObjetos - resto;
		}else{
			posicaoAtual = totalObjetos - maximosObjetos;
		}
	}
	
	public void proximo(){
		if((posicaoAtual + maximosObjetos) < totalObjetos){
			posicaoAtual += maximosObjetos;
		}
	}
	
	public String getMensagemNavegacao(){
		int ate = posicaoAtual + maximosObjetos;
		if(ate > totalObjetos){
			ate = totalObjetos;
		}
		return "Listando de " + (posicaoAtual + 1) + " até " + ate + " de " + totalObjetos + " registros";
	}

	/**
	 * Este método faz uma consulta de todos os registro no banco de dados
	 * 
	 * @return
	 */
	public List<Grupo> listarTodos() {

		return em.createQuery("from Grupo order by nome").getResultList();// Estamos
																			// realizando
																			// uma
																			// consulta
																			// orientada
																			// objeto
																			// nas
																			// classes
																			// do
																			// meu
																			// projeto
	}

	/**
	 * Este método salva o objeto no banco de dados
	 * 
	 * @param obj
	 * @return
	 */
	public Boolean gravar(Grupo obj) {
		try {
			em.getTransaction().begin();
			if (obj.getId() == null) {// verifica se o objeto informado já esta
										// cadastrado
				em.persist(obj);
			} else {
				em.merge(obj);
			}
			em.getTransaction().commit();
			UtilMensagens.mensagemInformacao("Objeto persistido com sucesso");
			return true;
		} catch (Exception e) {
			if (em.getTransaction().isActive() == false) {// Verifica se a
															// transaão não esta
															// ativa para ser
															// inicializada
				em.getTransaction().begin();
			}
			em.getTransaction().rollback();
			UtilMensagens.mensagemErro("Erro ao persistir objeto: " + UtilErros.getMensagemErro(e));
			return false;
		}
	}

	/**
	 * Este método exclui o objeto no banco de dados
	 * 
	 * @param obj
	 * @return
	 */
	public Boolean excluir(Grupo obj) {
		try {
			em.getTransaction().begin();
			em.remove(obj);
			em.getTransaction().commit();
			UtilMensagens.mensagemInformacao("Objeto removido com sucesso");
			return true;
		} catch (Exception e) {
			if (em.getTransaction().isActive() == false) {// Verifica se a
															// transaão não esta
															// ativa para ser
															// inicializada
				em.getTransaction().begin();
			}
			em.getTransaction().rollback();
			UtilMensagens.mensagemErro("Erro ao excluir objeto: " + UtilErros.getMensagemErro(e));
			return false;
		}
	}

	public Grupo localizar(Integer id) {
		return em.find(Grupo.class, id);
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public String getOrder() {
		return ordem;
	}

	public void setOrder(String ordem) {
		this.ordem = ordem;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public Integer getMaximosObjetos() {
		return maximosObjetos;
	}

	public void setMaximosObjetos(Integer maximosObjetos) {
		this.maximosObjetos = maximosObjetos;
	}

	public Integer getPosicaoAtual() {
		return posicaoAtual;
	}

	public void setPosicaoAtual(Integer posicaoAtual) {
		this.posicaoAtual = posicaoAtual;
	}

	public Integer getTotalObjetos() {
		return totalObjetos;
	}

	public void setTotalObjetos(Integer totalObjetos) {
		this.totalObjetos = totalObjetos;
	}
}
