package hamburgueria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hamburgueria.dao.ClienteDao;
import hamburgueria.dao.FormaDePagamentoDao;
import hamburgueria.dao.FuncionarioDao;
import hamburgueria.dao.MesaDao;
import hamburgueria.dao.PedidoDao;
import hbb.bill.hamburgueria.model.Cliente;
import hbb.bill.hamburgueria.model.FormaDePagamento;
import hbb.bill.hamburgueria.model.Funcionario;
import hbb.bill.hamburgueria.model.Mesa;
import hbb.bill.hamburgueria.model.Pedido;

@Controller
public class PedidoController {
	
	@Autowired
	private PedidoDao pedidoDao;
	
	@Autowired
	private ClienteDao clienteDao;

	@Autowired
	private FuncionarioDao funcionarioDao;
	
	@Autowired
	private MesaDao mesaDao;
	
	@Autowired
	private FormaDePagamentoDao formaDePagamentoDao;
	
	@GetMapping("/novopedido")
	private String novopedido(Model model) {
		model.addAttribute("pedido", new Pedido());
		
		List<Cliente> clientes = clienteDao.getAll(Cliente.class);
		model.addAttribute("clientes", clientes);
		
		List<Funcionario> funcionarios = funcionarioDao.getAll(Funcionario.class);
		model.addAttribute("funcionarios", funcionarios);
		
		List<Mesa> mesas = mesaDao.getAll(Mesa.class);
		model.addAttribute("mesas", mesas);
		
		List<FormaDePagamento> formaDePagamentos = formaDePagamentoDao.getAll(FormaDePagamento.class);
		model.addAttribute("formaDePagamentos", formaDePagamentos);
		
		return "pedidos/novopedido";
	}
	
	@PostMapping ("/salvarpedido")
	public String salvarpedido (
			@RequestParam (value="id")long id,
			@ModelAttribute Pedido pedido,
			Model model) {
		if(id != 0) {
			pedido.setId(id);
			pedidoDao.update(pedido);
			model.addAttribute("message","Pedido alterada com sucesso!");
		} else {
			pedido.setCliente(clienteDao.getById(Cliente.class, pedido.getCliente().getId()));
			pedido.setFuncionario(funcionarioDao.getById(Funcionario.class, pedido.getFuncionario().getId()));
			pedido.setFormaDePagamento(formaDePagamentoDao.getById(FormaDePagamento.class, pedido.getFormaDePagamento().getId()));
			pedido.setMesa(mesaDao.getById(Mesa.class, pedido.getMesa().getId()));
			pedidoDao.create(pedido);
			model.addAttribute("message", "Pedido criado com sucesso!");
		}
		
		return "pedidos/novopedido";
	}
	
	@GetMapping("/listarpedidos")
	public String listarpedido (
			@RequestParam(value="cliente")String cliente,
			Model model) {
		List<Pedido> pedidos = pedidoDao.getAll(Pedido.class);
		if (!cliente.equals(""))
			pedidos = pedidoDao.getByCliente(cliente);
		model.addAttribute("pedidos", pedidos);
		model.addAttribute("pesquisaPedido", new Pedido());
		
		return "pedidos/listarpedidos";
	}
	
	@GetMapping("/editarpedido")
	public String editarpedido (
			@RequestParam(value="id")long id,
			Model model ) {
		Pedido pedido = pedidoDao.getById(Pedido.class, id);
		model.addAttribute("pedido", pedido);
		
		List<Cliente> clientes = clienteDao.getAll(Cliente.class);
		model.addAttribute("clientes", clientes);
		
		List<Funcionario> funcionarios = funcionarioDao.getAll(Funcionario.class);
		model.addAttribute("funcionarios", funcionarios);
		
		List<Mesa> mesas = mesaDao.getAll(Mesa.class);
		model.addAttribute("mesas", mesas);
		
		List<FormaDePagamento> formaDePagamentos = formaDePagamentoDao.getAll(FormaDePagamento.class);
		model.addAttribute("formaDePagamentos", formaDePagamentos);
		
		return "pedidos/novopedido";
	}
	
	@GetMapping("/removerpedido")
	public String removerpedido(
			@RequestParam(value="id")long id,
			Model model ) {
		Pedido pedido = pedidoDao.getById(Pedido.class, id);
		if (pedido != null )
			pedidoDao.delete(pedido);
		model.addAttribute("message", "Pedido removido com sucesso !");
		List<Pedido> pedidos = pedidoDao.getAll(Pedido.class);
		model.addAttribute("pedidos", pedidos);
		model.addAttribute("pedidoPesquisa", new Pedido());
		
		return "pedidos/listarpedidos";
	}
}


