package com.serviceCliente.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.serviceCliente.dto.ClienteDTO;
import com.serviceCliente.dto.ClienteNewDTO;
import com.serviceCliente.enuns.TipoCliente;
import com.serviceCliente.exception.DataIntegrityException;
import com.serviceCliente.exception.ObjectNotFoundException;
import com.serviceCliente.model.Cidade;
import com.serviceCliente.model.Cliente;
import com.serviceCliente.model.Endereco;
import com.serviceCliente.repositories.CidadeRepository;
import com.serviceCliente.repositories.ClienteRepository;
import com.serviceCliente.repositories.EnderecoRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;

	public Cliente findId(Integer id) {
		Cliente cliente = clienteRepository.findOne(id);
		if (cliente == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + " , Tipo: " + Cliente.class.getName());
		}
		return cliente;
	}

	public List<Cliente> findAll() {
		List<Cliente> lista = clienteRepository.findAll();
		if (lista == null || lista.isEmpty()) {
			throw new ObjectNotFoundException("Lista não possui objetos!");
		}
		return lista;
	}

	public Page<Cliente> findAllPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);

	}

	public Cliente insert(Cliente obj) {
		obj.setId(null);
		enderecoRepository.save(obj.getEnderecos());
		return clienteRepository.save(obj);
	}

	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
	}

	public Cliente update(Cliente obj) {
		Cliente newCliente = findId(obj.getId());
		updateData(newCliente, obj);
		return clienteRepository.save(newCliente);
	}

	private void updateData(Cliente newCliente, Cliente obj) {

		newCliente.setNome(obj.getNome());
		newCliente.setEmail(obj.getEmail());
	}

	public void delete(Integer id) {
		try {
			clienteRepository.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir por que há entidades relacionadas!");
		}
	}

	public Cliente fromDTO(ClienteNewDTO objDTO) {
		Cliente cli = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCnpjOuCpf(),
				TipoCliente.toEnum(objDTO.getTipo()));
		Cidade cid = cidadeRepository.findOne(objDTO.getCidadeId());
		Endereco end = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(),
				objDTO.getBairro(), objDTO.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDTO.getTelefone1());
		if (objDTO.getTelefone2() != null) {
			cli.getTelefones().add(objDTO.getTelefone2());
		}
		if (objDTO.getTelefone3() != null) {
			cli.getTelefones().add(objDTO.getTelefone3());
		}
		return cli;
	}
}
