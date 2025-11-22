package com.springSecure.springSecure.service;

import com.springSecure.springSecure.model.UserObj;
import com.springSecure.springSecure.repository.UserRepo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class MyUserDetailsService implements UserDetailsService {
    public MyUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserObj byName = userRepo.findByName(username);
        // creating a wrapper class by using existing details
        /*A wrapper is an object that encapsulates another object’s data or adapts it to a different interface.
            UserObj → your app’s internal representation
            User → Spring Security representation (UserDetails)
        *
        ***/
        return new User(byName.getName(),byName.getPassword(), Collections.emptyList());
    }
}
