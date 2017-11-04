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

import com.serviceCliente.dto.CategoriaDTO;
import com.serviceCliente.model.Categoria;
import com.serviceCliente.services.CategoriaService;

@RestController
@RequestMapping("/rest/categorias")
public class CategoriaRest {
	
	@Autowired
	private CategoriaService categoriaService;
	
	 @GetMapping
	  public ResponseEntity<List<CategoriaDTO>> findAll() {	
		 List<Categoria> categorias =  categoriaService.findAll();
		 List<CategoriaDTO> categoriasDTO = categorias.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
	    return  ResponseEntity.ok().body(categoriasDTO);
	  }
	 
	 @GetMapping(value="/page")
	  public ResponseEntity<Page<CategoriaDTO>> findAllPage(
			 @RequestParam(value="page", defaultValue="0") Integer page, 
			 @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			 @RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			 @RequestParam(value="direction", defaultValue="ASC") String direction) {	
		 Page<Categoria> categorias =  categoriaService.findAllPage(page, linesPerPage, orderBy, direction);
		 Page<CategoriaDTO> categoriasDTO = categorias.map(obj -> new CategoriaDTO(obj));
	    return  ResponseEntity.ok().body(categoriasDTO);
	  }
	 
	 
	 @GetMapping(value="/{id}")
	 public ResponseEntity<Categoria> findId(@PathVariable Integer id){
		 return ResponseEntity.ok().body(categoriaService.findId(id));
	 }

	 @PostMapping
	 public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDTO){
		 Categoria obj = categoriaService.fromDTO(objDTO);
		 obj = categoriaService.insert(obj);
		 URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		 return ResponseEntity.created(uri).build();
	 }
	 
	 @PutMapping(value="/{id}")
	 public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO objDTO, @PathVariable Integer id){
		 Categoria obj =  categoriaService.fromDTO(objDTO);
		 obj.setId(id);
		 obj = categoriaService.update(obj);
		 return ResponseEntity.noContent().build();
	 }
	 
	 @DeleteMapping(value="/{id}")
	 public ResponseEntity<Categoria> delete(@PathVariable Integer id){
		 categoriaService.delete(id);
		 return ResponseEntity.noContent().build();
	 }
}
