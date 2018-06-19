package hamburgueria.controller;


import java.util.List;

import javax.validation.Valid;

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
public class ProdutoController {
	
	@Autowired
	private ProdutoDao produtoDao;
	
	@Autowired
	private ItemDao itemDao;
	
	@GetMapping("/novoproduto")
	public String novoproduto(Model model) {
		model.addAttribute("produto", new Produto());
		return "produtos/novoproduto";
	}	
	
	@PostMapping("/salvarproduto")
	public String salvarproduto( @Valid
			@RequestParam(value="id") long id,
			@ModelAttribute Produto produto, Item item,
			Model model) {
		if (id != 0) {
			produto.setId(id);
			item.setId(id);
			produtoDao.update(produto);
			itemDao.update(item);
			model.addAttribute("message", "Produto Alterada com sucesso !");
		} else {
			produtoDao.create(produto);
			itemDao.create(item);
			model.addAttribute("message", "Produto criado com sucesso !");
		}
		return "produtos/novoproduto";
	}
	
	
	@GetMapping("/listarprodutos")
	public String listarprodutos(
			@RequestParam(value="nome", defaultValue="") String nome,
			Model model) {
		List<Produto> produtos = produtoDao.getAll(Produto.class);
		if(!nome.equals(""))
			produtos = produtoDao.getByName(nome);
		model.addAttribute("produtos", produtos);
		model.addAttribute("produtoPesquisa", new Produto());
		return "produtos/listarprodutos";
	}

	@GetMapping ("/editarproduto")
	public String editarproduto(
			@RequestParam(value="id") long id,
			Model model) {
		Produto produto = produtoDao.getById(Produto.class, id);
		model.addAttribute("produto", produto);
		return "produtos/novoproduto";
	}
	
	@GetMapping ("/removerproduto")
	public String removerproduto(
			@RequestParam(value="id") long id,
			Model model) {
		Produto produto = produtoDao.getById(Produto.class, id);
		if(produto != null)
			produtoDao.delete(produto);
		model.addAttribute("message","Produto removido com sucesso! ");
		List<Produto> produtos = produtoDao.getAll(Produto.class);
		model.addAttribute("produto", produtos);
		model.addAttribute("produtoPesquisa", new Produto());
		return "produtos/listarprodutos";
		
	}
			

}
