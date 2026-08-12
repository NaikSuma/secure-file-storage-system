package com.mit.SecureFileStorage.auth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mit.SecureFileStorage.auth.entity.User;
import com.mit.SecureFileStorage.auth.repository.UserDetailRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserDetailRepository userDetailRepository;

   
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	User user =userDetailRepository
    		    .findByEmail(username)
    		    .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
		return user;


        //return new UserDetailsImpl(user);
    }

}