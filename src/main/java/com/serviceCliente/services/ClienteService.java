package com.serviceCliente.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serviceCliente.exception.ObjectNotFoundException;
import com.serviceCliente.model.Cliente;
import com.serviceCliente.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente findId(Integer id){		
		Cliente cliente = clienteRepository.findOne(id);		
		if(cliente == null){
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " , Tipo: " + Cliente.class.getName());
		}
		return cliente;
	}
	
	public List<Cliente> clientes(){
		List<Cliente> lista = clienteRepository.findAll();
		if(lista == null || lista.isEmpty()){
			throw new ObjectNotFoundException("Objeto não encontrado!");
		}
		return lista;
	}

}
