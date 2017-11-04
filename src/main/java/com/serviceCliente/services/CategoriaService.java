package com.serviceCliente.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.serviceCliente.exception.DataIntegrityException;
import com.serviceCliente.exception.ObjectNotFoundException;
import com.serviceCliente.model.Categoria;
import com.serviceCliente.repositories.CategoriaRepository;

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
	
	public List<Categoria> findAll(){
		List<Categoria> lista = categoriaRepository.findAll();
		if(lista == null || lista.isEmpty()){
			throw new ObjectNotFoundException("Lista não possui objetos!");
		}
		return lista;
	}

	public Page<Categoria> findAllPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return categoriaRepository.findAll(pageRequest);
		
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return categoriaRepository.save(obj);
	}

	public Categoria update(Categoria obj) {
		findId(obj.getId());
		return categoriaRepository.save(obj);
	}

	public void delete(Integer id) {
		try{
		 categoriaRepository.delete(id);
		}
		catch(DataIntegrityViolationException e){
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos!");
		}
	}

}
