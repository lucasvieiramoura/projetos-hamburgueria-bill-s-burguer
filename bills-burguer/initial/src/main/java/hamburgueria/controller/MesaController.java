package hamburgueria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hamburgueria.dao.MesaDao;
import hbb.bill.hamburgueria.model.Mesa;

public class MesaController {
	
	@Autowired
	private MesaDao mesaDao;
	
	@GetMapping("/novamesa")
	public String novamesa(Model model) {
		model.addAttribute("mesa", new Mesa());
		return "novamesa";
	}
	
	@PostMapping("/salvarmesa")
	public String salvarmesa(
			@RequestParam(value="id") long id,
			@ModelAttribute Mesa mesa,
			Model model) {
		if(id != 0) {
			mesa.setId(id);
			mesaDao.update(mesa);
			model.addAttribute("message","Mesa alterada com sucesso");
		} else {
			mesaDao.create(mesa);
			model.addAttribute("message", "Mesa criada com sucesso !");
		}
		return "mesas/salvarmesa";
	}
	
	@GetMapping("/listarmesa")
	public String listarmesa(
			@RequestParam(value="numero", defaultValue="")int numero,
			Model model) {
	List<Mesa> mesas = mesaDao.getAll(Mesa.class);
	if (numero !=  0)
	mesas = mesaDao.getByNumero(numero);
	model.addAttribute("Mesa", mesas);
	model.addAttribute("mesa", new Mesa());
	return "mesas/listarmesa";
	}
	
	
	@GetMapping("/removermesa")
	public String removemesa(
			@RequestParam(value="id")long id,
			Model model) {
			Mesa mesa = mesaDao.getById(Mesa.class, id);
		if (mesa != null) {
			mesaDao.delete(mesa);
			model.addAttribute("message","Mesa removida com sucesso !");
			List<Mesa> mesas = mesaDao.getAll(Mesa.class);
			model.addAttribute("mesa", mesas);
			model.addAttribute("pesquisarMesa", new Mesa());
			
		} else {
			model.addAttribute("message","Valores inseridos incorretos !");
		}
		
		return "mesas/listarmesa";
	}
}
