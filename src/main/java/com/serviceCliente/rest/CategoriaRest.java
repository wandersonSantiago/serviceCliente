package com.serviceCliente.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.serviceCliente.model.Categoria;
import com.serviceCliente.services.CategoriaService;

@RestController
@RequestMapping("/rest/categorias")
public class CategoriaRest {
	
	@Autowired
	private CategoriaService categoriaService;
	
	 @GetMapping
	  public ResponseEntity<List<Categoria>> categorias() {		 				 
	    return  ResponseEntity.ok().body(categoriaService.categorias());
	  }
	 
	 
	 @GetMapping(value="/{id}")
	 public ResponseEntity<Categoria> findId(@PathVariable Integer id){
		 return ResponseEntity.ok().body(categoriaService.findId(id));
	 }

}
