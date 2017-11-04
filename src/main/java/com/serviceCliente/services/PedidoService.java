package com.serviceCliente.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serviceCliente.exception.ObjectNotFoundException;
import com.serviceCliente.model.Pedido;
import com.serviceCliente.repositories.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido findId(Integer id){		
		Pedido pedido = pedidoRepository.findOne(id);		
		if(pedido == null){
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " , Tipo: " + Pedido.class.getName());
		}
		return pedido;
	}
	
	public List<Pedido> pedidos(){
		List<Pedido> lista = pedidoRepository.findAll();
		if(lista == null || lista.isEmpty()){
			throw new ObjectNotFoundException("Objeto não encontrado!");
		}
		return lista;
	}

}
