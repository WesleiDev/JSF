package br.com.devmedia.conversores;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.devmedia.beans.Funcionario;
import br.com.devmedia.jpa.EntityManagerUtil;

public class ConverterFuncionario implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
		//Converte da tela para o Objeto
		
		if (string == null || string.equals("Selecione um funcionario")){
			return null;
		}
		return EntityManagerUtil.getEntityManager().find(Funcionario.class, Integer.parseInt(string));
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		// Converte do Objeto para a tela

    if(obj == null){
    	return null;
    } 
    Funcionario objeto = (Funcionario) obj;
    return objeto.getId().toString();
}

}
