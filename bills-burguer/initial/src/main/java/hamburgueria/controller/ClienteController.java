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
import hamburgueria.dao.ClienteDao;
import hbb.bill.hamburgueria.model.Bairro;
import hbb.bill.hamburgueria.model.Cliente;

@Controller
public class ClienteController {
	
	@Autowired
	private ClienteDao clienteDao;
	
	@Autowired
	private BairroDao bairroDao;
	
	@GetMapping("/novocliente")
	private String novocliente(Model model) {
		model.addAttribute("cliente", new Cliente());
		
		List<Bairro> bairros = bairroDao.getAll(Bairro.class);
		model.addAttribute("bairros", bairros);
		
		return "clientes/novocliente";
	}
	
	@PostMapping ("/salvarcliente")
	public String salvarcliente (
			@RequestParam(value="id") long id,
			@ModelAttribute Cliente cliente,
			Model model ) {
		if (id != 0 ) {
			cliente.setId(id);
			cliente.setBairro(bairroDao.getById(Bairro.class, cliente.getBairro().getId()));
			clienteDao.update(cliente);
			model.addAttribute("message", " Cliente alterado com sucesso! ");
		} else {
			cliente.setBairro(bairroDao.getById(Bairro.class, cliente.getBairro().getId()));
			clienteDao.create(cliente);
			model.addAttribute("message", "Cliente cadastrado com sucesso! ");
		}
		return "clientes/novocliente";
	}
	
	@GetMapping ("/listarclientes")
	public String listarclientes (
			@RequestParam (value="nome", defaultValue="")String nome,
			Model model ) {
		List<Cliente> clientes = clienteDao.getAll(Cliente.class);
		if(!nome.equals(""))
			clientes = clienteDao.getByName(nome);
		model.addAttribute("clientes", clientes);
		model.addAttribute("clientePesquisa", new Cliente());
		
		return "clientes/listarclientes";
	}
	
	@GetMapping ("/editarcliente")
	public String editarcliente(
			@RequestParam(value="id") long id,
			Model model ) {
		Cliente cliente = clienteDao.getById(Cliente.class, id);
		model.addAttribute("cliente", cliente);
		
		List<Bairro> bairros = bairroDao.getAll(Bairro.class);
		model.addAttribute("bairros", bairros);
		
		return "clientes/novocliente";
	}
	
	@GetMapping ("/removercliente")
	public String removercliente (
			@RequestParam (value="id") long id,
			Model model ) {
		Cliente cliente = clienteDao.getById(Cliente.class, id);
		if (id != 0 ) 
			clienteDao.delete(cliente);
		model.addAttribute("message", "Cliente removido com sucesso! ");
		List<Cliente> clientes = clienteDao.getAll(Cliente.class);
		model.addAttribute("clientes", clientes);
		model.addAttribute("clientePesquisa", new Cliente());
		
		return "clientes/listarclientes";
	}
}
