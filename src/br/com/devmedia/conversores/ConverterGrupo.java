package br.com.devmedia.conversores;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.devmedia.beans.Grupo;
import br.com.devmedia.jpa.EntityManagerUtil;

public class ConverterGrupo implements Converter, Serializable{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
		//Converte da tela para o Objeto
		
		if (string == null || string.equals("Selecione um grupo")){
			return null;
		}
		return EntityManagerUtil.getEntityManager().find(Grupo.class, Integer.parseInt(string));
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		// Converte do Objeto para a tela

    if(obj == null){
    	return null;
    } 
    Grupo objeto = (Grupo) obj;
    return objeto.getId().toString();
}

}
