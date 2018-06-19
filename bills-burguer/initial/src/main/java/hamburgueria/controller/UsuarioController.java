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
import hamburgueria.dao.UsuarioDao;
import hbb.bill.hamburgueria.model.Regra;
import hbb.bill.hamburgueria.model.Usuario;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private RegraDao regraDao;
	
	
	@GetMapping("/novousuario")
	private String novousuario(Model model) {
		model.addAttribute("usuario", new Usuario());
		
		List<Regra> regras = regraDao.getAll(Regra.class);
		model.addAttribute("regras", regras);
		return "usuarios/novousuario";
	}
	
	@PostMapping("/salvarusuario")
	public String salvarusuario(
			@RequestParam(value="id") long id,
			@ModelAttribute Usuario usuario,
			Model model) {
		if (id != 0) {
			usuario.setId(id);
			usuario.setRegra(regraDao.getById(Regra.class, usuario.getRegra().getId()));
			usuarioDao.update(usuario);
			model.addAttribute("message", "Usuario alterado com sucesso !");
		} else {
			usuario.setRegra(regraDao.getById(Regra.class, usuario.getRegra().getId()));
			usuarioDao.create(usuario);
			model.addAttribute("message", "Usuario criado com sucesso !");
		}
		return "usuarios/novousuario";
	}
	
	@GetMapping("/listarusuarios")
	public String listarusuarios (
			@RequestParam(value="nome", defaultValue="") String nome,
			Model model) {
		List<Usuario> usuarios = usuarioDao.getAll(Usuario.class);
		if(!nome.equals(""))
			usuarios = usuarioDao.getByUsername(nome);
		model.addAttribute("usuarios", usuarios);
		model.addAttribute("usuarioPesquisa", new Usuario());
		return "usuarios/listarusuarios";
		
	} 
	
	@GetMapping ("/editarusuario")
	public String editarusuario(
			@RequestParam(value="id") long id,
			Model model ) {
		Usuario usuario = usuarioDao.getById(Usuario.class, id);
		model.addAttribute("usuario", usuario);
		
		List<Regra> regras = regraDao.getAll(Regra.class);
		model.addAttribute("regras", regras);
		
		return "usuarios/novousuario";
	}
	
	@GetMapping ("/removerusuario")
	public String removerusuario (
			@RequestParam (value="id") long id,
			Model model) {
		Usuario usuario = usuarioDao.getById(Usuario.class, id);
		if(usuario != null)
			usuarioDao.delete(usuario);
		model.addAttribute("message", "Usuario removido com sucesso !");
		List<Usuario> usuarios = usuarioDao.getAll(Usuario.class);
		model.addAttribute("usuarios", usuarios);
		model.addAttribute("usuarioPesquisa", new Usuario());
		
		return "usuarios/listarusuarios";
			
	}
}

