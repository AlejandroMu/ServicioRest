package edu.icesi.clienterest.Delegado;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.annotation.PostConstruct;

import edu.icesi.clienterest.security.MyUserPrincipal;
import edu.icesi.clienterest.model.*;

/**
 * UserDelegado
 */
public class UserDelegado implements UserDetailsService{
    @Autowired
    private PacienteDelegado pacientService;
    
    @PostConstruct
    public void post() {
        BCryptPasswordEncoder pas = new BCryptPasswordEncoder();
        Pacient p = pacientService.getPacient("1234");
        User us = new User("login@gmail.com", p.getNames(), p.getLastNames(), pas.encode("password"), p);
        p.setUser(us);
        us.setState(true);
        save(us);

    }

    private void save(User us) {
    }

    public Optional<User> getUser(String u) {
        return null;//users.findByUsername(u);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user=getUser(username);
        if(!user.isPresent()){
            throw new UsernameNotFoundException(username);
        }
        return new MyUserPrincipal(user.get());
    }

    
}