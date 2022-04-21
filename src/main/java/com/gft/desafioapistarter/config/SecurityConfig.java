package com.gft.desafioapistarter.config;

import com.gft.desafioapistarter.service.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioServiceImpl usuarioService;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                userDetailsService(usuarioService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf() .disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/api/starters/**", "/api/categoria/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST,"/api/starters", "/api/categoria").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/api/starters/**", "/api/categoria/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/usuarios/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }
}
