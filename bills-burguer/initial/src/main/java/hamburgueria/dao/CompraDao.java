package hamburgueria.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import hbb.bill.hamburgueria.model.Compra;

@Repository
@Transactional
public class CompraDao extends EntityDao<Compra>{
	
	@SuppressWarnings("unchecked")
	public List<Compra> getByProduto(String produto) {
		return super.getSession()
				.createQuery("from Compra c"
				+ "left join fetch c.produto as produto"
				+ "where produto.nome like :nome")
				.setParameter("nome","%" +produto+ "%").list();
	}

}
