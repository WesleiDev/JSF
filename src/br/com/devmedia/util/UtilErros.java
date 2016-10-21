package br.com.devmedia.util;

public class UtilErros {
/**
 * Capturando mensagem de erro de nível mais baixo para mostrar para o usuário
 * @param e
 * @return
 */
	
	public static String getMensagemErro(Exception e){
		
		while(e.getCause()!= null){//Enquanto a exeção tiver uma causa
			e = (Exception) e.getCause();
		}
		
		String retorno = e.getMessage();
		return retorno;
	}
}
