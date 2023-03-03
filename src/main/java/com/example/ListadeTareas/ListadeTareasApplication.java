package com.example.ListadeTareas;

import com.example.ListadeTareas.entities.Usuarios;
import com.example.ListadeTareas.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ListadeTareasApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ListadeTareasApplication.class, args);
		UserRepository repository = context.getBean(UserRepository.class);
		repository.save(new Usuarios("camilo","diaz","camilo0@hotmail.com","password"));
	}

}
