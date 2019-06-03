package edu.icesi.clienterest.Delegado;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
    	BCryptPasswordEncoder pas = new BCryptPasswordEncoder();
        Pacient p = pacientService.getPacient("1234");
        User us = new User("login@gmail.com", p.getNames(), p.getLastNames(), pas.encode("password"), p);
        p.setUser(us);
        us.setState(true);
        save(us);
    }
    public String url() {
		return "https://servicerestpacientes.herokuapp.com";
	}
    private void save(User us) {
    	if(us==null) 
			throw new IllegalArgumentException("User is empty");
		
    	template.put(url()+"/usuarios", us);
    }

    public User getUser(String u) {
    	if(u==null) {
			throw new IllegalArgumentException("id is empty");
		}
		ResponseEntity<User> rEntity=template.getForEntity(url()+"/usuarios", User.class,u);
		if (rEntity.getStatusCode() == HttpStatus.PRECONDITION_FAILED)
			throw new IllegalArgumentException("User is empty");
		return rEntity.getBody();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=getUser(username);
        return new MyUserPrincipal(user);
    }

    
}