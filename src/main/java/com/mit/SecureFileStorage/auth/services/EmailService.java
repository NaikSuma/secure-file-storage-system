package com.mit.SecureFileStorage.auth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.mit.SecureFileStorage.auth.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public String sendMail(User user){
        String subject = "Verify your email";
        String senderName = "SecureFileStorage";
        String mailContent = "Hello " + user.getUsername() + ",\n";
        mailContent += "Your verification code is: " + user.getVerificationCode() + "\n";
        mailContent += "Please enter this code to verify your email.";
        mailContent +="\n";
        mailContent+= senderName;
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(sender);
            message.setTo(user.getEmail());
            message.setSubject("Verify your email");
            message.setText("Your verification code is: " + user.getVerificationCode());
            javaMailSender.send(message);
            System.out.println(" Email sent to: " + user.getEmail());
            return "Email sent";
        } catch (Exception e) {
            System.err.println(" Email sending failed: " + e.getMessage());
            e.printStackTrace();
            return "Email sending failed: " + e.getMessage();
        }


}
}
