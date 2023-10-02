package fr.caensup.sio.todo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import fr.caensup.sio.todo.services.DbUserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(
        		(req)->req.requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**"),AntPathRequestMatcher.antMatcher("/createUser/**"))
        		.permitAll()
        		.anyRequest()
        		.authenticated()
        ).formLogin(
        		(form)-> form.loginPage("/login").permitAll())
        		.headers(
        				(headers)->headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
        				);
        return http.build();
    }
    
    @Bean
    public UserDetailsService getUserDetailsService() {
    	return new DbUserService();
    }
    
    @Bean
    public PasswordEncoder getPasswordEncoder() {
    	return new BCryptPasswordEncoder();
    }
}
