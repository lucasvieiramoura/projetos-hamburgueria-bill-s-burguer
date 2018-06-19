package hamburgueria.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import hbb.bill.hamburgueria.model.Cliente;

@Repository
@Transactional

public class ClienteDao extends EntityDao<Cliente> {

	@SuppressWarnings("unchecked")
	public List<Cliente> getByName(String nome) {
		return super.getSession()
				.createQuery("from Cliente c"
				+" where c.nome like :nome")
				.setParameter("nome","%"+nome+"%").list();
	}
}
