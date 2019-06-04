package edu.icesi.clienterest.Delegado;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

import edu.icesi.clienterest.security.MyUserPrincipal;
import edu.icesi.clienterest.model.*;

/**
 * UserDelegado
 */
@Service
public class UserDelegado implements UserDetailsService{
    @Autowired
    private PacienteDelegado pacientService;
    private RestTemplate template;
    @PostConstruct
    public void post() {
        template=new RestTemplate();
    	BCryptPasswordEncoder pas = new BCryptPasswordEncoder(11);
        Pacient p = pacientService.getPacient("1234");
        User us = new User("login1@gmail.com", p.getNames(), p.getLastNames(), pas.encode("password1"), p);
        p.setUser(us);
        us.setState(true);
        save(us);
    }
    public String url() {
		return "https://servicerestpacientes.herokuapp.com";
	}
    public void save(User us) {
    	if(us==null) 
			throw new IllegalArgumentException("User is empty");
		
    	
    	template.postForObject(url()+"/usuarios", us, User.class);
    	
    }

    public User getUser(String u) {
    	if (u == null)
			throw new IllegalArgumentException("UserName is null");
		ResponseEntity<List<User>> rEntity = template.exchange(url() + "/usuarios?id="+u, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<User>>() {
				});
		if (rEntity.getStatusCode() == HttpStatus.PRECONDITION_FAILED)
			throw new IllegalArgumentException("User is empty");

		return rEntity.getBody().get(0);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=getUser(username);
        return new MyUserPrincipal(user);
    }


    
}