package fr.projetimt.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().
        https://www.codejava.net/frameworks/spring-boot/http-basic-authentication-with-in-memory-users
        	https://www.baeldung.com/spring-security-jdbc-authentication
        		https://www.google.com/search?q=jpa+spring+basic+http&rlz=1C1GCEU_frDE925DE925&oq=jpa+spring+basic+http&aqs=chrome..69i57j33i22i29i30l2.3584j0j4&sourceid=chrome&ie=UTF-8
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .anyRequest().authenticated()
            .and()
            .httpBasic();
    }  
}