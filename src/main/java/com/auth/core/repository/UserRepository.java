package com.auth.core.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auth.core.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	Optional<User> findByUserName(String userName);

	Optional<User> findByEmail(String email);

}
