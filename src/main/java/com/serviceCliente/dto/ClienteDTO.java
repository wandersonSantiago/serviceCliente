package com.serviceCliente.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.serviceCliente.model.Cliente;

public class ClienteDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	@NotEmpty(message="O nome não pode ser nulo!")
	@Length(min=5, max=80, message="O nome tem que ter no minimo 5 e no maximo 80 caracteres")
	private String nome;
	@NotEmpty(message="O email é obrigatório")
	@Email(message="Email inválido!")
	private String email;
	
	public ClienteDTO(){
		
	}
	public ClienteDTO(Cliente obj){
		id = obj.getId();
		nome = obj.getNome();
		email = obj.getEmail();
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
