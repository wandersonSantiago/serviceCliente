package com.serviceCliente.rest;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.serviceCliente.dto.ClienteDTO;
import com.serviceCliente.dto.ClienteNewDTO;
import com.serviceCliente.model.Categoria;
import com.serviceCliente.model.Cliente;
import com.serviceCliente.services.ClienteService;

@RestController
@RequestMapping("/rest/clientes")
public class ClienteRest {
	
	@Autowired
	private ClienteService clienteService;
	
	 @GetMapping
	  public ResponseEntity<List<ClienteDTO>> findAll() {	
		 List<Cliente> clientes =  clienteService.findAll();
		 List<ClienteDTO> clientesDTO = clientes.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
	    return  ResponseEntity.ok().body(clientesDTO);
	  }
	 
	 @GetMapping(value="/page")
	  public ResponseEntity<Page<ClienteDTO>> findAllPage(
			 @RequestParam(value="page", defaultValue="0") Integer page, 
			 @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			 @RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			 @RequestParam(value="direction", defaultValue="ASC") String direction) {	
		 Page<Cliente> clientes =  clienteService.findAllPage(page, linesPerPage, orderBy, direction);
		 Page<ClienteDTO> clientesDTO = clientes.map(obj -> new ClienteDTO(obj));
	    return  ResponseEntity.ok().body(clientesDTO);
	  }
	 
	 
	 @GetMapping(value="/{id}")
	 public ResponseEntity<Cliente> findId(@PathVariable Integer id){
		 return ResponseEntity.ok().body(clienteService.findId(id));
	 }

/*	 @PostMapping
	 public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDTO){
		 Cliente obj = clienteService.fromDTO(objDTO);
		 obj = clienteService.insert(obj);
		 URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		 return ResponseEntity.created(uri).build();
	 }*/
	 
	 @PostMapping
	 public ResponseEntity<Void> insert(@Valid @RequestBody ClienteDTO objDTO){
		 Cliente obj = clienteService.fromDTO(objDTO);
		 obj = clienteService.insert(obj);
		 URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		 return ResponseEntity.created(uri).build();
	 }
	 
	 @PutMapping(value="/{id}")
	 public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDTO, @PathVariable Integer id){
		 Cliente obj =  clienteService.fromDTO(objDTO);
		 obj.setId(id);
		 obj = clienteService.update(obj);
		 return ResponseEntity.noContent().build();
	 }
	 
	 @DeleteMapping(value="/{id}")
	 public ResponseEntity<Cliente> delete(@PathVariable Integer id){
		 clienteService.delete(id);
		 return ResponseEntity.noContent().build();
	 }

}
