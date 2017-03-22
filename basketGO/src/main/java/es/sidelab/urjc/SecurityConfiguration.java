package es.sidelab.urjc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;



@Configuration
//@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    public UserRepositoryAuthenticationProvider authenticationProvider;
	@Override
	 protected void configure(HttpSecurity http) throws Exception {

	 // Public pages
	 http.authorizeRequests().antMatchers("/").permitAll();
	 http.authorizeRequests().antMatchers("/equipo").permitAll();
	 http.authorizeRequests().antMatchers("/index").permitAll();
	 http.authorizeRequests().antMatchers("/jugadores").permitAll();
	 http.authorizeRequests().antMatchers("/registro").permitAll();
	 http.authorizeRequests().antMatchers("/liga").permitAll();
	 http.authorizeRequests().antMatchers("/liga/clasificacion").permitAll();
	 http.authorizeRequests().antMatchers("/loguin").permitAll();
	 http.authorizeRequests().antMatchers("/loguinerror").permitAll();
	 http.authorizeRequests().antMatchers("/logout").permitAll();
	 http.authorizeRequests().antMatchers("/registrado").permitAll();
	 http.authorizeRequests().antMatchers("/registro").permitAll();
	 
	 // Private pages (all other pages)
	 http.authorizeRequests().antMatchers("/jugadores/creacion").hasAnyRole("SUPERADMIN","MANAGER");
	 http.authorizeRequests().antMatchers("/jugadores/registro").hasAnyRole("SUPERADMIN","MANAGER");
	 http.authorizeRequests().antMatchers("/equipos/creacion").hasAnyRole("SUPERADMIN","MANAGER");
	 http.authorizeRequests().antMatchers("/equipos/registro").hasAnyRole("SUPERADMIN","MANAGER");
	 http.authorizeRequests().antMatchers("/liga/creacion").hasAnyRole("SUPERADMIN","ADMIN");
	 http.authorizeRequests().antMatchers("/liga/creando").hasAnyRole("SUPERADMIN","ADMIN");
	 http.authorizeRequests().antMatchers("/liga/gestion").hasAnyRole("SUPERADMIN","ADMIN");
	 http.authorizeRequests().antMatchers("/liga/gestionando").hasAnyRole("SUPERADMIN","ADMIN");
	 http.authorizeRequests().antMatchers("/liga/anadir").hasAnyRole("SUPERADMIN","ADMIN");
	 http.authorizeRequests().antMatchers("/liga/anadiendo").hasAnyRole("SUPERADMIN","ADMIN");


	 // Login form
	 http.formLogin().loginPage("/loguin");
	 http.formLogin().usernameParameter("username");
	 http.formLogin().passwordParameter("password");
	 http.formLogin().defaultSuccessUrl("/postloguin");
	 http.formLogin().failureUrl("/loguinerror");
	 // Logout
	 http.logout().logoutUrl("/logout");
	 http.logout().logoutSuccessUrl("/");

	 }
	@Override
	 protected void configure(AuthenticationManagerBuilder auth) throws Exception {

	 // User
        auth.authenticationProvider(authenticationProvider);

	 }
}
