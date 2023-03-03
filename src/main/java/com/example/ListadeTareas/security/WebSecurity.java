package com.example.ListadeTareas.security;

import com.example.ListadeTareas.services.JpaUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurity{
    private final JpaUserDetailService jpaUserDetailService;
    public WebSecurity(JpaUserDetailService jpaUserDetailService){
        this.jpaUserDetailService = jpaUserDetailService;
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("**/singup","**/register").permitAll()
                        .anyRequest().denyAll())
                .userDetailsService(jpaUserDetailService)
                .httpBasic(Customizer.withDefaults())
                .build();
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
