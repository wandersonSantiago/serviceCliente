package com.serviceCliente.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.serviceCliente.model.Cliente;
import com.serviceCliente.services.ClienteService;

@RestController
@RequestMapping("/rest/clientes")
public class ClienteRest {
	
	@Autowired
	private ClienteService clienteService;
	
	 @GetMapping
	  public ResponseEntity<List<Cliente>> clientes() {		 				 
	    return  ResponseEntity.ok().body(clienteService.clientes());
	  }
	 
	 
	 @GetMapping(value="/{id}")
	 public ResponseEntity<Cliente> findId(@PathVariable Integer id){
		 return ResponseEntity.ok().body(clienteService.findId(id));
	 }

}
