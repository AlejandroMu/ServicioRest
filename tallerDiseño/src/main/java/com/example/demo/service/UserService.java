package com.example.demo.service;

import java.util.Optional;

import javax.annotation.PostConstruct;

import com.example.demo.model.*;
import com.example.demo.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.*;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository users;
    @Autowired
    private PacientService pacientService;

    @PostConstruct
    public void post() {
        BCryptPasswordEncoder pas = new BCryptPasswordEncoder();
        Pacient p = pacientService.getPacient("1234");
        User us = new User("login@gmail.com", p.getNames(), p.getLastNames(), pas.encode("password"), p);
        p.setUser(us);
        us.setState(true);
        users.save(us);

    }

    public Optional<User> getUser(String u) {
        return users.findByUsername(u);
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