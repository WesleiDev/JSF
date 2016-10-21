package br.com.devmedia.controle;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.devmedia.beans.Funcionario;
import br.com.devmedia.conversores.ConverterGrupo;
import br.com.devmedia.conversores.ConverterSetor;
import br.com.devmedia.modelo.FuncionarioDAO;
import br.com.devmedia.modelo.GrupoDAO;
import br.com.devmedia.modelo.SetorDAO;
import br.com.devmedia.util.UtilErros;
import br.com.devmedia.util.UtilMensagens;

@ManagedBean(name="controleFuncionario")
@SessionScoped 
public class ControleFuncionario implements Serializable {
	
	private FuncionarioDAO dao;
	private Funcionario objeto;
	private GrupoDAO daoGrupo;
	private SetorDAO daoSetor;
	private ConverterGrupo converterGrupo;
	private ConverterSetor converterSetor;
	
	public ControleFuncionario(){
	 dao = new FuncionarioDAO();
	 daoGrupo = new GrupoDAO();
	 daoSetor = new SetorDAO();
	 converterGrupo = new ConverterGrupo();
	 converterSetor = new ConverterSetor();
	}
	
	public String listar(){
		return "/privado/funcionario/listar";
	}
	
	public String novo(){
		objeto = new Funcionario();
		return "form";
	}
	
	public String cancelar(){
		return "listar";
	}
	
	public String gravar(){
		if(dao.gravar(objeto)){
			return "listar";
		}else{
			return "form";
		}
	}
	
	public String alterar(Funcionario obj){
		objeto = obj;
		return "form";
	}
	
	public String excluir(Funcionario obj){
		dao.excluir(obj);
		return "listar";
	}
	/**
	 * Upando a Imagem para salvar no banco de dados
	 * @param event
	 */
	public void enviarFoto(FileUploadEvent event){
		try{
			byte[] foto = IOUtils.toByteArray(event.getFile().getInputstream());
			objeto.setFoto(foto);
			UtilMensagens.mensagemInformacao("Arquivo Enviado com sucesso! " + event.getFile().getFileName());
		}catch(Exception e){
			UtilMensagens.mensagemErro("Erro ao enviar arquivo: " + UtilErros.getMensagemErro(e));
		}
	}
	
	public StreamedContent getImagemDinamica(){
		String strid = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("id_imagem");
		if(strid != null){
			Integer id = Integer.parseInt(strid);
			Funcionario obj = dao.localizar(id);
			return obj.getImage();
		}
		return new DefaultStreamedContent();
	}
	public FuncionarioDAO getDao() {
		return dao;
	}
	public void setDao(FuncionarioDAO dao) {
		this.dao = dao;
	}
	public Funcionario getObjeto() {
		return objeto;
	}
	public void setObjeto(Funcionario objeto) {
		this.objeto = objeto;
	}
	public GrupoDAO getDaoGrupo() {
		return daoGrupo;
	}
	public void setDaoGrupo(GrupoDAO daoGrupo) {
		this.daoGrupo = daoGrupo;
	}
	public SetorDAO getDaoSetor() {
		return daoSetor;
	}
	public void setDaoSetor(SetorDAO doaSetor) {
		this.daoSetor = doaSetor;
	}
	public ConverterGrupo getConverterGrupo() {
		return converterGrupo;
	}
	public void setConverterGrupo(ConverterGrupo converterGrupo) {
		this.converterGrupo = converterGrupo;
	}
	public ConverterSetor getConverterSetor() {
		return converterSetor;
	}
	public void setConverterSetor(ConverterSetor converterSetor) {
		this.converterSetor = converterSetor;
	}

}
