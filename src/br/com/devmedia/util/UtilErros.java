package br.com.devmedia.util;

public class UtilErros {
/**
 * Capturando mensagem de erro de n�vel mais baixo para mostrar para o usu�rio
 * @param e
 * @return
 */
	
	public static String getMensagemErro(Exception e){
		
		while(e.getCause()!= null){//Enquanto a exe��o tiver uma causa
			e = (Exception) e.getCause();
		}
		
		String retorno = e.getMessage();
		return retorno;
	}
}
