package hamburgueria.main;

import java.text.ParseException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import hamburgueria.dao.RegraDao;
import hamburgueria.dao.RegraUsuarioDao;
import hamburgueria.dao.UsuarioDao;
import hbb.bill.hamburgueria.model.Regra;
import hbb.bill.hamburgueria.model.RegraUsuario;
import hbb.bill.hamburgueria.model.Usuario;


@ComponentScan(basePackages = {"hamburgueria.main","hamburgueria.controller",
		"hamburgueria.dao","hamburgueria.model"})

@EnableAutoConfiguration
public class Application {
	
	@SuppressWarnings("unused")
    public static void main(String[] args) throws ParseException {
    	ConfigurableApplicationContext context = SpringApplication
        .run(Application.class);
    	
    	RegraDao regraDao = context.getBean(RegraDao.class);
    	
    	Regra regra1 = new Regra();
    	regra1.setNome("Administrador");
    	regra1.setRole("Admin");
    	regraDao.create(regra1);
    	
    	Regra regra2 = new Regra();
    	regra2.setNome("Vendas");
    	regra2.setRole("Vendedor");
    	regraDao.create(regra2);
    	
    	Regra regra3 = new Regra();
    	regra3.setNome("Conferencia");
    	regra3.setRole("relatorios");
    	regraDao.create(regra3);
    	
    	UsuarioDao usuarioDao = context.getBean(UsuarioDao.class);
    	
    	Usuario usuario1 = new Usuario();
    	usuario1.setNome("Joao");
    	usuario1.setSobrenome("Galo");
    	usuario1.setUsername("administrador");
    	usuario1.setUserpassword("adm@123");
    	usuario1.setUser_status(true);
    	usuario1.setRegra(regra1);
    	usuarioDao.create(usuario1);
    
    	Usuario usuario2 = new Usuario();
    	usuario2.setNome("Adilson");
    	usuario2.setSobrenome("Maria");
    	usuario2.setUsername("vendedor");
    	usuario2.setUserpassword("vendedor");
    	usuario2.setUser_status(true);
    	usuario2.setRegra(regra2);
    	usuarioDao.create(usuario2);
    	
    	Usuario usuario3 = new Usuario();
    	usuario3.setNome("Thiago");
    	usuario3.setSobrenome("Ferreira");
    	usuario3.setUsername("relatorios");
    	usuario3.setUserpassword("relatorios");
    	usuario3.setUser_status(true);
    	usuario3.setRegra(regra3);
    	usuarioDao.create(usuario3);
    	
    	
    	
    	RegraUsuarioDao regraUsuarioDao = context.getBean(RegraUsuarioDao.class);
    	
    	RegraUsuario regraUsuario1 = new RegraUsuario();
    	regraUsuario1.setUsuario(usuario1);
    	regraUsuario1.setRegra(regra1);
    	regraUsuarioDao.create(regraUsuario1);
    	
    	RegraUsuario regraUsuario2 = new RegraUsuario();
    	regraUsuario2.setUsuario(usuario2);
    	regraUsuario2.setRegra(regra2);
    	regraUsuarioDao.create(regraUsuario2);
    	
    	RegraUsuario regraUsuario3 = new RegraUsuario();
    	regraUsuario3.setUsuario(usuario3);
    	regraUsuario3.setRegra(regra3);
    	regraUsuarioDao.create(regraUsuario3);
    	/*
    	ProdutoDao produtoDao = context.getBean(ProdutoDao.class);
    	
    	Produto produto1 = new Produto();
    	produto1.setNome("Numero 1");
    	produto1.setDescricao("O produto numero 1 contem : 1 carne alface, tomate, ovo, batata palha");
    	produto1.setPreco(7.0);
    	produto1.setDataDeCadastro(26-11-2017 00:00);
    	produto1.setStatus(true);
    	produtoDao.create(produto1);*/
    }

}
