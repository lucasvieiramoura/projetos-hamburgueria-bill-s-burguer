package hamburgueria.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import hbb.bill.hamburgueria.model.Funcionario;

@Repository
@Transactional

public class FuncionarioDao extends EntityDao<Funcionario> {

	@SuppressWarnings("unchecked")
	public List<Funcionario> getByName(String nome) {
		return super.getSession()
				.createQuery("from Funcionario f"
				+" where f.nome like :nome")
				.setParameter("nome","%"+nome+"%").list();
	}

}
