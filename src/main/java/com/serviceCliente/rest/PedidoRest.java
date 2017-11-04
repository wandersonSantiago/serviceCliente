package com.serviceCliente.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.serviceCliente.model.Pedido;
import com.serviceCliente.services.PedidoService;


@RestController
@RequestMapping("/rest/pedidos")
public class PedidoRest {
	
	@Autowired
	private PedidoService pedidoService;
	
	 @GetMapping
	  public ResponseEntity<List<Pedido>> pedidos() {		 				 
	    return  ResponseEntity.ok().body(pedidoService.pedidos());
	  }
	 
	 
	 @GetMapping(value="/{id}")
	 public ResponseEntity<Pedido> findId(@PathVariable Integer id){
		 return ResponseEntity.ok().body(pedidoService.findId(id));
	 }

}
