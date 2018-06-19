package hamburgueria.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import hbb.bill.hamburgueria.model.Mesa;

@Repository
@Transactional

public class MesaDao extends EntityDao<Mesa>{
	
	@SuppressWarnings("unchecked")
	public List<Mesa> getByNumero(int numero){
		return super.getSession()
			.createQuery("from mesa m"
				+ "where m.numero like :numero")
			.setParameter("numero", "%" +numero+ "%").list();
	}
	
}
