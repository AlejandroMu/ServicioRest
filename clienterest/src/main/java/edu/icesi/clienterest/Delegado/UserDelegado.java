package edu.icesi.clienterest.Delegado;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
    private User test;
    private RestTemplate rest;

	

	public String url() {
		return "https://servicerestpacientes.herokuapp.com";
	}
    @PostConstruct
    public void post() {
        BCryptPasswordEncoder pas = new BCryptPasswordEncoder();
        Pacient p = pacientService.getPacient("1234");
        User us = new User("login@gmail.com", p.getNames(), p.getLastNames(), pas.encode("password"), p);
        p.setUser(us);
        us.setState(true);
        test=us;
        save(us);
        rest = new RestTemplate();

    }

    private void save(User us) {
    	if(us==null) 
			throw new IllegalArgumentException("User is empty");
		
		rest.put(url()+"/atenciones", us);
    }

    public User getUser(String u) {
    	
        return test;//users.findByUsername(u);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=getUser(username);
        return new MyUserPrincipal(user);
    }

    
}