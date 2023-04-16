package com.example.ListadeTareas;

import com.example.ListadeTareas.entities.Usuarios;
import com.example.ListadeTareas.repository.UsuariosRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ListadeTareasApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ListadeTareasApplication.class, args);
		UsuariosRepository repository = context.getBean(UsuariosRepository.class);
//		repository.save(new Usuarios("camilo","diaz","camilo0@hotmail.com","password","ROLE_USER"));
	}
//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("*").allowedOriginPatterns("*");
//			}
//		};
//	}
}
