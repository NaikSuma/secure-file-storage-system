//package com.mit.SecureFileStorage.auth.entity;
//
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.Date;
//
//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class VerificationToken {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String token;
//
//    @Column(nullable = false)
//    private String email;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date createdAt;
//
//    // Optional: for expiration logic
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date expiresAt;
//
//}
