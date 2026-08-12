//package com.mit.SecureFileStorage.service;
//
//
//
//import com.mit.SecureFileStorage.auth.entity.User;
//import com.mit.SecureFileStorage.auth.repository.UserDetailRepository;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.*;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private UserDetailRepository userRepository;
//
//    // Spring Security will call this during login
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//    	User user = userRepository
//    		    .findByEmail(username)
//    		    .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
//		return user;
//
//
//        //return new UserDetailsImpl(user);
//    }
//}
//
