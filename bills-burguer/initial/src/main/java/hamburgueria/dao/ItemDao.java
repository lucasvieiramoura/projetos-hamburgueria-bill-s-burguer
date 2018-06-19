package hamburgueria.dao;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import hbb.bill.hamburgueria.model.Item;

@Repository
@Transactional
public class ItemDao extends EntityDao<Item> {

}
