package com.servieCliente.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servieCliente.exception.ObjectNotFoundException;
import com.servieCliente.model.Categoria;
import com.servieCliente.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria findId(Integer id){		
		Categoria categoria = categoriaRepository.findOne(id);		
		if(categoria == null){
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " , Tipo: " + Categoria.class.getName());
		}
		return categoria;
	}
	
	public List<Categoria> categorias(){
		List<Categoria> lista = categoriaRepository.findAll();
		if(lista == null || lista.isEmpty()){
			throw new ObjectNotFoundException("Objeto não encontrado!");
		}
		return lista;
	}

}
