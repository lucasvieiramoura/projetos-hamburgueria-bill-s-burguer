package hamburgueria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hamburgueria.dao.RegraDao;
import hbb.bill.hamburgueria.model.Regra;

@Controller
public class RegraController {

	@Autowired
	private RegraDao regraDao;
	
	@GetMapping("/novaregra")
	private String novaregra(Model model) {
		model.addAttribute("regra", new Regra());
		return "regras/novaregra";
	}
	
	@PostMapping ("/salvarregra")
	public String salvarregra(
			@RequestParam(value="id")long id,
			@ModelAttribute Regra regra,
			Model model) {
		if(id != 0) {
			regra.setId(id);
			regraDao.update(regra);
			model.addAttribute("message", "Regra alterda com sucesso!");
		}else {
			regra.setId(id);
			regraDao.create(regra);
			model.addAttribute("message", "Regra criada com sucesso!");
		}
		
		return "regras/novaregra";
	}
	
	@GetMapping ("/listarregras")
	public String listarregras (
			@RequestParam(value="id", defaultValue="")String nome,
			Model model ) {
		
		List<Regra> regras = regraDao.getAll(Regra.class);
		if(!nome.equals(""))
			regras = regraDao.getByName(nome);
		model.addAttribute("regras", regras);
		model.addAttribute("regraPesquisa", new Regra());
		
		return "regras/listarregras";
	}
	
	@GetMapping ("/editarregras")
	public String editarregras(
			@RequestParam(value="id")long id,
			Model model ) {
		Regra regras = regraDao.getById(Regra.class, id);
		model.addAttribute("regras", regras);
		
		return "regras/novaregra";
	}
	
	@GetMapping ("/removerregra")
	public String removerregra (
			@RequestParam(value="id") long id,
			Model model ) {
		Regra regra = regraDao.getById(Regra.class, id);
		if(regra != null)
			regraDao.delete(regra);
		model.addAttribute("message", "Regra removida com sucesso !");
		List<Regra> regras = regraDao.getAll(Regra.class);
		model.addAttribute("regras", regras);
		model.addAttribute("regraPesquisa", new Regra());
		
		return "regra/listarregras";
	}
	
}
