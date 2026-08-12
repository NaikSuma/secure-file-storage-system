//package com.mit.SecureFileStorage.service;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import com.mit.SecureFileStorage.auth.entity.User;
//
//import java.util.Collection;
//import java.util.Collections;
//
//public class UserDetailsImpl implements UserDetails {
//
//    private final User user;
//
//    public UserDetailsImpl(User user) {
//        this.user = user;
//    }
//
//    @Override
//    public String getUsername() {
//        return user.getUsername();
//    }
//
//    @Override
//    public String getPassword() {
//        return user.getPassword();
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Collections.emptyList(); // If no roles/authorities yet
//    }
//
//    @Override public boolean isAccountNonExpired() 
//    { 
//    	return true; 
//    }
//    @Override public boolean isAccountNonLocked() 
//    { 
//    	return true; 
//    }
//    @Override public boolean isCredentialsNonExpired() { 
//    	return true; 
//    }
//    @Override public boolean isEnabled() { 
//    	return true;
//    	}
//
//    public User getUser() {
//        return user;
//    }
//}
