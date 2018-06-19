package hamburgueria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hamburgueria.dao.BairroDao;
import hamburgueria.model.Bairro;

@Controller
public class BairroController {
	
	@Autowired
	private BairroDao bairroDao;
	
	@GetMapping ("/novobairro")
	public String novobairro ( Model model) {
		model.addAttribute("bairro", new Bairro());
		return "bairros/novobairro";
	}
	
	@PostMapping("/salvarbairro")
	public String salvarbairro(
			@RequestParam (value="id") long id, 
			@ModelAttribute Bairro bairro,
			Model model) {
		if(id != 0) {
			bairro.setId(id);
			bairroDao.update(bairro);
			model.addAttribute("message","Bairro alterado com sucesso !");
		} else {
			bairroDao.create(bairro);
			model.addAttribute("message","Bairro criado com sucesso !");
		}
		return "bairros/novobairro";
	}
	
	@GetMapping("/listarbairros")
	public String listarbairros(
			@RequestParam (value="nome", defaultValue="") String nome,
			Model model) {
		List<Bairro> bairros = bairroDao.getAll(Bairro.class);
		if (!nome.equals("")) {
			bairros = bairroDao.getByName(nome);
			model.addAttribute("bairro", bairros);
			model.addAttribute("pesquisaBairro", new Bairro());
			
		}else {
			model.addAttribute("message","Valores fornecidos incorretos!");
		}
		return "bairros/listarbairros";
		
	}
	
	@GetMapping("/removerbairro")
	public String removerbairro(
			@RequestParam (value="id")long id,
			Model model) {
			Bairro bairro = bairroDao.getById(Bairro.class, id);
			bairroDao.delete(bairro);
			List<Bairro> bairros = bairroDao.getAll(Bairro.class);
			model.addAttribute("bairro", bairros);
			model.addAttribute("pesquisabairro", new Bairro());
			
			return "bairros/listarbairros";
	}
			
			
}
