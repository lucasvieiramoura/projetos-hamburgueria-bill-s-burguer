package hamburgueria.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import hbb.bill.hamburgueria.model.Produto;

@Repository
@Transactional
@Component
public class ProdutoDao extends EntityDao<Produto>{
	
	@SuppressWarnings("unchecked")
	public List<Produto> getByName(String nome) {
		return super.getSession()
				.createQuery("from Produto p"
				+" where p.nome like :nome")
				.setParameter("nome","%"+nome+"%").list();
	}
	
	public void recarrega(Produto produto) {
		getSession().refresh(produto);
	}
}
