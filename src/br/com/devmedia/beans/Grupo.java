package br.com.devmedia.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="GRUPO")

public class Grupo implements Serializable{
	@Id//Estamos definindo a propriedade ID como chave primária
	@Column(name="ID")
	@SequenceGenerator(name="SEQ_GRUPO", sequenceName="SEQ_GRUPO_ID",
	allocationSize = 1)
	@GeneratedValue(strategy= GenerationType.AUTO)//No MYSQL pode usar somente esta linha, a liha de cima pode ser excluida
	private Integer id;
	@NotEmpty(message="O nome não pode ser nule")
	@Length(max=50, message="O nome não pode ultrapassar {max} caracteres")
	@Column(name="NOME", length=50, nullable=false)//Especificando o nome da coluna, tamanho e informando que a coluna não pode conter valor null
	@Index(name="IDX_GRUPO_NOME")//Criando um index no banco de dados para a coluna nome
	private String nome;
	
	public Grupo(){
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
/**
 * Adicionando o equals e hashCode fazemos com que ao comparar este objeto Grupo,
 * não compare pelo endereço de memória e sim a ID (Source/Generate hasCode() and equals())
 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grupo other = (Grupo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/**
	 * Para criar o toString automáticamento source/Generate to String()
	 */
	@Override
	public String toString() {
		return nome;
	}

}
