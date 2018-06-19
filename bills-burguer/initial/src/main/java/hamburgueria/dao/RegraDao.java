package hamburgueria.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import hbb.bill.hamburgueria.model.Regra;

@Repository
@Transactional
public class RegraDao extends EntityDao<Regra>{
	
	@SuppressWarnings("unchecked")
	public List<Regra> getByName(String role){
		return super.getSession()
				.createQuery("from Regra r"
				+ " where r.role like :role")
				.setParameter("role", "%" +role+ "%").list();
	}
	
	public Regra getByRole(Class<Regra> class1, String role) {
		return (Regra) super.getSession()
				.createQuery("from Regra r "
				+ " where r.role like :role")
				.setParameter("role","%"+role+"%").uniqueResult();
	}
}
