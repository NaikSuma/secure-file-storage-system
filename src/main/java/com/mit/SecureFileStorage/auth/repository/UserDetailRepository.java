package com.mit.SecureFileStorage.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mit.SecureFileStorage.auth.entity.User;

import java.util.Optional;


@Repository
public interface UserDetailRepository extends JpaRepository<User,Long> {
	 Optional<User> findByEmail(String username);
}