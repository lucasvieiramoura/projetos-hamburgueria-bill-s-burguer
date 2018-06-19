package hamburgueria.main;

import javax.sql.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private DataSource dataSource;
    
    @Autowired
    public void configAuthentication (AuthenticationManagerBuilder auth) throws Exception {
    	auth.jdbcAuthentication()
    	.dataSource(dataSource)
    	.usersByUsernameQuery(
    	"select username,userpassword,user_status,nome, sobrenome, regra_id from usuario where username=?")
    	.authoritiesByUsernameQuery(
    	"select regra_id,username from usuario where username=?");
    	
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {        
        http
        .authorizeRequests()
            .antMatchers("/", "/home").permitAll()
            .anyRequest().authenticated()
            .and() 
        .formLogin()
            .loginPage("/login")
            .usernameParameter("nome").passwordParameter("senha")
            .permitAll()
            .and()
            .logout()
            .permitAll()
            .and()
	          .csrf().disable();
        
       }
    
}