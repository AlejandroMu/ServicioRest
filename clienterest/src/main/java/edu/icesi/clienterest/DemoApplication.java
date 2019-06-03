package edu.icesi.clienterest;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

import edu.icesi.clienterest.model.Pacient;
import edu.icesi.clienterest.model.User;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
//		RestTemplate f=new RestTemplate();
//		User fd=f.exchange("https://servicerestpacientes.herokuapp.com/usuarios?id=login@gmail.com", HttpMethod.GET, null,
//				new ParameterizedTypeReference<List<User>>() {
//				}).getBody().get(0);
		SpringApplication.run(DemoApplication.class, args);
	}
// 
}
