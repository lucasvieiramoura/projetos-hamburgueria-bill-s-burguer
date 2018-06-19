package hamburgueria.dao;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import hbb.bill.hamburgueria.model.RegraUsuario;

@Repository
@Transactional

public class RegraUsuarioDao extends EntityDao<RegraUsuario> {

}
