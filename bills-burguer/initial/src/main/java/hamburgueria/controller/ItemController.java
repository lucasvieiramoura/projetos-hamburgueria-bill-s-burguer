package hamburgueria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hamburgueria.dao.ItemDao;
import hamburgueria.dao.ProdutoDao;
import hbb.bill.hamburgueria.model.Item;
import hbb.bill.hamburgueria.model.Produto;

@Controller
public class ItemController {

	@Autowired
	private ItemDao itemDao;
	
	@Autowired
	private ProdutoDao produtoDao;
	
	@GetMapping("/novoitem")
	private String novoitem (Model model) {
		model.addAttribute("item", new Item());
		model.addAttribute("produto", new Produto());
		
		List<Item> itens = itemDao.getAll(Item.class);
		model.addAttribute("itens", itens);
		
		List<Produto> produtos = produtoDao.getAll(Produto.class);
		model.addAttribute("produtos", produtos);

		return "vendas/novoitem";
	}
	
	@PostMapping ("/salvaritem")
	public String salvaritem (
			@RequestParam (value="id") long id,
			@ModelAttribute Item item, Produto produto,
			Model model ) {
			if (id != 0) {
				item.setId(id);
				item.setProduto(produtoDao.getById(Produto.class, item.getProduto().getId()));
				itemDao.update(item);
				produtoDao.update(produto);
				model.addAttribute("message", "Item alterado com sucesso");
			} else {
				item.setProduto(produtoDao.getById(Produto.class, item.getProduto().getId()));
				itemDao.create(item);
				produtoDao.create(produto);
				model.addAttribute("message", "Item Criado com sucesso");
			}
		
			return "vendas/novoitem";
		}
	
	@GetMapping("/listaritens")
	private String listaritens (
			@RequestParam (value="id", defaultValue="")long id,
			Model model ) {
		List<Item> itens = itemDao.getAll(Item.class);
		model.addAttribute("items", itens);
		return "vendas/novoitem";
	}
}
