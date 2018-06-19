package hamburgueria.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import hbb.bill.hamburgueria.model.FormaDePagamento;

@Repository
@Transactional

public class FormaDePagamentoDao extends EntityDao<FormaDePagamento>{
	
	@SuppressWarnings("unchecked")
	public List<FormaDePagamento>  getByName (String nome){
		return super.getSession()
			.createQuery("from formadepagamento fp"
				+"where fp.nome like :nome")
				.setParameter("nome","%" +nome+ "%").list();
	}

}
