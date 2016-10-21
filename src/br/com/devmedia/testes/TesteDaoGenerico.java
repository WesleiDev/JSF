package br.com.devmedia.testes;

import java.util.List;

import br.com.devmedia.beans.Setor;
import br.com.devmedia.modelo.DAOSetor;

public class TesteDaoGenerico {

	public static void main(String[] args) {
		
	
		DAOSetor<Setor> dao = new DAOSetor<Setor>();
		List<Setor> lista = dao.listar();
		for(Setor o : lista){
			System.out.println("Código: " + o.getId() + " Nome: " + o.getNome());
		}

	}

}
