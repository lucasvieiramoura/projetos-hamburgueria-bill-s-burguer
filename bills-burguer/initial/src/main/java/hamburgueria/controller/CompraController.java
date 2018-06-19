package hamburgueria.controller;
 
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import hamburgueria.dao.ClienteDao;
import hamburgueria.dao.CompraDao;
import hamburgueria.dao.FormaDePagamentoDao;
import hamburgueria.dao.FuncionarioDao;
import hamburgueria.dao.ItemDao;
import hamburgueria.dao.MesaDao;
import hamburgueria.dao.PedidoDao;
import hamburgueria.dao.ProdutoDao;
import hbb.bill.hamburgueria.model.Cliente;
import hbb.bill.hamburgueria.model.Compra;
import hbb.bill.hamburgueria.model.FormaDePagamento;
import hbb.bill.hamburgueria.model.Funcionario;
import hbb.bill.hamburgueria.model.Item;
import hbb.bill.hamburgueria.model.Mesa;
import hbb.bill.hamburgueria.model.Pedido;
import hbb.bill.hamburgueria.model.Produto;

@Controller
public class CompraController {
	@Autowired
	private ProdutoDao produtoDao;
	@Autowired
	private ItemDao itemDao;
	@Autowired
	private PedidoDao pedidoDao;
	@Autowired
	private CompraDao compraDao;
	@Autowired
	private ClienteDao clienteDao;
	@Autowired
	private FuncionarioDao funcionarioDao;
	@Autowired
	private MesaDao mesaDao;
	@Autowired
	private FormaDePagamentoDao formaDePagamentoDao;
	/*
	@GetMapping("/novacompra")//Lembrar de mudar no HTML e no MvcConfig de /compras para /novacompra
	public String novacompra(Model model) {
		model.addAttribute("compra",new Compra());
		model.addAttribute("pedido", new Pedido());
		
		// Necessário listar todas as informações em bloco devido ser OneToOne no banco, as informações são ComboBox
		List<Cliente> clientes = clienteDao.getAll(Cliente.class);
		model.addAttribute("clientes", clientes);
		List<Funcionario> funcionarios = funcionarioDao.getAll(Funcionario.class);
		model.addAttribute("funcionarios", funcionarios);
		List<Mesa> mesas = mesaDao.getAll(Mesa.class);
		model.addAttribute("mesas", mesas);
		List<FormaDePagamento> formaDePagamentos = formaDePagamentoDao.getAll(FormaDePagamento.class);
		model.addAttribute("formaDePagamentos", formaDePagamentos);
		
		// Lista de itens para compra, aqui estou buscando no banco todas os itens
		List<Item> itens = itemDao.getAll(Item.class);
		model.addAttribute("itens", itens);
		
		
		return "vendas/novacompra";
	}
	*/ // Código anterior
	 
	 
	 @GetMapping("/novoproduto")
	public String novoproduto(Model model) {
		model.addAttribute("produto", new Produto());
		return "produtos/novoproduto";
	}	
	
	 private List<Item> items = new ArrayList<Item>();
	 
	
	@RequestMapping(value="/compras", method=RequestMethod.GET)
	public String compras ( Model model){
		model.addAttribute("compra", new Compra());
		model.addAttribute("pedido", new Pedido());
		
		Compra compra = new Compra();
		compra.setItem(items);
		
		List<Item> items = itemDao.getAll(Item.class);
		model.addAttribute("items", items);
		
		List<Produto> produtos = produtoDao.getAll(Produto.class);
		model.addAttribute("produtos", produtos);
		
		List<Cliente> clientes = clienteDao.getAll(Cliente.class);
		model.addAttribute("clientes", clientes);
		
		List<Funcionario> funcionarios = funcionarioDao.getAll(Funcionario.class);
		model.addAttribute("funcionarios", funcionarios);
		
		List<Mesa> mesas = mesaDao.getAll(Mesa.class);
		model.addAttribute("mesas", mesas);
		
		List<FormaDePagamento> formaDePagamentos = formaDePagamentoDao.getAll(FormaDePagamento.class);
		model.addAttribute("formaDePagamentos", formaDePagamentos);
		
		return "vendas/compras";
	}
	
	@GetMapping ("/novacompra")
	private String novacompra ( Model model){
		model.addAttribute("compra", new Compra());
		model.addAttribute("pedido", new Pedido());
		
		List<Item> items = itemDao.getAll(Item.class);
		model.addAttribute("items", items);
		Compra compra = new Compra();
		compra.setItem(items);
		
		List<Cliente> clientes = clienteDao.getAll(Cliente.class);
		model.addAttribute("clientes", clientes);
		
		List<Funcionario> funcionarios = funcionarioDao.getAll(Funcionario.class);
		model.addAttribute("funcionarios", funcionarios);
		
		List<Mesa> mesas = mesaDao.getAll(Mesa.class);
		model.addAttribute("mesas", mesas);
		
		List<FormaDePagamento> formaDePagamentos = formaDePagamentoDao.getAll(FormaDePagamento.class);
		model.addAttribute("formaDePagamentos", formaDePagamentos);
		
		return "vendas/compras";
	}
	
	private List<Item> compras = new ArrayList<Item>();
	
	@PostMapping ("/salvarcompra")
	public String salvarcompra (
			@RequestParam (value="id") long id,
			@ModelAttribute Compra compra,
			Model model ) {
		if ( id != 0) {
			model.addAttribute("message", "Valores incorretos, a venda não foi concluída !");
						
		}else {
			compra.setId(id);
			compra.setPedido(pedidoDao.getById(Pedido.class, compra.getPedido().getId()));
			
			
			// Descobrir uma maneira para usar laço de repetição para ir adicionando os itens na compra
			
				Item item = new Item();
				item.setProduto(produtoDao.getById(Produto.class, item.getProduto().getId()));
				compras.add(item);
			
			compraDao.create(compra);
			model.addAttribute("message", "Venda Concluída ! ");
		}
		return "vendas/compras";
	}
	
	@GetMapping("/listarcompras")
	public String listarcompras (
			@RequestParam (value="produto")String produto,
			Model model ) {
		List<Compra> compras = compraDao.getAll(Compra.class);
		if (!produto.equals("") )
			compras = compraDao.getByProduto(produto);
		model.addAttribute("compras", compras);
		model.addAttribute("comprasPesquisa", new Compra());
		return "vendas/listarcompras";
	}

}
