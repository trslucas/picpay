package com.picpaysimplificado;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PicpaysimplificadoApplication {

	public static void main(String[] args) {
		Dotenv.configure().load();
		SpringApplication.run(PicpaysimplificadoApplication.class, args);
	}

}
