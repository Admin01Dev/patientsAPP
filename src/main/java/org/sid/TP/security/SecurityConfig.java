package org.sid.TP.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//PasswordEncoder passwordEncoder = passwordEncoder();
		
		auth.inMemoryAuthentication().withUser("Doctor").password("{noop}1234").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("Nurse").password("{noop}5678").roles("USER");
		auth.inMemoryAuthentication().withUser("Support").password("{noop}90").roles("ADMIN", "USER");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.formLogin().defaultSuccessUrl("/list", true);
//		http.httpBasic();
		http.authorizeRequests().antMatchers("/save**/**", "/delete**/**", "/form**/**").hasRole("ADMIN");
		http.authorizeRequests().anyRequest().authenticated();
		//http.csrf().disabled;
		http.exceptionHandling().accessDeniedPage("/notAuthorized");
	}
	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
}
