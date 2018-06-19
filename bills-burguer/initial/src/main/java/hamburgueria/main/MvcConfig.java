package hamburgueria.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home/home").setViewName("home/home");
        registry.addViewController("/").setViewName("home/home");
        registry.addViewController("/hello").setViewName("home/hello");
        registry.addViewController("/login").setViewName("home/login");
        registry.addViewController("/403").setViewName("erro/403");
        registry.addViewController("/novousuario").setViewName("usuarios/novousuario");
        registry.addViewController("/listarusuarios").setViewName("usuarios/listarusuarios");
        registry.addViewController("/novocliente").setViewName("clientes/novocliente");
        registry.addViewController("/listarclientes").setViewName("clientes/listarclientes");
        registry.addViewController("/novofuncionario").setViewName("funcionarios/novofuncionario");
        registry.addViewController("/listarfuncionarios").setViewName("funcionarios/listarfuncionarios");
        registry.addViewController("/novamesa").setViewName("mesas/novamesa");
        registry.addViewController("/listarmesas").setViewName("mesas/listarmesas");
        registry.addViewController("/novoproduto").setViewName("produtos/novoproduto");
        registry.addViewController("/listarprodutos").setViewName("produtos/listarprodutos");
        registry.addViewController("/novobairro").setViewName("bairros/novobairro");
        registry.addViewController("/listarbairros").setViewName("bairros/listarbairros");
        registry.addViewController("/novoitem").setViewName("vendas/novoitem");
    }
    
    @Bean (name="dataSource")
    public DriverManagerDataSource dataSource() {
    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
    driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
    driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/hamburgueria");
    driverManagerDataSource.setUsername("postgres");
    driverManagerDataSource.setPassword("postgres");
	return driverManagerDataSource;
    	
    }
    
    @Bean
    public InternalResourceViewResolver viewResolver() {
    	InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    	resolver.setPrefix("erro/403");
    	resolver.setSuffix(".html");
    	return resolver;
    }

}