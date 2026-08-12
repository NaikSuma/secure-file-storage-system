package com.mit.SecureFileStorage.auth.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mit.SecureFileStorage.auth.entity.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, UUID> {
    Authority findByRoleCode(String user);
}