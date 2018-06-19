package hamburgueria.dao;



import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import hbb.bill.hamburgueria.model.Usuario;

@Repository
@Transactional
public class UsuarioDao extends EntityDao<Usuario> {
	
	@SuppressWarnings ("unchecked")
	public List<Usuario> getByUsername(String nome) {
		return super.getSession()
				.createQuery("from Usuario u "
				+" left join fetch u.regra reg "
				+" where u.nome like :nome")
				.setParameter("nome","%"+nome+"%").list();
	}
	
	public Usuario getByNome(Class<Usuario> cls, String username) {
		return (Usuario) getSession().createQuery("from Usuario u"
				+ " where u.username like :username")
				.setParameter("username", "%"+username+"%").uniqueResult();
	}

}
