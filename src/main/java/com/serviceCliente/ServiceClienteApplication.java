package com.serviceCliente;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.serviceCliente.enuns.EstadoPagamento;
import com.serviceCliente.enuns.TipoCliente;
import com.serviceCliente.model.Categoria;
import com.serviceCliente.model.Cidade;
import com.serviceCliente.model.Cliente;
import com.serviceCliente.model.Endereco;
import com.serviceCliente.model.Estado;
import com.serviceCliente.model.ItemPedido;
import com.serviceCliente.model.Pagamento;
import com.serviceCliente.model.PagamentoComBoleto;
import com.serviceCliente.model.PagamentoComCartao;
import com.serviceCliente.model.Pedido;
import com.serviceCliente.model.Produto;
import com.serviceCliente.repositories.CategoriaRepository;
import com.serviceCliente.repositories.CidadeRepository;
import com.serviceCliente.repositories.ClienteRepository;
import com.serviceCliente.repositories.EnderecoRepository;
import com.serviceCliente.repositories.EstadoRepository;
import com.serviceCliente.repositories.ItemPedidoRepository;
import com.serviceCliente.repositories.PagamentoRepository;
import com.serviceCliente.repositories.PedidoRepository;
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
	  @Autowired
	  private PedidoRepository pedidoRepository;
	  @Autowired
	  private PagamentoRepository pagamentoRepository;
	  @Autowired
	  private ItemPedidoRepository itemPedidoRepository;
	  
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pag1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pag1);
		
		Pagamento pag2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null );
		ped2.setPagamento(pag2);
		
		//cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.save(Arrays.asList(ped1,ped2));
		pagamentoRepository.save(Arrays.asList(pag1,pag2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.save(Arrays.asList(ip1,ip2,ip3));
	}
}
