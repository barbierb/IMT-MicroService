package fr.projetimt.utils;

import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import fr.projetimt.resources.UserResource;
 
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Bean
    public UserDetailsService userDetailsService() {
    	UserDetailsService ures = new UserResource();
        return ures;
    }
     
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
     
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        
        return authProvider;
    }
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.cors().and().csrf().disable()
    	.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/api/user").permitAll()
    		//.antMatchers(HttpMethod.GET, "/api/user").hasRole("ADMIN") on aura essayé :'(
	    	.antMatchers("/api/**").authenticated()
	    	.antMatchers("/**").permitAll()
	    	.anyRequest().authenticated()
    	.and().formLogin().permitAll()
    	.and().logout().logoutSuccessUrl("/").permitAll()
    	.and().httpBasic();
    }
}