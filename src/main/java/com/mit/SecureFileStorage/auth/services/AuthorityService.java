package com.mit.SecureFileStorage.auth.services;

import com.mit.SecureFileStorage.auth.entity.Authority;
import com.mit.SecureFileStorage.auth.entity.User;
import com.mit.SecureFileStorage.auth.repository.AuthorityRepository;
import com.mit.SecureFileStorage.auth.repository.UserDetailRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
@Service
public class AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    public List<Authority> getUserAuthority(){
       List<Authority> authorities=new ArrayList<>();
       Authority authority= authorityRepository.findByRoleCode("USER");
       authorities.add(authority);
       return  authorities;
    }

    public Authority createAuthority(String role, String description){
        Authority authority= Authority.builder().roleCode(role).roleDescription(description).build();
        return authorityRepository.save(authority);
    }
}