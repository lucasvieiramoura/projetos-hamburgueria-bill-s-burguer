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
import hamburgueria.dao.FuncionarioDao;
import hbb.bill.hamburgueria.model.Bairro;
import hbb.bill.hamburgueria.model.Funcionario;

@Controller
public class FuncionarioController {
	
	@Autowired
	private FuncionarioDao funcionarioDao;
	
	@Autowired
	private BairroDao bairroDao;
	
	@GetMapping("/novofuncionario")
	private String novofuncionario (Model model) {
		model.addAttribute("funcionario",new Funcionario());
		
		List<Bairro> bairros = bairroDao.getAll(Bairro.class);
		model.addAttribute("bairros", bairros);
		
		return "funcionarios/novofuncionario";
	}
	
	@PostMapping("/salvarfuncionario")
	public String salvarfuncionario (
			@RequestParam(value="id")long id,
			@ModelAttribute Funcionario funcionario,
			Model model ) {
		if (id != 0) {
			funcionario.setId(id);
			funcionario.setBairro(bairroDao.getById(Bairro.class, funcionario.getBairro().getId()));
			funcionarioDao.update(funcionario);
			model.addAttribute("message","Funcionário alterado com sucesso !");
		}else {
			funcionario.setBairro(bairroDao.getById(Bairro.class, funcionario.getBairro().getId()));
			funcionarioDao.create(funcionario);
			model.addAttribute("message", "Funcionário criado com sucesso !");
		}
		return "funcionarios/novofuncionario";
	}
	
	@GetMapping("/listarfuncionarios")
	public String listarfuncionarios(
			@RequestParam (value="nome", defaultValue="")String nome,
			Model model ) {
		List<Funcionario> funcionarios = funcionarioDao.getAll(Funcionario.class);
		if (!nome.equals(""))
			funcionarios = funcionarioDao.getByName(nome);
		model.addAttribute("funcionarios", funcionarios);
		model.addAttribute("funcionarioPesquisa", new Funcionario());
		return "funcionarios/listarfuncionarios";
	}
	
	
	@GetMapping("/editarfuncionario")
	public String editarcliente(
			@RequestParam(value="id") long id,
			Model model ) {
		Funcionario funcionario = funcionarioDao.getById(Funcionario.class, id);
		model.addAttribute("funcionario", funcionario);
		
		List<Bairro> bairros = bairroDao.getAll(Bairro.class);
		model.addAttribute("bairros", bairros);
		
		return "funcionarios/novofuncionario";
	}
	
	@GetMapping("/removerfuncionario")
	private String removerfuncionario(
			@RequestParam (value="id")long id,
			Model model ) {
		Funcionario funcionario = funcionarioDao.getById(Funcionario.class, id);
		if (id != 0)
			funcionarioDao.delete(funcionario);
		model.addAttribute("message", "Funcionario removido com sucesso !");
		List<Funcionario> funcionarios = funcionarioDao.getAll(Funcionario.class);
		model.addAttribute("funcionarios", funcionarios);
		model.addAttribute("funcionarioPesquisa", new Funcionario());
		
		return "funcionarios/listarfuncionarios";
	}
}
