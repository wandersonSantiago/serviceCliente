package com.serviceCliente;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.serviceCliente.enuns.TipoCliente;
import com.serviceCliente.model.Categoria;
import com.serviceCliente.model.Cidade;
import com.serviceCliente.model.Cliente;
import com.serviceCliente.model.Endereco;
import com.serviceCliente.model.Estado;
import com.serviceCliente.model.Produto;
import com.serviceCliente.repositories.CategoriaRepository;
import com.serviceCliente.repositories.CidadeRepository;
import com.serviceCliente.repositories.ClienteRepository;
import com.serviceCliente.repositories.EnderecoRepository;
import com.serviceCliente.repositories.EstadoRepository;
import com.serviceCliente.repositories.ProdutoRepository;

@SpringBootApplication
public class ServiceClienteApplication implements CommandLineRunner{

	  @Autowired
	  private CategoriaRepository categoriaRepository;
	  @Autowired
	  private ProdutoRepository produtoRepository;
	  @Autowired
	  private CidadeRepository cidadeRepository;
	  @Autowired
	  private EstadoRepository estadoRepository;
	  @Autowired
	  private ClienteRepository clienteRepository;
	  @Autowired
	  private EnderecoRepository enderecoRepository;
	  
	public static void main(String[] args) {
		SpringApplication.run(ServiceClienteApplication.class, args);
	}

	
	@Override
	public void run(String... arg0) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		/*
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));*/
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		categoriaRepository.save(Arrays.asList(cat1,cat2));
		produtoRepository.save(Arrays.asList(p1,p2,p3));
		
		
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null,"Uberlandia", est1);
		Cidade c2 = new Cidade(null,"São Paulo", est2);
		Cidade c3 = new Cidade(null,"Campinas", est2);
		
		/*est1.setCidades(Arrays.asList(c1));
		est2.setCidades(Arrays.asList(c2,c3));*/
		
		estadoRepository.save(Arrays.asList(est1,est2));
		cidadeRepository.save(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "348899878", TipoCliente.PESSOA_FISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("546546868768", "56446546545456"));
		
		Endereco e1 = new Endereco(null, "rua teste", "4545", "teste complemento", "bairro teste", "45646545", cli1, c1); 
		Endereco e2 = new Endereco(null, "rua teste", "4545", "teste complemento", "bairro teste", "45646545", cli1, c2);
		
		//cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.save(cli1);
		enderecoRepository.save(Arrays.asList(e1,e2));
		
		
		
		
	}
}
