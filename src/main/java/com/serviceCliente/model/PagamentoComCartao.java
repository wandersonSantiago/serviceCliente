package com.serviceCliente.model;

import javax.persistence.Entity;

import com.serviceCliente.enuns.EstadoPagamento;

@Entity
public class PagamentoComCartao extends Pagamento{
	private static final long serialVersionUID = 1L;
	
	private Integer numeroParcelas;
	
	public PagamentoComCartao(){
		
	}

	public PagamentoComCartao(Integer id, EstadoPagamento pagamento, Pedido pedido, Integer numeroParcelas) {
		super(id, pagamento, pedido);
		this.setNumeroParcelas(numeroParcelas);
	}

	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}
	
	
}
