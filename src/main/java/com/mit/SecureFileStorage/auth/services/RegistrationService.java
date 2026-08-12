package com.mit.SecureFileStorage.auth.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerErrorException;

import com.mit.SecureFileStorage.auth.dto.RegistrationRequest;
import com.mit.SecureFileStorage.auth.dto.RegistrationResponse;
import com.mit.SecureFileStorage.auth.entity.User;
import com.mit.SecureFileStorage.auth.helper.VerificationCodeGenerator;
import com.mit.SecureFileStorage.auth.repository.UserDetailRepository;

@Service
public class RegistrationService {

    @Autowired
    private UserDetailRepository userDetailRepository;
//
//    @Autowired
//    private AuthorityService authorityService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    public RegistrationResponse createUser(RegistrationRequest request) {

        Optional<User> existing = userDetailRepository.findByEmail(request.getEmail());
       
        if (existing.isPresent()) {
            return RegistrationResponse.builder()
                    .code(400)
                    .message("Email already exists!")
                    .build();
        }


        try{

            User user = new User();
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setEmail(request.getEmail());
            user.setEnabled(false);
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setProvider("manual");

            String code = VerificationCodeGenerator.generateCode();

            user.setVerificationCode(code);
           // user.setAuthorities(authorityService.getUserAuthority());
            userDetailRepository.save(user);
            emailService.sendMail(user);


            return RegistrationResponse.builder()
                    .code(200)
                    .message("User created!")
                    .build();


        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ServerErrorException(e.getMessage(),e.getCause());
        }
    }

    public void verifyUser(String userName) {
        User user = userDetailRepository.findByEmail(userName)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + userName));
        
        user.setEnabled(true);
        userDetailRepository.save(user);
    }

    
    
}