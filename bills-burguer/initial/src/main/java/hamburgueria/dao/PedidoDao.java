package hamburgueria.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import hbb.bill.hamburgueria.model.Pedido;

@Repository
@Transactional

public class PedidoDao extends EntityDao<Pedido> {
	
	@SuppressWarnings("unchecked")
	public List<Pedido> getByCliente(String cliente) {
		return super.getSession()
				.createQuery("from Pedido p"
				+ "inner join fetch p.pedido_cliente as cliente"
				+ "inner join fetch p.pedido_funcionario as funcionario"
				+ "inner join fetch p.pedido_mesa as mesa"
				+ "inner join fectch p.pedido_pagamento as pagamento"
				+ "inner join fecthc p.data_de_emissao"
				+ "where cliente.nome like :nome")
				.setParameter("nome","%" +cliente+ "%").list();
				
	}
	
}
