package br.com.devmedia.conversores;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.devmedia.beans.Grupo;
import br.com.devmedia.beans.Setor;
import br.com.devmedia.jpa.EntityManagerUtil;

public class ConverterSetor implements Converter, Serializable{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
		//Converte da tela para o Objeto
		
		if (string == null || string.equals("Selecione um setor")){
			return null;
		}
		return EntityManagerUtil.getEntityManager().find(Setor.class, Integer.parseInt(string));
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		// Converte do Objeto para a tela

    if(obj == null){
    	return null;
    } 
    Setor objeto = (Setor) obj;
    return objeto.getId().toString();
}

}
