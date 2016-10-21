package br.com.devmedia.beans;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Index;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;



import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Index;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@Entity
@Table(name="FUNCIONARIO")
public class Funcionario implements Serializable{
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@NotEmpty(message="O nome deve ser informado")
	@Length(max=50, message="O nome não pode ultrapassar {max} caracteres")
	@Column(name="NOME", length=50, nullable=false)
	@Index(name="IDX_FUNCIONARIO_NOME")
	private String nome;
	@NotEmpty(message="O CPF deve ser informado")
	@CPF(message="Informe um CPF válido")
	@Column(name="CPF", length=14, nullable=false, unique=true)
	private String cpf;
	@NotEmpty(message="O email deve ser informado")
	@Email(message="Informe um email válido")
	@Column(name="EMAIL", length=50, nullable=false)
	private String email;
	@Column(name="SALARIO", nullable=false, columnDefinition="numeric(10,2)")
	@NotNull(message="O salário deve ser informado")
	private Double salario;
	@NotNull(message="A data de nascimento deve ser informada")
	@Column(name="NASCIMENO", nullable=false)
	@Temporal(TemporalType.DATE)//Escolhedo o formato da data
	private Calendar nascimento;
	@NotNull(message="O Campo ativo deve ser informado")
	@Column(name="ATIVO", nullable=false)
	private Boolean ativo;
	@Column(name="FOTO")
	@Lob//Armazena valores binários no banco
	private byte[] foto;
	@NotEmpty(message="O nome do usuário deve ser informado")
	@Length(max=20, message="O nome do usuário não pode ultrapassar {max} caracters")
	@Column(name="USUARIO", length=20, nullable=false, unique=true)
	@Index(name="IDX_FUNCIONARIO_NOME_USUARIO")
	private String nomeUsuario;
	@NotEmpty(message="A senha deve ser informada")
	@Length(max=16, message="A senha não pode ultrapassar {max} caracters")
	@Column(name="SENHA", length=16, nullable=false)
	private String senha;
	@NotNull(message="O Grupo deve ser informado")
	@ManyToOne
	@JoinColumn(name="GRUPO", referencedColumnName="ID", nullable=false)
	private Grupo grupo;
	@NotNull(message="O setor deve ser informado")
	@ManyToOne
	@JoinColumn(name="SETOR", referencedColumnName="ID")// Aqui estamos nos referenciando a chave estrangeira
	private Setor setor;
	@Transient
	private StreamedContent image;//Gerando uma imagem dinâmica para usar com o Primeface- verificar metodo get 
	
	
	
	public Funcionario(){
		
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



	public String getCpf() {
		return cpf;
	}



	public void setCpf(String cpf) {
		this.cpf = cpf;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public Double getSalario() {
		return salario;
	}



	public void setSalario(Double salario) {
		this.salario = salario;
	}



	public Calendar getNascimento() {
		return nascimento;
	}



	public void setNascimento(Calendar nascimento) {
		this.nascimento = nascimento;
	}



	public Boolean getAtivo() {
		return ativo;
	}



	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}



	public byte[] getFoto() {
		return foto;
	}



	public void setFoto(byte[] foto) {
		this.foto = foto;
	}



	public String getNomeUsuario() {
		return nomeUsuario;
	}



	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}



	public String getSenha() {
		return senha;
	}



	public void setSenha(String senha) {
		this.senha = senha;
	}



	public Grupo getGrupo() {
		return grupo;
	}



	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}



	public Setor getSetor() {
		return setor;
	}



	public void setSetor(Setor setor) {
		this.setor = setor;
	}



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
		Funcionario other = (Funcionario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return nome;
	}



	public StreamedContent getImage() {
		if(this.getFoto()!=null){
			return new DefaultStreamedContent(
					new ByteArrayInputStream(this.getFoto()), "");
		}else return new DefaultStreamedContent();
	}



	public void setImage(StreamedContent image) {
		this.image = image;
	}

}
