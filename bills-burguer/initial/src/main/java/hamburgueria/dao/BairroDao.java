package hamburgueria.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import hbb.bill.hamburgueria.model.Bairro;

@Repository
@Transactional

public class BairroDao extends EntityDao<Bairro> {

		@SuppressWarnings("unchecked")
		public List<Bairro> getByName(String nome){
			return super.getSession()
					.createQuery("from bairro b"
					+ "where b.nome like :nome")
					.setParameter("nome", "%" +nome+ "%").list();
		}
}
