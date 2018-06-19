package hamburgueria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hamburgueria.dao.FormaDePagamentoDao;
import hbb.bill.hamburgueria.model.FormaDePagamento;


@Controller
public class FormaDePagamentoController {
	
	@Autowired
	private FormaDePagamentoDao formaDePagamentoDao;
	
	@GetMapping("/novaformadepagamento")
	public String novaformadepagamento(Model model) {
		model.addAttribute("formadepagamento", new FormaDePagamento());
		return "novaformadepagamento";
	}
	
	@PostMapping("/listarformadepagamento")
	public String listarformadepagamento(
			@RequestParam(value="nome", defaultValue="")String nome,
			Model model) {
		List<FormaDePagamento>  formadepagamentos = formaDePagamentoDao.getAll(FormaDePagamento.class);
		if (!nome.equals("")) {
			formadepagamentos = formaDePagamentoDao.getByName(nome);
			model.addAttribute("formaDePagamento", formadepagamentos);
			model.addAttribute("pesquisaFormaDePagamento", new FormaDePagamento());
		} else {
			model.addAttribute("message","Valores fornecidos incorretos!");
		}
		return "listarformadepagamento";
	}
	
	@GetMapping("/editarformadepagamento")
	public String editarformadepagamento(
			@RequestParam (value="id")long id,
			Model model) {
		FormaDePagamento formaDePagamento = formaDePagamentoDao.getById(FormaDePagamento.class, id);
		model.addAttribute("formaDePagamento", formaDePagamento);
		return "novaformadepagamento";
	}
	
	@GetMapping("/removerformadepagamento")
	public String removeformadepagamento(
			@RequestParam(value="id")long id,
			Model model) {
		FormaDePagamento formaDePagamento = formaDePagamentoDao.getById(FormaDePagamento.class, id);
		if (formaDePagamento != null) {
			formaDePagamentoDao.delete(formaDePagamento);
			model.addAttribute("model", " Forma de Pagamento Removida!");
			List<FormaDePagamento> formaDePagamentos = formaDePagamentoDao.getAll(FormaDePagamento.class);
			model.addAttribute("formaDePagamento",formaDePagamentos);
			model.addAttribute("pesquisaFormaDePagamento", new FormaDePagamento());
			
		} else {
			model.addAttribute("model","Valores informados incorretos");
		}
		
		return "listarformadepagamento";
	}
			
	
	
}
